package id.go.bkn.sscn.core.report.command;

import id.go.bkn.sscn.core.report.GeneralReportUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * The Class ReportCommand.
 */
public abstract class ReportCommand implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6822044242911655588L;

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(ReportCommand.class);

	protected static final int FIRST_COLUMN_WIDTH = 1800;
	protected static final int COLUMN_WIDTH = 5400;
	protected static final int MONTHS_IN_A_YEAR = 12;

	/** The output filename. */
	private static String outputFilename;

	/** The attachment constant */
	public static final boolean ATTACHMENT = true;
	public static final boolean INLINE = false;

	/** The file directory separator. */
	protected static final String SLASH = File.separator;

	protected static final String SHORT_DATE_FORMAT_STRING = "dd/MM/yyyy";
	protected static final String LONG_DATE_FORMAT_STRING = "dd MMMMM yyyy";

	protected static final DateFormat SHORT_DATE_FORMAT = new SimpleDateFormat(
			SHORT_DATE_FORMAT_STRING);
	protected static final DateFormat LONG_DATE_FORMAT = new SimpleDateFormat(
			LONG_DATE_FORMAT_STRING);

	/**
	 * Instantiates a new report command.
	 * 
	 * @param ctx
	 *            the ctx *
	 */
	public ReportCommand() {
		super();
	}

	/**
	 * This method sends to the client the report generated (array the bytes) <br>
	 * base on the array of POJOS by the response.
	 * 
	 * @param pMyData
	 *            the my data
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param myMap
	 *            the my map
	 * @param pJasperReportFile
	 *            the jasper report file
	 * @throws ServletException
	 *             if an error occurred
	 */
	protected void generalPDFReports(Object[] pMyData,
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> myMap, String pJasperReportFile)
			throws ServletException {
		try {
			byte[] bytes = null;
			net.sf.jasperreports.engine.data.JRBeanArrayDataSource myJrDataSource = new JRBeanArrayDataSource(
					pMyData);
			bytes = JasperRunManager.runReportToPdf(pJasperReportFile, myMap,
					myJrDataSource);
			serveReport(request, response, bytes);
		} catch (Exception e) {
			LOG.error("Failed to Generate General PDF Report", e);
			throw new ServletException("Failed to Generate General PDF Report",
					e);
		}
	}

	/**
	 * Serve report document to the client, could be local (directly send pdf
	 * document to client) or remote (send printing job to the Cups Server).
	 * 
	 * @param request
	 * @param response
	 * @param bytes
	 * @throws IOException
	 */
	private void serveReport(HttpServletRequest request,
			HttpServletResponse response, byte[] bytes) throws IOException {
		OutputStream outputStream = null;
		try {
			InputStream inputStream = new ByteArrayInputStream(bytes);
			outputStream = response.getOutputStream();	
			// send the pdf document to the client directly
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			outputStream.write(bytes, 0, bytes.length);
			outputStream.flush();
		} catch (IOException ex) {
			if (outputStream != null){
				outputStream.close();
			}			
			throw ex;
		} finally {
			if (outputStream != null){
				outputStream.close();
			}
		}
	}

	protected void generalPDFReports(Object[] pMyData,
			HttpServletResponse response, Map<String, Object> myMap,
			String pJasperReportFile, Boolean isAttachment, String outFilename,
			Boolean isProtected) throws ServletException {
		try {
			byte[] bytes = null;
			net.sf.jasperreports.engine.data.JRBeanArrayDataSource myJrDataSource = new JRBeanArrayDataSource(
					pMyData);
			if (isProtected != null && isProtected) {
				bytes = runReportToProtectedPdf(pJasperReportFile, myMap,
						myJrDataSource, outFilename);
			} else {
				bytes = JasperRunManager.runReportToPdf(pJasperReportFile,
						myMap, myJrDataSource);
			}

			ServletOutputStream servletOutputStream = response
					.getOutputStream();
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);

			if (isAttachment != null && isAttachment) {
				setAttachment(response, outFilename);
			} else {
				response.setHeader("Content-Disposition", "inline; filename="
						+ outFilename);
				response.setHeader("Location",
						"report/#toolbar=0&scrollbar=0&navpanes=0");
			}

			servletOutputStream.write(bytes, 0, bytes.length);
			servletOutputStream.flush();
			servletOutputStream.close();
		} catch (Exception e) {
			LOG.error("Failed to Generate General PDF Report", e);
			throw new ServletException("Failed to Generate General PDF Report",
					e);
		}
	}

	/**
	 * Fills a report and sends it to an output stream in PDF format. The
	 * intermediate JasperPrint object is not saved on disk.
	 */

	@SuppressWarnings("unchecked")
	protected static byte[] runReportToProtectedPdf(String sourceFileName,
			Map parameters, JRDataSource jrDataSource, String outFileName)
			throws JRException {
		JasperPrint jasperPrint = JasperFillManager.fillReport(sourceFileName,
				parameters, jrDataSource);
		return exportReportToProtectedPdf(jasperPrint, outFileName);
	}

	/**
	 * Exports the generated report object received as parameter into PDF format
	 * and returns the binary content as a byte array.
	 * 
	 * @param jasperPrint
	 *            report object to export
	 * @return byte array representing the resulting PDF content
	 * @see net.sf.jasperreports.engine.export.JRPdfExporter
	 */
	protected static byte[] exportReportToProtectedPdf(JasperPrint jasperPrint,
			String outFileName) throws JRException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
		exporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);
		exporter.setParameter(JRPdfExporterParameter.IS_128_BIT_KEY,
				Boolean.TRUE);
		exporter.setParameter(JRPdfExporterParameter.USER_PASSWORD, "");
		exporter.setParameter(JRPdfExporterParameter.OWNER_PASSWORD, "");
		// exporter.setParameter(JRPdfExporterParameter.PERMISSIONS, new
		// Integer(PdfWriter.ALLOW_SCREENREADERS | PdfWriter.ALLOW_COPY |
		// PdfWriter.ALLOW_PRINTING | PdfWriter.ALLOW_MODIFY_CONTENTS |
		// PdfWriter.ALLOW_MODIFY_ANNOTATIONS));
		exporter.setParameter(JRPdfExporterParameter.PERMISSIONS,
				Integer.valueOf(0));
		exporter.exportReport();
		return baos.toByteArray();
	}

	/**
	 * Generate xls.
	 * 
	 * @param pMyData
	 *            the my data
	 * @param myMap
	 *            the my map
	 * @return the byte[]
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * 
	 */
	protected abstract byte[] generateXls(Object[] pMyData,
			Map<String, Object> myMap) throws IOException;

	/**
	 * Creates the styled sheet.
	 * 
	 * @param wb
	 *            the wb
	 * @param name
	 *            the name
	 * @return the sheet
	 * 
	 */
	protected static Sheet createStyledSheet(Workbook wb, String name) {
		Sheet sheet = wb.createSheet(name);
		// turn off gridlines
		sheet.setDisplayGridlines(false);
		sheet.setPrintGridlines(false);
		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);
		final double rightMargin = 0.25;
		final double leftMargin = 0.25;
		sheet.setMargin(Sheet.RightMargin, rightMargin);
		sheet.setMargin(Sheet.LeftMargin, leftMargin);
		sheet.getPrintSetup().setLandscape(true);
		return sheet;
	}

	/**
	 * Creates the styled row.
	 * 
	 * @param sheet
	 *            the sheet
	 * @param rowIndex
	 *            the row index
	 * @param style
	 *            the style
	 * @param cols
	 *            the cols
	 * @return the row
	 * 
	 */
	protected static Row createStyledRow(Sheet sheet, int rowIndex,
			CellStyle style, int cols) {
		Row row = sheet.createRow(rowIndex);
		for (int i = 0; i < cols; i++) {
			Cell cell = row.createCell(i);
			cell.setCellStyle(style);
		}
		return row;
	}

	/**
	 * Sets the auto size for all colums.
	 * 
	 * @param sheet
	 *            the sheet
	 * @param cols
	 *            the cols
	 * 
	 */
	protected static void setAutoSizeForAllColums(Sheet sheet, int cols) {
		final int firstColumnWidth = 1800;
		final int columnWidth = 5400;
		for (int j = 0; j < cols; j++) {
			if (j == 0) {
				sheet.setColumnWidth(j, firstColumnWidth);
			} else {
				sheet.setColumnWidth(j, columnWidth);
			}
		}
	}

	/**
	 * This method sends to the client the report generated (array the bytes) <br>
	 * base on the array of POJOS by the response. The report is in excel (.xls)
	 * format.
	 * 
	 * @param pMyData
	 *            the my data
	 * @param response
	 *            the response
	 * @param myMap
	 *            the my map
	 * @param pJasperReportFile
	 *            the jasper report file
	 * @param pExcelOutputFile
	 *            the excel output file
	 * @throws ServletException
	 *             if an error occurred
	 * 
	 */
	protected void generalXlsReports(Object[] pMyData,
			HttpServletResponse response, Map<String, Object> myMap,
			String pJasperReportFile, String... pExcelOutputFile)
			throws ServletException {
		try {
			net.sf.jasperreports.engine.data.JRBeanArrayDataSource myJrDataSource = new JRBeanArrayDataSource(
					pMyData);
			byte[] bytes = runReportToXls(pJasperReportFile, myMap,
					myJrDataSource);
			ServletOutputStream servletOutputStream = response
					.getOutputStream();
			// Excel 97
			response.setContentType("application/xls");
			// Excel 2007
			// response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

			if ((pExcelOutputFile != null) && (pExcelOutputFile.length > 0)) {
				setAttachment(response, pExcelOutputFile[0]);
			} else {
				setAttachment(response, "BKNDefaultReport.xls");
			}
			response.setContentLength(bytes.length);
			servletOutputStream.write(bytes, 0, bytes.length);
			servletOutputStream.flush();
			servletOutputStream.close();
		} catch (Exception e) {
			LOG.error("Failed to Generate General Excel Report", e);
			throw new ServletException(
					"Failed to Generate General Excel Report", e);
		}
	}

	/**
	 * Generate xls report.
	 * 
	 * @param pMyData
	 *            the my data
	 * @param response
	 *            the response
	 * @param myMap
	 *            the my map
	 * @param pExcelFile
	 *            the excel file
	 * @throws ServletException
	 *             the servlet exception
	 */
	protected void generateXlsReport(Object[] pMyData,
			HttpServletResponse response, Map<String, Object> myMap,
			String pExcelFile) throws ServletException {
		try {
			byte[] bytes = generateXls(pMyData, myMap);
			ServletOutputStream servletOutputStream = response
					.getOutputStream();
			// Excel 97
			response.setContentType("application/xls");
			// Excel 2007
			// response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

			setAttachment(response, pExcelFile);
			response.setContentLength(bytes.length);
			servletOutputStream.write(bytes, 0, bytes.length);
			servletOutputStream.flush();
			servletOutputStream.close();
		} catch (Exception e) {
			LOG.error("Failed to Generate General Excel Report", e);
			throw new ServletException(
					"Failed to Generate General Excel Report", e);
		}
	}

	/**
	 * Fills a report and sends it to an output stream in PDF format. The
	 * intermediate JasperPrint object is not saved on disk.
	 * 
	 * @param sourceFileName
	 *            String
	 * @param parameters
	 *            Map<String, Object>
	 * @param jrDataSource
	 *            JRDataSource
	 * @return byte[]
	 * @throws JRException
	 *             the jR exception
	 * 
	 */
	protected static byte[] runReportToXls(String sourceFileName,
			Map<String, Object> parameters, JRDataSource jrDataSource)
			throws JRException {
		JasperPrint jasperPrint = JasperFillManager.fillReport(sourceFileName,
				parameters, jrDataSource);
		return exportReportToXls(jasperPrint);
	}

	/**
	 * Exports the generated report object received as parameter into PDF format
	 * and returns the binary content as a byte array.
	 * 
	 * @param jasperPrint
	 *            report object to export
	 * @return byte array representing the resulting PDF content
	 * @throws JRException
	 *             the jR exception
	 * 
	 * @see net.sf.jasperreports.engine.export.JRPdfExporter
	 */
	protected static byte[] exportReportToXls(JasperPrint jasperPrint)
			throws JRException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JRXlsExporter exporterXLS = new JRXlsExporter();
		exporterXLS.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporterXLS.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		exporterXLS.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				outputFilename);
		exporterXLS.setParameter(
				JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.FALSE);
		exporterXLS.setParameter(
				JRXlsAbstractExporterParameter.IS_DETECT_CELL_TYPE,
				Boolean.TRUE);
		exporterXLS.setParameter(
				JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				Boolean.FALSE);
		exporterXLS
				.setParameter(
						JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
						Boolean.TRUE);
		exporterXLS.exportReport();

		return baos.toByteArray();
	}

	/**
	 * Execute.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return void
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException 
	 * 
	 */
	public abstract void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

	/**
	 * Sets the output filename.
	 * 
	 * @param outputFilename
	 *            String
	 * @return void
	 * 
	 */
	public static void setOutputFilename(String outputFilename) {
		ReportCommand.outputFilename = outputFilename;
	}

	/**
	 * Gets the output filename.
	 * 
	 * @return String
	 * 
	 */
	public static String getOutputFilename() {
		return outputFilename;
	}

	/**
	 * Creates the styled font.
	 * 
	 * @param wb
	 *            the wb
	 * @param boldWeight
	 *            the bold weight
	 * @param fontHeighInPoints
	 *            the font heigh in points
	 * @param color
	 *            the color
	 * @return the font
	 * 
	 */
	private static Font createStyledFont(Workbook wb, Short boldWeight,
			Short fontHeighInPoints, Short color) {
		if (wb != null) {
			Font font = wb.createFont();
			if (boldWeight != null) {
				font.setBoldweight(boldWeight);
			}
			if (fontHeighInPoints != null) {
				font.setFontHeightInPoints(fontHeighInPoints);
			}
			if (color != null) {
				font.setColor(color);
			}
			return font;
		}
		return null;
	}

	/**
	 * Adds the addtional style.
	 * 
	 * @param style
	 *            the style
	 * @param alignment
	 *            the alignment
	 * @param fillForegroundColor
	 *            the fill foreground color
	 * @param fillPattern
	 *            the fill pattern
	 * @param font
	 *            the font
	 * @param wrapText
	 *            the wrap text
	 * @return the cell style
	 * 
	 */
	private static CellStyle addAddtionalStyle(CellStyle style,
			Short alignment, Short fillForegroundColor, Short fillPattern,
			Font font, Boolean wrapText) {
		if (style != null) {
			if (alignment != null) {
				style.setAlignment(alignment.shortValue());
			}
			if (fillForegroundColor != null) {
				style.setFillForegroundColor(fillForegroundColor.shortValue());
			}
			if (fillPattern != null) {
				style.setFillPattern(fillPattern.shortValue());
			}
			if (font != null) {
				style.setFont(font);
			}
			if (wrapText != null) {
				style.setWrapText(wrapText.booleanValue());
			}
		}
		return style;
	}

	/**
	 * Creates the bordered style.
	 * 
	 * @param wb
	 *            the wb
	 * @return the cell style
	 * 
	 */
	private static CellStyle createBorderedStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		return style;
	}

	/**
	 * Creates the sub title style.
	 * 
	 * @param wb
	 *            the wb
	 * @return the cell style
	 * 
	 */
	private static CellStyle createSubTitleStyle(Workbook wb) {
		final Integer fontHeight = 12;
		return addAddtionalStyle(
				createBorderedStyle(wb),
				CellStyle.ALIGN_LEFT,
				IndexedColors.BLUE_GREY.getIndex(),
				CellStyle.SOLID_FOREGROUND,
				createStyledFont(wb, Font.BOLDWEIGHT_BOLD,
						fontHeight.shortValue(), null), null);
	}

	/**
	 * Creates the title style.
	 * 
	 * @param wb
	 *            the wb
	 * @return the cell style
	 * 
	 */
	private static CellStyle createTitleStyle(Workbook wb) {
		final Integer fontHeight = 24;
		return addAddtionalStyle(
				createBorderedStyle(wb),
				CellStyle.ALIGN_CENTER,
				IndexedColors.AQUA.getIndex(),
				CellStyle.SOLID_FOREGROUND,
				createStyledFont(wb, Font.BOLDWEIGHT_BOLD,
						fontHeight.shortValue(), null), null);
	}

	/**
	 * Creates the header style.
	 * 
	 * @param wb
	 *            the wb
	 * @return the cell style
	 * 
	 */
	private static CellStyle createHeaderStyle(Workbook wb) {
		CellStyle headerStyle = addAddtionalStyle(createBorderedStyle(wb),
				CellStyle.ALIGN_CENTER,
				IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex(),
				CellStyle.SOLID_FOREGROUND,
				createStyledFont(wb, Font.BOLDWEIGHT_BOLD, null, null), true);
		headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		return headerStyle;
	}

	/**
	 * Creates the header date style.
	 * 
	 * @param wb
	 *            the wb
	 * @return the cell style
	 * 
	 */
	private static CellStyle createHeaderDateStyle(Workbook wb) {
		DataFormat df = wb.createDataFormat();
		CellStyle headerDateStyle = addAddtionalStyle(createBorderedStyle(wb),
				CellStyle.ALIGN_CENTER,
				IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex(),
				CellStyle.SOLID_FOREGROUND,
				createStyledFont(wb, Font.BOLDWEIGHT_BOLD, null, null), null);
		headerDateStyle.setDataFormat(df.getFormat("d-mmm"));
		return headerDateStyle;
	}

	/**
	 * Creates the group header style.
	 * 
	 * @param wb
	 *            the wb
	 * @return the cell style
	 * 
	 */
	private static CellStyle createGroupHeaderStyle(Workbook wb) {
		CellStyle groupHeaderStyle = addAddtionalStyle(
				createBorderedStyle(wb),
				CellStyle.ALIGN_LEFT,
				IndexedColors.LAVENDER.getIndex(),
				CellStyle.SOLID_FOREGROUND,
				createStyledFont(wb, Font.BOLDWEIGHT_BOLD, null,
						IndexedColors.BLUE.getIndex()), null);
		final Integer identation = 5;
		groupHeaderStyle.setIndention(identation.shortValue());
		return groupHeaderStyle;
	}

	/**
	 * Creates the cell budget category style.
	 * 
	 * @param wb
	 *            the wb
	 * @return the cell style
	 * 
	 */
	private static CellStyle createCellBudgetCategoryStyle(Workbook wb) {
		return addAddtionalStyle(createBorderedStyle(wb), CellStyle.ALIGN_LEFT,
				null, null, null, false);
	}

	/**
	 * Creates the cell budget numeric style.
	 * 
	 * @param wb
	 *            the wb
	 * @return the cell style
	 * 
	 */
	private static CellStyle createCellBudgetNumericStyle(Workbook wb) {
		CellStyle cellBudgetNumericStyle = addAddtionalStyle(
				createBorderedStyle(wb), CellStyle.ALIGN_RIGHT, null, null,
				null, false);
		final Integer dataFormat = 3;
		cellBudgetNumericStyle.setDataFormat(dataFormat.shortValue());
		return cellBudgetNumericStyle;
	}

	/**
	 * Creates the cell footer style.
	 * 
	 * @param wb
	 *            the wb
	 * @return the cell style
	 * 
	 */
	private static CellStyle createCellFooterStyle(Workbook wb) {
		return addAddtionalStyle(createBorderedStyle(wb),
				CellStyle.ALIGN_CENTER, null, null, null, false);
	}

	/**
	 * create a library of cell styles.
	 * 
	 * @param wb
	 *            the wb
	 * @return the map
	 */
	protected static Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		styles.put("title", createTitleStyle(wb));
		styles.put("sub_title", createSubTitleStyle(wb));
		styles.put("header", createHeaderStyle(wb));
		styles.put("group_header", createGroupHeaderStyle(wb));
		styles.put("header_date", createHeaderDateStyle(wb));
		styles.put("cell_budget_category", createCellBudgetCategoryStyle(wb));
		styles.put("cell_budget_numeric", createCellBudgetNumericStyle(wb));
		styles.put("cell_footer", createCellFooterStyle(wb));
		return styles;
	}

	/**
	 * load default logo.
	 * 
	 * @param request
	 *            the request
	 * @return the input stream
	 * 
	 */

	protected static InputStream loadDefaultLogo(HttpServletRequest request) {
		InputStream logoStream = null; 
		try {
			logoStream = new FileInputStream(request
					.getSession()
					.getServletContext()
					.getRealPath(
							"/WEB-INF" + GeneralReportUtil.PATH_TO_GARUDA_LOGO));
		} catch (FileNotFoundException e) {
			LOG.error("Default logo not found in "
					+ GeneralReportUtil.PATH_TO_GARUDA_LOGO + ".", e.getCause());
		}
		return logoStream;
	}

	/**
	 * Get base directory name.
	 * 
	 * @param request
	 *            HttpServletRequest object
	 * @return base directory name
	 */
	protected static String getBaseDirectory(HttpServletRequest request) {
		return request.getSession().getServletContext()
				.getRealPath("/WEB-INF/");
	}

	/**
	 * Construct the report base directory.
	 * 
	 * 
	 * @param request
	 *            HttpServletRequest object
	 * @return report base directory name
	 */
	protected static String getReportBaseDirectory(HttpServletRequest request) {
		return new StringBuilder(getBaseDirectory(request)).append(SLASH)
				.append("reports").append(SLASH).toString();
	}

	/**
	 * Create a string for a date with the format "dd/MM/yyyy"
	 * 
	 * @param date
	 * @return formatted date, or an empty string if the date is null.
	 */
	protected static String formatShortDate(Date date) {
		if (date == null) {
			return "";
		} else {
			return SHORT_DATE_FORMAT.format(date);
		}
	}

	/**
	 * Create a string for a date with the format "dd MMMMM yyyy"
	 * 
	 * @param date
	 * @return formatted date, or an empty string if the date is null.
	 */
	protected static String formatLongDate(Date date) {
		if (date == null) {
			return "";
		} else {
			return LONG_DATE_FORMAT.format(date);
		}
	}

	/**
	 * Parse a short date string ("dd/MM/yyyy") into a Date object.
	 * 
	 * @param dateString
	 *            Date string.
	 * @return Date object.
	 * @throws ParseException
	 */
	protected static Date parseShortDateString(String dateString)
			throws ParseException {
		return SHORT_DATE_FORMAT.parse(dateString);
	}

	/**
	 * Parse a long date string ("dd MMMMM yyyy") into a Date object.
	 * 
	 * @param date
	 *            Date string.
	 * @return Date object.
	 * @throws ParseException
	 */
	protected static Date parseLongDateString(String dateString)
			throws ParseException {
		return LONG_DATE_FORMAT.parse(dateString);
	}

	/**
	 * 
	 * @param response
	 * @param filename
	 */
	protected static void setAttachment(HttpServletResponse response,
			String filename) {
		response.setHeader("Content-disposition", "attachment; filename="
				+ filename);
	}

	/**
	 * 
	 * @param response
	 * @param filename
	 */
	protected static void setInline(HttpServletResponse response,
			String filename) {
		response.setHeader("Content-disposition", "inline; filename="
				+ filename);
	}

}
