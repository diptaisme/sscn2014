package id.go.bkn.sscn.servlet;

import id.go.bkn.sscn.manager.Constanta;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmailServlet
 * Email untuk send contact us
 */

public class EmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String USERNAME = "sscn.adm2013@gmail.com";
	private final String PASSWORD = "sscnServer";
	private final String SUBJECT = "SSCN Contact Us";
	private final String FROM = "sscn.adm2013@gmail.com";
	private final String TO = "satgas.cpns2013@bkn.go.id";

	/**
	 * Default constructor.
	 */
	public EmailServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		super.init();
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
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(USERNAME, PASSWORD);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(TO));
			message.setSubject(SUBJECT);
			String nama = request.getParameter("nama");
			String telpon = request.getParameter("telpon");
			String email = request.getParameter("email");
			String pesan = request.getParameter("pesan");			
			
			message.setText("Dear Administrator," + "\n\n Nama : "
					+ nama + "\n\n Telpon : "
					+ telpon + "\n\n Email : "
					+ email + "\n\n Pesan : "
					+ pesan);

			Transport.send(message);

			// printPesan(response);
			response.sendRedirect(Constanta.URL_WEB_SSCN);

		} catch (MessagingException e) {
			e.printStackTrace();
			printPesanGagal(response);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	private void printPesanGagal(HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<HTML><HEAD><TITLE>SSCN</TITLE>"
					+ "</HEAD><BODY>Maaf proses pengiriman pesan gagal. Klik <a href='"
					+ Constanta.URL_WEB_SSCN
					+ "'>link ini </a> untuk kembali</BODY></HTML>");
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
