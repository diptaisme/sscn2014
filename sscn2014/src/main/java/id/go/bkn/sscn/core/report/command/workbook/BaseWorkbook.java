package id.go.bkn.sscn.core.report.command.workbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * <code>BaseWorkbook</code>
 *
 */
public class BaseWorkbook extends HSSFWorkbook {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(BaseWorkbook.class);

	/** The Constant FORMAT. */
	protected static final String FORMAT = "FORMAT";

	// Styles Constants
	public static final String STYLE_CELL_CHARACTER = "cell_character";
	public static final String STYLE_CELL_NUMERIC = "cell_numeric";
	public static final String STYLE_TITLE = "title";
	public static final String STYLE_SUB_TITLE = "subtitle";
	public static final String STYLE_HEADER = "header";
	public static final String STYLE_GROUP_HEADER = "group_header";
	public static final String STYLE_HEADER_DATE = "header_date";
	public static final String STYLE_ROWNUM = "rownum";
	public static final String STYLE_CELL_FOOTER = "cell_footer";
	public static final String STYLE_ROW_ALL_BOLD = "rowAllBold";

	/** The sheet. */
	protected Sheet sheet;

	/** The styles. */
	protected Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

	/** The headers. */
	protected Map<Integer, Object> headers;

	protected Map<String, Object> excelMap;

	/** Number of column. */
	protected int colNumber;

	/** The format data. */
	protected String formatData;

	/** The title. */
	protected String title;

	/** The sub title. */
	protected String subTitle;

	/** The start index. */
	protected Integer startIndex;

	/**
	 * Constructor.
	 */
	public BaseWorkbook() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param reportMap
	 *            the sheet properties
	 */
	public BaseWorkbook(Map<String, Object> reportMap) {
		this(reportMap, "Report");
	}

	/**
	 * 
	 * @param excelMap
	 * @param reportName
	 */
	public BaseWorkbook(Map<String, Object> excelMap, String reportName) {
		super();
		setTheProperties(excelMap);
		// create sheet
		createTheStyledSheet(reportName);
	}

	/**
	 * Constructor.
	 * 
	 * @param excelMap
	 * @throws IOException
	 * @param templateInputStream
	 */
	public BaseWorkbook(Map<String, Object> excelMap,
			InputStream templateInputStream) throws IOException {
		super(new POIFSFileSystem(templateInputStream));
		this.excelMap = excelMap;
		setTheProperties(excelMap);
		sheet = this.getSheetAt(0);
	}

	/**
	 * Sets the properties.
	 * 
	 * @param excelMap
	 *            the sheet properties
	 */
	protected void setProperties(Map<String, Object> excelMap) {
		setTheProperties(excelMap);
	}

	/**
	 * This method is created to avoid 'costructor calls overridable method'
	 * violation.
	 * 
	 * @param excelMap
	 */
	@SuppressWarnings("unchecked")
	private void setTheProperties(Map<String, Object> excelMap) {
		colNumber = getIntParameter(excelMap, "COLNUM");
		formatData = getStringParameter(excelMap, "FORMAT");
		title = getStringParameter(excelMap, "TITLE");
		subTitle = getStringParameter(excelMap, "ADDITIONAL_TITLE");
		headers = (Map<Integer, Object>) excelMap.get("HEADER");
		formatData = getStringParameter(excelMap, "FORMAT");
		startIndex = getIntParameter(excelMap, "START_INDEX");
	}

	/**
	 * Prepare Parameter.
	 * 
	 */
	public void setHeader() {
		for (int i = 0; i < startIndex; i++) {
			try {
				Row row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				for (int j = 0; j < this.colNumber; j++) {
					Cell cell = row.getCell(j);
					if (cell != null) {
						StringBuilder parameter = new StringBuilder();
						for (Entry<String, Object> entry : excelMap.entrySet()) {
							parameter.append("{").append(entry.getKey())
									.append("}");
							replaceCellValue(cell, parameter.toString(),
									entry.getValue());
							// reset parameter
							parameter.delete(0, parameter.length());
						}
					}
				}
			} catch (IllegalStateException e) {
				LOG.error("Row: " + i, e);
				continue;
			}
		}
	}

	/**
	 * Creates the header.
	 * 
	 */
	public void createHeader() {
		createHeader(0, true);
	}

