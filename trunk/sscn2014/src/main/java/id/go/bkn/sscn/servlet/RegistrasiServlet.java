package id.go.bkn.sscn.servlet;

import id.go.bkn.sscn.manager.Constanta;
import id.go.bkn.sscn.persistence.entities.DtPendaftaran;
import id.go.bkn.sscn.persistence.entities.RefInstansi;
import id.go.bkn.sscn.persistence.entities.TabelPendaftar;
import id.go.bkn.sscn.services.RegistrasiService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.hibernate.Hibernate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class RegistrasiServlet
 */

public class RegistrasiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private RegistrasiService registrasiService;

	/**
	 * Default constructor.
	 */
	public RegistrasiServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		super.init();
		WebApplicationContext springContext = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
		registrasiService = (RegistrasiService) springContext
				.getBean("RegistrasiService");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("formID") != null) {
			// check form id dari form Pendaftaran web SSCN
			if (request.getParameter("formID").equals("32063786011852")) {
				List<DtPendaftaran> pendaftarans = null;
				TabelPendaftar pendaftar = (TabelPendaftar) request
						.getSession().getAttribute("userLogin");
				int jumlahDaftarAwal = pendaftar.getJumlahDaftar();
				try {
					//
					String remoteAddr = request.getRemoteAddr();
					ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
					reCaptcha
							.setPrivateKey("6LdlHOsSAAAAACe2WYaGCjU2sc95EZqCI9wLcLXY");

					String challenge = request
							.getParameter("recaptcha_challenge_field");
					String uresponse = request
							.getParameter("recaptcha_response_field");
					ReCaptchaResponse reCaptchaResponse = reCaptcha
							.checkAnswer(remoteAddr, challenge, uresponse);

					if (!reCaptchaResponse.isValid()) {
						cetakRegistrasiGagalCaptchaNotValid(response);
					} else {
						RefInstansi instansi = registrasiService.getInstansibyId(pendaftar.getRefInstansi().getKode());						
						if (!instansi.getStatus().equalsIgnoreCase("1")) { // pake
																			// flag
																			// atau
																			// status?
							cetakRegistrasiGagal(response, instansi.getNama());
						} else {
							// cek jumlah daftarnya user
							if (pendaftar.getJumlahDaftar() >= 0
									&& pendaftar.getJumlahDaftar() < 3) {
								pendaftarans = registrasiService
										.insertPendaftarans(request, pendaftar);
							}
							if (pendaftarans == null) {
								cetakRegistrasiGagal(response);
							} else {
								// update jumlah_daftar field di tabel pendaftar
								int jumlahDaftar = pendaftar.getJumlahDaftar()
										+ pendaftarans.size();
								if (jumlahDaftar <= 3) {
									pendaftar.setJumlahDaftar(jumlahDaftar);
									registrasiService
											.updatePendaftar(pendaftar);
									// redirect ke cetak registrasi page
									try {
										response.sendRedirect(getParamUrlCetakPendaftaran(pendaftarans));
									} catch (Exception ex) {
										ex.printStackTrace();
										registrasiService
												.deletePendaftarans(pendaftarans); // rollback
																					// pendaftaran
																					// manually
										pendaftar
												.setJumlahDaftar(jumlahDaftarAwal);
										registrasiService
												.updatePendaftar(pendaftar);
										cetakRegistrasiGagal(response);
									}
								} else {
									registrasiService
											.deletePendaftarans(pendaftarans); // rollback
																				// pendaftaran
																				// manually
									pendaftar.setJumlahDaftar(jumlahDaftarAwal);
									registrasiService
											.updatePendaftar(pendaftar);
									cetakRegistrasiMaksimalJumlahPendaftaran(response);
								}
							}

						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					registrasiService.deletePendaftarans(pendaftarans); // rollback
																		// pendaftaran
																		// manually
					pendaftar.setJumlahDaftar(jumlahDaftarAwal);
					registrasiService.updatePendaftar(pendaftar);
					cetakRegistrasiGagal(response);
				}
			} else {
				cetakNotAksesRegistrasi(response);
			}
		} else {
			cetakNotAksesRegistrasi(response);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
	
	private void cetakRegistrasiGagal(HttpServletResponse response, String instansiNama) {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<HTML><HEAD><TITLE>SSCN</TITLE>"
					+ "</HEAD><BODY>Maaf proses registrasi gagal. Instansi "+instansiNama+" telah ditutup. Klik <a href='"
					+ Constanta.URL_WEB_SSCN
					+ "'>link ini </a> untuk kembali</BODY></HTML>");
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void cetakRegistrasiGagal(HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<HTML><HEAD><TITLE>SSCN</TITLE>"
					+ "</HEAD><BODY>Maaf proses registrasi gagal. Klik <a href='"
					+ Constanta.URL_WEB_SSCN
					+ "'>link ini </a> untuk kembali</BODY></HTML>");
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void cetakNotAksesRegistrasi(HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<HTML><HEAD><TITLE>SSCN</TITLE>"
					+ "</HEAD><BODY>Maaf anda tidak dapat mengakses halaman ini. Klik <a href='"
					+ Constanta.URL_WEB_SSCN
					+ "'>link ini </a> untuk kembali</BODY></HTML>");
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void cetakRegistrasiGagalCaptchaNotValid(
			HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<HTML><HEAD><TITLE>SSCN</TITLE>"
					+ "</HEAD><BODY>Maaf proses registrasi gagal, silahkan isi catpcha dengan benar. Klik <a href='"
					+ Constanta.URL_WEB_SSCN
					+ "'>link ini </a> untuk kembali</BODY></HTML>");
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void cetakRegistrasiMaksimalJumlahPendaftaran(
			HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<HTML><HEAD><TITLE>SSCN</TITLE>"
					+ "</HEAD><BODY>Maaf proses registrasi gagal, Anda telah mencapai jumlah daftar sebanyak 3 kali. Klik <a href='"
					+ Constanta.URL_WEB_SSCN
					+ "'>link ini </a> untuk kembali</BODY></HTML>");
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// getParamUrlCetakPendaftaran
	private String getParamUrlCetakPendaftaran(List<DtPendaftaran> pendaftarans) {
		String url = Constanta.URL_WEB_SSCN_CETAK_REGISTRASI;
		int i = 0;
		for (DtPendaftaran pendaftaran : pendaftarans) {
			++i;
			url += "idRegistrasi" + i + "=" + pendaftaran.getId() + "&";
		}
		return url;
	}

}
