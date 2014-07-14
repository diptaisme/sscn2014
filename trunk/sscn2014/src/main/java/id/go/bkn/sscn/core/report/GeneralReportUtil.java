package id.go.bkn.sscn.core.report;

public class GeneralReportUtil {
	/** The default directory. */
	private static String defaultDirectory = "/reports/";
	/** The logo garuda. */
	public static String PATH_TO_GARUDA_LOGO = "/reports/garuda.png";
	/** The rpt Test. */
	private static String rptTest = defaultDirectory + "rptTest.xls";
	/** The rpt DataPendaftaran. */
	private static String rptDataPendaftaran = defaultDirectory
			+ "rptDataPendaftaran.xls";

	/** The rpt DataPesertaTest. */
	private static String rptDataPesertaTest = defaultDirectory
			+ "rptDataPesertaTest.xls";

	/** The rpt Rekapan Pendaftaran. */
	private static String rptRekapanPendaftaran = defaultDirectory
			+ "rptRekapanPendaftaran.xls";

	/** The rpt Registrasi. */
	private static String rptRegistrasi = defaultDirectory
			+ "rptRegistrasi.jasper";
	/** The rpt Registrasi2014. */
	private static String rptRegistrasi2014 = defaultDirectory
			+ "rptRegistrasi2014.jasper";
	/** The rpt Peserta Ujian. */
	private static String rptPesertaUjian = defaultDirectory
			+ "rptPesertaUjian.jasper";

	public static String getRptTest() {
		return rptTest;
	}

	public static String getRptRegistrasi() {
		return rptRegistrasi;
	}

	public static String getRptRegistrasi2014() {
		return rptRegistrasi2014;
	}

	public static String getRptPesertaUjian() {
		return rptPesertaUjian;
	}

	public static String getRptDataPendaftaran() {
		return rptDataPendaftaran;
	}

	public static String getRptDataPesertaTest() {
		return rptDataPesertaTest;
	}

	/** The Constant RPT_TEST_CETAK. */
	public static final String RPT_TEST_CETAK = "rptTestCetak";
	/** The Constant RPT_DATA_PENDAFTARAN. */
	public static final String RPT_DATA_PENDAFTARAN = "rptDataPendaftaran";
	/** The Constant RPT_DATA_PESERTA_TEST. */
	public static final String RPT_DATA_PESERTA_TEST = "rptDataPesertaTest";
	/** The Constant RPT_REKAPAN_PENDAFTARAN. */
	public static final String RPT_REKAPAN_PENDAFTARAN = "rptRekapanPendaftaran";
}
