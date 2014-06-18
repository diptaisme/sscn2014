package id.go.bkn.sscn.core.report.command;

import id.go.bkn.sscn.core.report.command.workbook.TestWorkbook;
import id.go.bkn.sscn.dao.DtPendaftaranDao;
import id.go.bkn.sscn.persistence.entities.DtPendaftaran;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component("ReportTestCommand")
public class ReportTestCommand extends ReportCommand {
	/** The Constant OUTPUT_STREAM_INITIAL_SIZE. */
	private static final int OUTPUT_STREAM_INITIAL_SIZE = 2048;

	private static final String TEMPLATE = "TEMPLATE";

	public static final String REPORT_TYPE = "XLS";

	private static final int INT_START_INDEX = 4;

	public static final String FORMAT = "FORMAT";

	private static final String START_INDEX = "START_INDEX";

	public static final String TANGGAL = "TANGGAL";

	@Inject
	private DtPendaftaranDao dtPendaftaranDao;

	@Override
	protected byte[] generateXls(Object[] pMyData, Map<String, Object> myMap)
			throws IOException {
		ByteArrayOutputStream bt = new ByteArrayOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(bt,
				OUTPUT_STREAM_INITIAL_SIZE);
		InputStream inp = new FileInputStream(myMap.get(TEMPLATE).toString());
		TestWorkbook wb = new TestWorkbook(myMap, inp);
		wb.createStyles();
		wb.setHeader();
		if (pMyData.length > 0) {
			// fill excel data
			int startIndex = Integer
					.parseInt(myMap.get(START_INDEX).toString());
			int lastRow = wb.fillData(startIndex, pMyData);
			// wb.createSummary(lastRow, 1);
		}
		wb.write(bos);
		bos.flush();
		bos.close();
		return bt.toByteArray();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		String reportBaseDir = getReportBaseDirectory(request);
		Map<String, Object> excelMap = new HashMap<String, Object>();

		Date nowTime = java.util.Calendar.getInstance().getTime();
		Format formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");		

		List<DtPendaftaran> listPns = dtPendaftaranDao.findAll(new int[] { 0, 50});

		Object[] pnsArray = listPns.toArray();

		excelMap.put(TEMPLATE, reportBaseDir + "rptTest.xls");
		excelMap.put(REPORT_TYPE, "XLS");
		excelMap.put("TANGGAL", "Keadaan " + formatter.format(nowTime));
		excelMap.put(START_INDEX, Integer.valueOf(INT_START_INDEX));
		excelMap.put(FORMAT, "NCCCCCCCC");

		generateXlsReport(pnsArray, response, excelMap, "rptTest.xls");

	}
}
