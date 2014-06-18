package id.go.bkn.sscn.servlets;

import id.go.bkn.sscn.constanta.Constanta;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ActionServlet
 */
@WebServlet("/ActionServlet")
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// controller untuk halaman
		String page = request.getParameter("page");
		if (page == null) {
			cetakNotSSCN(response);
		} else {
			page = "/WEB-INF/jsp/" + page + ".jsp";
			try {
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				cetakNotAvalaibleSSCN(response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private void cetakNotSSCN(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML><HEAD><TITLE>SSCN</TITLE>"
				+ "</HEAD><BODY>Maaf anda tidak dapat mengakses halaman ini. Klik <a href='"
				+ Constanta.URL_WEB_SSCN
				+ "'>link ini </a> untuk kembali</BODY></HTML>");
		out.close();
	}

	private void cetakNotAvalaibleSSCN(HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML><HEAD><TITLE>SSCN</TITLE>"
				+ "</HEAD><BODY>Maaf halaman yang anda akses tidak tersedia. Klik <a href='"
				+ Constanta.URL_WEB_SSCN
				+ "'>link ini </a> untuk kembali</BODY></HTML>");
		out.close();
	}

}