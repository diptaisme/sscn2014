package id.go.bkn.sscn.core.report.command.workbook;

import id.go.bkn.sscn.core.report.GeneralReportFactory;
import id.go.bkn.sscn.persistence.entities.DtPendaftaran;
import id.go.bkn.sscn.persistence.entities.view.DataPendaftaran;
import id.go.bkn.sscn.persistence.entities.view.RekapanPendaftaran;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class DecodeData {
	// constants
	private static final String ROWNUM = "ROWNUM";
	private static final Log LOG = LogFactory.getLog(DecodeData.class);

	/**
	 * Inner interface used to create decoder objects.
	 * 
	 * 
	 */
	private interface Decoder {
		Object[] decode(Object object);
	}

	/**
	 * Map containing decoder objects.
	 */
	private static final Map<String, Decoder> DECODER_MAP = new HashMap<String, Decoder>();

	// Put Decoder objects and their corresponding reportType here.
	static {
		DECODER_MAP.put(GeneralReportFactory.RPT_TEST_CETAK.toLowerCase(),
				new Decoder() {
					public Object[] decode(Object object) {
						return decodeTestSaja(object);
					}
				});
		DECODER_MAP.put(GeneralReportFactory.RPT_DATA_PENDAFTARAN.toLowerCase(),
				new Decoder() {
					public Object[] decode(Object object) {
						return decodeDataPendaftaran(object);
					}
				});
		DECODER_MAP.put(GeneralReportFactory.RPT_DATA_PESERTA_TEST.toLowerCase(),
				new Decoder() {
					public Object[] decode(Object object) {
						return decodeDataPesertaTest(object);
					}
				});

		DECODER_MAP.put(GeneralReportFactory.RPT_REKAPAN_PENDAFTARAN.toLowerCase(),
				new Decoder() {
					public Object[] decode(Object object) {
						return decodeDataRekapanPendaftaran(object);
					}
				});
	}

	public static Object[] decodeObject(Object object, String reportType) {
		if (reportType == null) {
			LOG.debug("reportType is null");
			return new Object[] {};
		}
		Decoder decoder = DECODER_MAP.get(reportType.toLowerCase());
		if (decoder != null) {
			return decoder.decode(object);
		} else {
			LOG.debug("Decoder object for reportType '" + reportType
					+ "' is not found");
			return new Object[] {};
		}
	}

	protected static Object[] decodeTestSaja(Object object) {
		DtPendaftaran val = (DtPendaftaran) object;
		return new Object[] { ROWNUM, val.getId(), val.getNama(), };
	}
	
	protected static Object[] decodeDataPendaftaran(Object object) {
		DataPendaftaran val = (DataPendaftaran) object;
		return new Object[] { ROWNUM, val.getNoNik(), val.getTglCreated(), val.getNoRegister(), val.getNoPeserta(), val.getNama(), 
				val.getTmpLahir(), val.getTglLahir(), val.getJnsKelamin(), val.getAlamat(), val.getKodePos(), val.getPropinsi(), val.getKota(),
				val.getTelpon(), val.getEmail(), val.getAsalInstitusiPendidikan(), val.getNoIjazah(), val.getAkreditasi(), val.getNilaiIpk(),
				val.getLokasiKode(), val.getLokasiNama(), val.getJabatanKode(), val.getJabatanNama(), val.getPendidikanKode(), val.getPendidikanNama(), val.getStatus()};
	}
	protected static Object[] decodeDataPesertaTest(Object object) {
		DataPendaftaran val = (DataPendaftaran) object;
		return new Object[] { ROWNUM, val.getNoNik(), val.getNoRegister(), val.getNoPeserta(), val.getNama(), 
				val.getTmpLahir(), val.getTglLahir(), val.getJnsKelamin(), val.getAlamat(), val.getKodePos(), val.getPropinsi(), val.getKota(),
				val.getTelpon(), val.getEmail(), val.getAsalInstitusiPendidikan(), val.getNoIjazah(), val.getAkreditasi(), val.getNilaiIpk(),
				val.getLokasiKode(), val.getLokasiNama(), val.getJabatanKode(), val.getJabatanNama(), val.getPendidikanKode(), val.getPendidikanNama(), val.getTglCreated(), val.getStatus()};
	}
	protected static Object[] decodeDataRekapanPendaftaran(Object object) {
		RekapanPendaftaran val = (RekapanPendaftaran) object;
		return new Object[] { ROWNUM, val.getLokasi(), val.getJabatan(), val.getPendidikan(), 
				val.getJumlahPendaftar(), val.getJumlahLulus(), val.getJumlahTidakLulus(), val.getJumlahBelumVerifikasi()};
	}
}
