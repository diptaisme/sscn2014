package id.go.bkn.sscn.core.report.command.workbook;

import id.go.bkn.sscn.core.report.GeneralReportUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class TestWorkbook extends BaseWorkbook {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(TestWorkbook.class);

	/** The report type. */
	private String reportType;

	/** The param1. */
	private String param1;

	/** The param2. */
	private String param2;

	/**
	 * Constructor.
	 * 
	 * @param sheetProperties
	 *            the sheet properties
	 */
	public TestWorkbook(Map<String, Object> sheetProperties) {
		this(sheetProperties, "Report");
	}

	/**
	 * 
	 * @param sheetProperties
	 * @param reportName
	 */
	public TestWorkbook(Map<String, Object> sheetProperties, String reportName) {
		super();
		setTheProperties(sheetProperties);
		// create sheet
		createStyledSheet(reportName);
	}

	/**
	 * 
	 * @param sheetName
	 */
	public void addSheet(String sheetName) {
		createStyledSheet(sheetName);
	}

	/**
	 * Constructor.
	 * 
	 * @param sheetProperties
	 *            the sheet properties
	 * @param inp
	 *            the inp
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public TestWorkbook(Map<String, Object> sheetProperties, InputStream inp)
			throws IOException {
		super(sheetProperties, inp);
		setTheProperties(sheetProperties);
		// get sheet
		sheet = this.getSheetAt(0);
	}

	/**
	 * Sets the properties.
	 * 
	 * @param sheetProperties
	 *            the sheet properties
	 */
	@Override
	protected void setProperties(Map<String, Object> sheetProperties) {
		setTheProperties(sheetProperties);
	}

	/**
	 * Gets the styles.
	 * 
	 * @return styles
	 */
	public Map<String, CellStyle> getStyles() {
		return this.styles;
	}

	/**
	 * Sets the header.
	 */
	@Override
	public void setHeader() {
		final int rowNumber = 2;
		final int columnNumber = 5;
		for (int i = 0; i < rowNumber; i++) {
			try {
				Row row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				for (int j = 0; j < columnNumber; j++) {
					Cell cell = row.getCell(j);
					if (cell != null) {
						BaseWorkbook.replaceCellValue(cell, "{PARAM_1}",
								this.param1);
						BaseWorkbook.replaceCellValue(cell, "{PARAM_2}",
								this.param2);
					}
				}
			} catch (IllegalStateException e) {
				LOG.error("Row: " + i, e);
				continue;
			}
		}
	}

	/**
	 * Fill data.
	 * 
	 * @param startIdx
	 *            the start idx
	 * @param pMyData
	 *            the my data
	 * @return the int
	 */
	public int fillData(int startIdx, Object[] pMyData) {
		Row row;
		Cell cell;
		int rownum = startIdx;
		for (int i = 0; i < pMyData.length; i++, rownum++) {
			if (pMyData[i] == null) {
				continue;
			}
			Object[] objArr = DecodeData.decodeObject(pMyData[i], reportType);
			row = sheet.createRow(rownum);
			for (int j = 0; j < objArr.length; j++) {
				cell = row.createCell(j);
				String styleName = STYLE_CELL_CHARACTER;
				if (objArr[j] != null) {
					char cFormat = 'C';
					try {
						cFormat = formatData.charAt(j);
						if (cFormat == 'N') {
							styleName = STYLE_CELL_NUMERIC;
						}
					} finally {
						if (objArr[j].toString().equals("ROWNUM")) {
							cell.setCellValue(i + 1);
							styleName = STYLE_ROWNUM;
						} else {
							if (cFormat == 'N') {
								cell.setCellValue(Integer.parseInt(objArr[j]
										.toString()));
							} else {
								cell.setCellValue(objArr[j].toString());
							}
						}
					}
				}
				cell.setCellStyle(styles.get(styleName));
			}
		}
		// rownum is the last + 1 rows of the data
		return rownum;
	}

	/**
	 * 
	 * @param sheetProperties
	 */
	private void setTheProperties(Map<String, Object> sheetProperties) {
		reportType = GeneralReportUtil.RPT_TEST_CETAK;
		formatData = getStringParameter(sheetProperties,
				"FORMAT");
		param1 = getStringParameter(sheetProperties, "PARAM_1");
		param2 = getStringParameter(sheetProperties, "PARAM_2");
	}
}