	/**
	 * Creates the header.
	 * 
	 */
	public void createHeader(int rowIndex, boolean freeze) {
		int currentRowIndex = rowIndex;
		// create title row
		Row titleRow = createStyledRow(currentRowIndex,
				getCellStyle(STYLE_TITLE), colNumber);
		titleRow.getCell(0).setCellValue(title);
		if (title.contains("\n")) {
			final double multipler = 1.5;
			Double height = titleRow.getHeight() * multipler
					* (StringUtils.countMatches(title, "\n") + 1);
			titleRow.setHeight(height.shortValue());
		}
		sheet.addMergedRegion(new CellRangeAddress(currentRowIndex,
				currentRowIndex, 0, colNumber - 1));
		currentRowIndex++;
		// create sub title row
		Row subTitleRow = createStyledRow(currentRowIndex,
				getCellStyle(STYLE_SUB_TITLE), colNumber);
		sheet.addMergedRegion(new CellRangeAddress(currentRowIndex,
				currentRowIndex, 1, colNumber - 1));
		subTitleRow.getCell(0).setCellValue(subTitle);
		currentRowIndex++;
		// separator row
		Row sepRow = sheet.createRow(currentRowIndex);
		final float height = 2.5f;
		sepRow.setHeightInPoints(height);
		currentRowIndex++;
		// the header row
		for (Entry<Integer, Object> entry : headers.entrySet()) {
			String[] arr = (String[]) entry.getValue();
			Row row = sheet.createRow(currentRowIndex > 0 ? currentRowIndex
					: entry.getKey());
			for (int i = 0; i < arr.length; i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(arr[i]);
				cell.setCellStyle(getCellStyle(STYLE_HEADER));
			}
		}
		if (freeze) {
			// freeze the first row
			final int rowSplit = 5;
			sheet.createFreezePane(0, rowSplit);
		}
	}

	/**
	 * Creates the summary.
	 * 
	 * @param rownum
	 *            the rownum
	 * @param mergeCol
	 *            the merge col
	 */
	public void createSummary(int rownum, int mergeCol) {
		createSummary(rownum, mergeCol, this.startIndex);
	}

