package id.go.bkn.sscn.core.report.command;

import id.go.bkn.sscn.core.report.GeneralReportUtil;
import id.go.bkn.sscn.dao.RefPendidikanDao;
import id.go.bkn.sscn.manager.Constanta;
import id.go.bkn.sscn.persistence.entities.DtPendaftaran;
import id.go.bkn.sscn.persistence.entities.RefPendidikan;
import id.go.bkn.sscn.services.RegistrasiService;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;


@Component("ReportPesertaUjianCommand")
public class ReportPesertaUjianCommand extends ReportCommand {

	@Inject
	private RegistrasiService registrasiService;
	
	@Inject
	private RefPendidikanDao refPendidikanDao;

	/**
	 * serialVersionUID
	 */

	private static final long serialVersionUID = -599742719308669945L;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		// if (request.getParameter("formID") != null) {
		// check form id dari form Pendaftaran web SSCN Server
		// if (request.getParameter("formID").equals("32063786011852")) {
		try {
			// 14636
			String noPendaftaran = request.getParameter("no_pendaftaran");
			DtPendaftaran pendaftaran = registrasiService
					.getPendaftaranByNoRegistrasi(noPendaftaran);

			if (pendaftaran == null) {
				cetakRegistrasiGagal(response);
			} else {
				// generate pdf :)
				try {
					Map<String, Object> mapParamater = generateParameterToReport(pendaftaran);

					String baseDir = getBaseDirectory(request);
					String fileName = baseDir
							+ GeneralReportUtil.getRptPesertaUjian();

					InputStream logo = loadDefaultLogo(request);
					InputStream logo2 = loadDefaultLogo(request);
					mapParamater.put("LOGO",logo);
					mapParamater.put("LOGO2",logo2);
					Object[] arrResult = new Object[]{new Object()};
					this.generalPDFReports(arrResult, request, response,
							mapParamater, fileName);
				} catch (Exception ex) {
					ex.printStackTrace();
					cetakRegistrasiGagal(response);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			cetakRegistrasiGagal(response);
		}
		// }
		// else {
		// cetakNotAksesRegistrasi(response);
		// }
		// }
		// else {
		// cetakNotAksesRegistrasi(response);
		// }

	}

	private void cetakRegistrasiGagal(HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<HTML><HEAD><TITLE>SSCN Server</TITLE>"
					+ "</HEAD><BODY>Maaf proses cetak kartu peserta ujian gagal. Klik <a href='"
					+ Constanta.URL_WEB_SSCN
					+ "'>link ini </a> untuk kembali</BODY></HTML>");
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}	

	@Override
	protected byte[] generateXls(Object[] pMyData, Map<String, Object> myMap)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	private Map<String, Object> generateParameterToReport(
			DtPendaftaran pendaftaran) {
		Map<String, Object> mapParamater = new HashMap<String, Object>();

		String noPeserta = pendaftaran.getNoPeserta();
		String formattedNoPeserta = noPeserta.substring(0, 4) + "-"
				+ noPeserta.substring(4, 5) + "-" + noPeserta.substring(5, 10)
				+ "-" + noPeserta.substring(10, 11);
		mapParamater.put("NOMOR_PESERTA", formattedNoPeserta);

		mapParamater.put("NAMA", pendaftaran.getNama());
		mapParamater.put("JENIS_KELAMIN",
				(pendaftaran.getJnsKelamin()).equals("P") ? "Pria" : "Wanita");
		String tempatLahir = pendaftaran.getTmpLahir();
		SimpleDateFormat formatDateJava = new SimpleDateFormat("dd-MM-yyyy");
		String tglLahir = formatDateJava.format(pendaftaran.getTglLahir());
		mapParamater.put("TTL", tempatLahir + " / " + tglLahir);

		String kodePendidikan = pendaftaran.getPendidikan();
		RefPendidikan refPendidikan = refPendidikanDao.findByProperty("kode", kodePendidikan, null).get(0);		
		mapParamater.put("PENDIDIKAN", refPendidikan.getKode() + " - "+refPendidikan.getNama());
		mapParamater.put("JABATAN", pendaftaran.getFormasi().getRefJabatan().getKode() +" - "+pendaftaran.getFormasi().getRefJabatan()
				.getNama());
		mapParamater.put("LOKASI", pendaftaran.getFormasi().getRefLokasi()
				.getNama());
		mapParamater.put("INSTANSI", pendaftaran.getFormasi().getRefInstansi()
				.getNama());

		return mapParamater;
	}

}
