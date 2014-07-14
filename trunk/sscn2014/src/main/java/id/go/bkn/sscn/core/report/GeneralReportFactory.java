package id.go.bkn.sscn.core.report;

import id.go.bkn.sscn.core.report.command.ReportCommand;
import id.go.bkn.sscn.core.report.command.ReportDataPendaftaranCommand;
import id.go.bkn.sscn.core.report.command.ReportDataPesertaTestCommand;
import id.go.bkn.sscn.core.report.command.ReportPesertaUjianCommand;
import id.go.bkn.sscn.core.report.command.ReportRegistrasi2014Command;
import id.go.bkn.sscn.core.report.command.ReportRegistrasiCommand;
import id.go.bkn.sscn.core.report.command.ReportRekapanPendaftaranCommand;
import id.go.bkn.sscn.core.report.command.ReportTestCommand;
import id.go.bkn.sscn.servlet.reportcommand.PengumumanReportCommand;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.LogFactory;

public class GeneralReportFactory {
	private static final org.apache.commons.logging.Log LOG = LogFactory
			.getLog(GeneralReportFactory.class);

	/** The Constant RPT_DATA_PENDAFTARAN. */
	public static final String RPT_DATA_PENDAFTARAN = "rptDataPendaftaran";
	/** The Constant RPT_DATA_PESERTA_TEST. */
	public static final String RPT_DATA_PESERTA_TEST = "rptDataPesertaTest";
	/** The Constant RPT_TEST_CETAK. */
	public static final String RPT_TEST_CETAK = "rptTestCetak";
	/** The Constant RPT_REGISTRASI. */
	public static final String RPT_REGISTRASI = "rptRegistrasi";
	/** The Constant RPT_REGISTRASI_2014. */
	public static final String RPT_REGISTRASI_2014 = "rptRegistrasi2014";
	/** The Constant RPT_PESERTA_UJIAN. */
	public static final String RPT_PESERTA_UJIAN = "rptPesertaUjian";
	/** The Constant RPT_REKAPAN_PENDAFTARAN. */
	public static final String RPT_REKAPAN_PENDAFTARAN = "rptRekapanPendaftaran";

	/** The Constant REPORT_PENGUMUMAN. */
	public static final String REPORT_PENGUMUMAN = "reportPengumuman";

	private static final Map<String, Class<? extends ReportCommand>> REPORT_COMMANDS = new HashMap<String, Class<? extends ReportCommand>>(
			54, 1);

	static {
		REPORT_COMMANDS.put(RPT_TEST_CETAK, ReportTestCommand.class);
		REPORT_COMMANDS.put(RPT_REGISTRASI, ReportRegistrasiCommand.class);
		REPORT_COMMANDS.put(RPT_REGISTRASI_2014, ReportRegistrasi2014Command.class);
		REPORT_COMMANDS.put(REPORT_PENGUMUMAN, PengumumanReportCommand.class);
		REPORT_COMMANDS.put(RPT_PESERTA_UJIAN, ReportPesertaUjianCommand.class);
		REPORT_COMMANDS.put(RPT_DATA_PENDAFTARAN,
				ReportDataPendaftaranCommand.class);
		REPORT_COMMANDS.put(RPT_DATA_PESERTA_TEST,
				ReportDataPesertaTestCommand.class);
		REPORT_COMMANDS.put(RPT_REKAPAN_PENDAFTARAN,
				ReportRekapanPendaftaranCommand.class);
	}

	/**
	 * This private constructor is created to prevent instantiation of this
	 * class.
	 * 
	 */
	private GeneralReportFactory() {
		// to prevent instantiation
	}

	/**
	 * This method is a factory method to get the <b> ReportCommand </b> object
	 * according to the <code> reportType </code> parameter given.
	 * 
	 * @param reportType
	 *            String
	 * @return ReportCommand
	 */
	public static ReportCommand getReportCommand(String reportType) {
		ReportCommand reportCommand = null;
		Class<? extends ReportCommand> reportCommandClass = REPORT_COMMANDS
				.get(reportType);
		if (reportCommandClass != null) {
			try {
				reportCommand = reportCommandClass.newInstance();
			} catch (Exception e) {
				LOG.debug("Can't create a ReportCommand object.", e);
			}
		}
		return reportCommand;
	}
}
