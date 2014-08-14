package id.go.bkn.sscn.core.report.command;

import id.go.bkn.sscn.core.report.GeneralReportUtil;
import id.go.bkn.sscn.dao.RefLokasiTestDao;
import id.go.bkn.sscn.dao.RefPendidikanDao;
import id.go.bkn.sscn.manager.Constanta;
import id.go.bkn.sscn.persistence.entities.DtPendaftaran;
import id.go.bkn.sscn.persistence.entities.DtPersyaratan;
import id.go.bkn.sscn.persistence.entities.TabelPendaftar;
import id.go.bkn.sscn.services.PersyaratanService;
import id.go.bkn.sscn.services.RegistrasiService;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component("ReportRegistrasi2014Command")
public class ReportRegistrasi2014Command extends ReportCommand {

	@Inject
	private RefPendidikanDao refPendidikanDao;

	@Inject
	private RegistrasiService registrasiService;

	@Inject
	private PersyaratanService persyaratanService;
	
	@Inject
	private RefLokasiTestDao refLokasiTestDao;
	/**
	 * serialVersionUID
	 */

	private static final long serialVersionUID = -599742719308669945L;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		String formId = request.getParameter("formID");
		if (formId != null) {
			// check form id dari form Pendaftaran web SSCN
			if (formId.substring(0, formId.length() - 1)
					.equals("3206378601185")) {
				try {
					// DtPendaftaran pendaftaran = registrasiService
					// .getPendaftaranById(request.getParameter("idRegistrasi"));
					HttpSession session = request.getSession();
					TabelPendaftar pendaftar = null;
					if (session.getAttribute("userLogin") != null) {
						pendaftar = (TabelPendaftar) session
								.getAttribute("userLogin");
					}
					List<DtPendaftaran> listPendafatans = registrasiService
							.getPendaftaranByUserId(pendaftar);

					if (listPendafatans == null || listPendafatans.size() == 0) {
						cetakRegistrasiGagal(response);
					} else {
						// generate pdf :)
						try {
							DtPendaftaran pendaftaran = listPendafatans.get(0);
							Map<String, Object> mapParamater = generateParameterToReport(pendaftaran);

							String baseDir = getBaseDirectory(request);
							String fileName = baseDir
									+ GeneralReportUtil.getRptRegistrasi2014();

							List<DtPersyaratan> listPersyaratans = persyaratanService
									.findByProperty(
											"refInstansi.kode",
											pendaftaran.getFormasi()
													.getRefInstansi().getKode(),
											null, null);
							Object[] arrResult = listPersyaratans.toArray();

							InputStream logo = loadDefaultLogo(request);
							mapParamater.put("LOGO", logo);

							this.generalPDFReports(arrResult, request,
									response, mapParamater, fileName);
						} catch (Exception ex) {
							ex.printStackTrace();
							cetakRegistrasiGagal(response);
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					cetakRegistrasiGagal(response);
				}
			} else {
				cetakNotAksesRegistrasi(response);
			}
		} else {
			cetakNotAksesRegistrasi(response);
		}

	}

	private void cetakRegistrasiGagal(HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html");
			out = response.getWriter();
			out.println("<HTML><HEAD><TITLE>SSCN</TITLE>"
					+ "</HEAD><BODY>Maaf proses cetak registrasi gagal. Klik <a href='"
					+ Constanta.URL_WEB_SSCN
					+ "'>link ini </a> untuk kembali</BODY></HTML>");

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	private void cetakNotAksesRegistrasi(HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html");
			out = response.getWriter();
			out.println("<HTML><HEAD><TITLE>SSCN</TITLE>"
					+ "</HEAD><BODY>Maaf anda tidak dapat mengakses halaman ini. Klik <a href='"
					+ Constanta.URL_WEB_SSCN
					+ "'>link ini </a> untuk kembali</BODY></HTML>");
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
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
		mapParamater.put("NO_REGISTRASI", pendaftaran.getNoRegister());
		mapParamater.put("BARCODE", pendaftaran.getNoRegister());
		mapParamater.put("NAMA", pendaftaran.getNama());
		String tempatLahir = pendaftaran.getTmpLahir();
		SimpleDateFormat formatDateTglLahir = new SimpleDateFormat("dd-MM-yyyy");
		String tglLahir = formatDateTglLahir.format(pendaftaran.getTglLahir());
		mapParamater.put("TTL", tempatLahir + " / " + tglLahir);
		mapParamater.put("NIK", pendaftaran.getNoNik());
		mapParamater.put("UNIVERSITAS", pendaftaran.getLembaga());
		String pendidikan = refPendidikanDao
				.findByProperty("kode", pendaftaran.getPendidikan(), null)
				.get(0).getNama();
		mapParamater.put("PENDIDIKAN", pendidikan);
		mapParamater.put("NO_IJAZAH", pendaftaran.getNoIjazah());
		mapParamater.put("AKREDITAS", pendaftaran.getAkreditasi());
		
		String lokasiTest="";
		if(!pendaftaran.getLokasiTest().equalsIgnoreCase("")){
			lokasiTest=refLokasiTestDao.findByProperty("kode", pendaftaran.getLokasiTest(), null).get(0).getNama();			
		}
		mapParamater.put("LOKASI_TEST", lokasiTest);

		mapParamater.put("INSTANSI", pendaftaran.getFormasi().getRefInstansi()
				.getNama());
		Locale id = new Locale("in", "ID");

		// JABATAN1
		SimpleDateFormat formatDateTglDaftar = new SimpleDateFormat(
				"dd-MM-yyyy HH:mm:ss", id);
		String tglDaftar1 = formatDateTglDaftar.format(pendaftaran
				.getTglCreated());
		mapParamater.put("JABATAN1", pendaftaran.getFormasi().getRefJabatan()
				.getNama()
				+ " (" + tglDaftar1 + ") ");
		mapParamater.put("JABATAN2", "");
		mapParamater.put("JABATAN3", "");
		if (pendaftaran.getFormasi2() != null) {
			// JABATAN2
			String tglDaftar2 = formatDateTglDaftar.format(pendaftaran
					.getTglCreated());
			mapParamater.put("JABATAN2", pendaftaran.getFormasi2()
					.getRefJabatan().getNama()
					+ " (" + tglDaftar2 + ") ");
		}
		if (pendaftaran.getFormasi3() != null
				&& pendaftaran.getFormasi2() == null) {
			// JABATAN3
			String tglDaftar3 = formatDateTglDaftar.format(pendaftaran
					.getTglCreated());
			mapParamater.put("JABATAN2", pendaftaran.getFormasi3()
					.getRefJabatan().getNama()
					+ " (" + tglDaftar3 + ") ");
		}
		if (pendaftaran.getFormasi3() != null
				&& pendaftaran.getFormasi2() != null) {
			// JABATAN3
			String tglDaftar3 = formatDateTglDaftar.format(pendaftaran
					.getTglCreated());
			mapParamater.put("JABATAN3", pendaftaran.getFormasi3()
					.getRefJabatan().getNama()
					+ " (" + tglDaftar3 + ") ");
		}

		return mapParamater;
	}

}
