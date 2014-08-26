package id.go.bkn.sscn.servlet;

import id.go.bkn.sscn.manager.Constanta;
import id.go.bkn.sscn.manager.DBManager;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 * Servlet implementation class PrintServlet
 */

public class PrintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection = null;

	/**
	 * Default constructor.
	 */
	public PrintServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			connection = DBManager.getConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ex :" + ex.getMessage());
			connection = null;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get get get");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		byte[] byteStream;
		String instansi = request.getParameter("instansi");
		System.out.println("pengumuman instansi " + instansi);
		OutputStream outStream = null;
		try {
			Map<String, Object> mapParamater = new HashMap<String, Object>();
			mapParamater.put("INSTANSI_KODE", instansi);
			byteStream = JasperRunManager.runReportToPdf(getServletContext()
					.getRealPath(Constanta.PATH_REPORT_PENGUMUMAN_INSTANSI),
					mapParamater, connection);

			outStream = response.getOutputStream();
			response.setHeader("Content-Disposition",
					"attachment, filename=PengumumuanInstansi.pdf");
			response.setContentType("application/pdf");
			response.setContentLength(byteStream.length);
			outStream.write(byteStream, 0, byteStream.length);
			outStream.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (outStream != null) {
				outStream.close();
			}
		} finally {
			if (outStream != null) {
				outStream.close();
			}
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ex :" + ex.getMessage());
		}

	}

}