	/**
	 * Creates the summary.
	 * 
	 * @param rownum
	 *            the rownum
	 * @param mergeCol
	 *            the merge col
	 */
	public void createSummary(int rownum, int mergeCol, int pStartIndex) {
		LOG.info("Create Summary Report.");
		// Summary row
		Row totalRow = sheet.createRow(rownum);
		// Row firstRow = sheet.getRow(this.startIndex);
		Cell cell;
		for (int i = 0; i < colNumber; i++) {
			cell = totalRow.createCell(i);
			cell.setCellStyle(getCellStyle(STYLE_CELL_CHARACTER));
			// if ((i > mergeCol) &&
			// (firstRow.getCell(i).getCellStyle().equals(styles.get(CELL_NUMERIC))))
			// {
			if ((i > mergeCol) && (formatData.charAt(i) == 'N')) {
				cell.setCellStyle(getCellStyle(STYLE_CELL_NUMERIC));
				cell.setCellFormula("SUM(" + decodeChar(i) + (pStartIndex + 1)
						+ ":" + decodeChar(i) + Integer.toString(rownum) + ")");
			}
		}
		totalRow.getCell(0).setCellValue("JUMLAH SELURUHNYA");
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, mergeCol));
	}

	/**
	 * Replace cell value.
	 * 
	 * @param cell
	 *            the cell
	 * @param parameter
	 *            the parameter
	 * @param replacement
	 *            the replacement
	 * @return the string
	 */
	public static String replaceCellValue(Cell cell, String parameter,
			Object replacement) {
		String replacementStr;
		if (replacement == null) {
			replacementStr = "";
		} else {
			replacementStr = replacement.toString();
		}
		String cellValue = "";
		if (cell.getCellType() == Cell.CELL_TYPE_STRING
				|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			cellValue = cell.getStringCellValue();
			if (cellValue.contains(parameter)) {
				cellValue = cellValue.replace(parameter, replacementStr);
				cell.setCellValue(cellValue);
			}
		}
		return cellValue;
	}

	/**
	 * Decode char.
	 * 
	 * @param colIndex
	 *            the col index
	 * @return the char
	 */
	protected static String decodeChar(int colIndex) {
		String[] chars = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z", "AA", "AB", "AC", "AD", "AE", "AF", "AG",
				"AH", "AI", "AJ", "AK", "AL", "AM", "AN", "AO", "AP", "AQ",
				"AR" };
		return chars[colIndex];
	}

	/**
	 * Creates the styled sheet.
	 * 
	 * @param name
	 *            the name
	 */
	protected void createStyledSheet(String name) {
		createTheStyledSheet(name);
	}

	/**
	 * This method is created to avoid the 'constructor calls overridable
	 * method' violation.
	 * 
	 * @param name
	 */
	private void createTheStyledSheet(String name) {
		sheet = this.createSheet(name);
		// turn off gridlines
		sheet.setDisplayGridlines(false);
		sheet.setPrintGridlines(false);
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
	 */
	protected static CellStyle addAddtionalStyle(CellStyle style,
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
	 * @return the cell style
	 */
	protected CellStyle createBorderedStyle() {
		CellStyle style = this.createCellStyle();
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
	 * Creates the title style.
	 * 
	 * @return the cell style
	 */
	protected CellStyle createTitleStyle() {
		final int fontHeight = 14;
		return addAddtionalStyle(createBorderedStyle(), CellStyle.ALIGN_CENTER,
				IndexedColors.AQUA.getIndex(), CellStyle.SOLID_FOREGROUND,
				createStyledFont(Font.BOLDWEIGHT_BOLD, fontHeight, null), true);
	}

	/**
	 * Creates the sub title style.
	 * 
	 * @return the cell style
	 */
	protected CellStyle createSubTitleStyle() {
		final int fontHeight = 12;
		return addAddtionalStyle(createBorderedStyle(), CellStyle.ALIGN_LEFT,
				IndexedColors.BLUE_GREY.getIndex(), CellStyle.SOLID_FOREGROUND,
				createStyledFont(Font.BOLDWEIGHT_BOLD, fontHeight, null), null);
	}

	/**
	 * Creates the header style.
	 * 
	 * @return the cell style
	 */
	protected CellStyle createHeaderStyle() {
		CellStyle headerStyle;
		headerStyle = addAddtionalStyle(createBorderedStyle(),
				CellStyle.ALIGN_CENTER,
				IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex(),
				CellStyle.SOLID_FOREGROUND,
				createStyledFont(Font.BOLDWEIGHT_BOLD, null, null), true);
		headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headerStyle.setWrapText(true);
		return headerStyle;
	}

	/**
	 * Creates the group header style.
	 * 
	 * @return the cell style
	 */
	protected CellStyle createGroupHeaderStyle() {
		CellStyle groupHeaderStyle;
		groupHeaderStyle = addAddtionalStyle(
				createBorderedStyle(),
				CellStyle.ALIGN_LEFT,
				IndexedColors.LAVENDER.getIndex(),
				CellStyle.SOLID_FOREGROUND,
				createStyledFont(Font.BOLDWEIGHT_BOLD, null,
						IndexedColors.BLUE.getIndex()), null);
		final Integer indention = 5;
		groupHeaderStyle.setIndention(indention.shortValue());
		return groupHeaderStyle;
	}

	/**
	 * Creates the styled font.
	 * 
	 * @param boldWeight
	 *            the bold weight
	 * @param fontHeighInPoints
	 *            the font heigh in points
	 * @param color
	 *            the color
	 * @return the font
	 */
	protected Font createStyledFont(Short boldWeight,
			Integer fontHeighInPoints, Short color) {
		Font font = this.createFont();
		if (boldWeight != null) {
			font.setBoldweight(boldWeight);
		}
		if (fontHeighInPoints != null) {
			font.setFontHeightInPoints(fontHeighInPoints.shortValue());
		}
		if (color != null) {
			font.setColor(color.shortValue());
		}
		return font;
	}

	/**
	 * Creates the styled row.
	 * 
	 * @param rowIndex
	 *            the row index
	 * @param style
	 *            the style
	 * @param cols
	 *            the cols
	 * @return the row
	 */
	protected Row createStyledRow(int rowIndex, CellStyle style, int cols) {
		Row row = sheet.createRow(rowIndex);
		for (int i = 0; i < cols; i++) {
			Cell cell = row.createCell(i);
			cell.setCellStyle(style);
		}
		return row;
	}

	/**
	 * Creates the header date style.
	 * 
	 * @return the cell style
	 */
	protected CellStyle createHeaderDateStyle() {
		CellStyle headerDateStyle;
		DataFormat df = this.createDataFormat();
		headerDateStyle = addAddtionalStyle(createBorderedStyle(),
				CellStyle.ALIGN_CENTER,
				IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex(),
				CellStyle.SOLID_FOREGROUND,
				createStyledFont(Font.BOLDWEIGHT_BOLD, null, null), null);
		headerDateStyle.setDataFormat(df.getFormat("d-mmm"));
		return headerDateStyle;
	}

	/**
	 * Creates the row num style.
	 * 
	 * @return the cell style
	 */
	protected CellStyle createRowNumStyle() {
		CellStyle cellStyle = createCellNumericStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		return cellStyle;
	}

	/**
	 * Creates the cell numeric style.
	 * 
	 * @return the cell style
	 */
	protected CellStyle createCellNumericStyle() {
		CellStyle cellBudgetNumericStyle = addAddtionalStyle(
				createBorderedStyle(), CellStyle.ALIGN_RIGHT, null, null, null,
				false);
		final Integer dataFormat = 3;
		cellBudgetNumericStyle.setDataFormat(dataFormat.shortValue());
		return cellBudgetNumericStyle;
	}

	/**
	 * Creates the cell character style.
	 * 
	 * @return the cell style
	 */
	protected CellStyle createCellCharacterStyle() {
		return addAddtionalStyle(createBorderedStyle(), CellStyle.ALIGN_LEFT,
				null, null, null, true);
	}

	/**
	 * Creates the cell footer style.
	 * 
	 * @return the cell style
	 */
	protected CellStyle createCellFooterStyle() {
		return addAddtionalStyle(createBorderedStyle(), CellStyle.ALIGN_CENTER,
				null, null, null, false);
	}

	/**
	 * Creates the cell signature style.
	 * 
	 * @return the cell style
	 */
	protected CellStyle createCellSignatureStyle() {
		return addAddtionalStyle(this.createCellStyle(),
				CellStyle.ALIGN_CENTER, null, null, null, false);
	}

	/**
	 * Creates the styles
	 */
	public void createStyles() {
		styles.put(STYLE_TITLE, createTitleStyle());
		styles.put(STYLE_SUB_TITLE, createSubTitleStyle());
		styles.put(STYLE_HEADER, createHeaderStyle());
		styles.put(STYLE_GROUP_HEADER, createGroupHeaderStyle());
		styles.put(STYLE_GROUP_HEADER, createHeaderDateStyle());
		styles.put(STYLE_ROWNUM, createRowNumStyle());
		styles.put(STYLE_ROW_ALL_BOLD, createRowAllBold());
		styles.put(STYLE_CELL_CHARACTER, createCellCharacterStyle());
		styles.put(STYLE_CELL_NUMERIC, createCellNumericStyle());
		styles.put(STYLE_CELL_FOOTER, createCellFooterStyle());
	}

	protected CellStyle createRowAllBold() {
		CellStyle cellStyle = createCellNumericStyle();
		HSSFFont font = createFont();
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setFont(font);
		return cellStyle;
	}

	/**
	 * Lookup for created cell style. return cell character style if styleName
	 * not found.
	 * 
	 * @param styleName
	 *            style name.
	 * @return cell style.
	 */
	public CellStyle getCellStyle(String styleName) {
		CellStyle cellStyle = styles.get(styleName);
		return cellStyle != null ? cellStyle : styles.get(STYLE_CELL_CHARACTER);
	}

	/**
	 * Gets the string parameter.
	 * 
	 * @param parameters
	 *            the parameters
	 * @param key
	 *            the key
	 * @return the string parameter
	 */
	protected static String getStringParameter(Map<String, Object> parameters,
			String key) {
		return getStringParameter(parameters, key, "");
	}

	/**
	 * Gets the string parameter.
	 * 
	 * @param parameters
	 *            the parameters
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the string parameter
	 * @author hakim
	 */
	protected static String getStringParameter(Map<String, Object> parameters,
			String key, String defaultValue) {
		return parameters.get(key) == null ? defaultValue : parameters.get(key)
				.toString();
	}

	/**
	 * Gets the int parameter.
	 * 
	 * @param parameters
	 *            the parameters
	 * @param key
	 *            the key
	 * @return the int parameter
	 */
	protected static Integer getIntParameter(Map<String, Object> parameters,
			String key) {
		return Integer.valueOf(getStringParameter(parameters, key, "0"));
	}

	/**
	 * @return the sheet
	 */
	public Sheet getSheet() {
		return sheet;
	}

	/**
	 * @return the colNumber
	 */
	public int getColNumber() {
		return colNumber;
	}

	/**
	 * @param colNumber
	 *            the colNumber to set
	 */
	public void setColNumber(int colNumber) {
		this.colNumber = colNumber;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the subTitle
	 */
	public String getSubTitle() {
		return subTitle;
	}

	/**
	 * @param subTitle
	 *            the subTitle to set
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
}
