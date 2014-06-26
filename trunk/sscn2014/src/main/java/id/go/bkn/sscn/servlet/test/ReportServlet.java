package id.go.bkn.sscn.servlet.test;

import id.go.bkn.sscn.core.report.GeneralReportFactory;
import id.go.bkn.sscn.core.report.command.ReportCommand;
import id.go.bkn.sscn.core.report.exception.ReportException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ClassUtils;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Component("ReportServlet")
public class ReportServlet extends HttpServlet {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5472782840801581612L;
	/** The application context. */
	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext currentApplicationContext) {
		applicationContext = currentApplicationContext;
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	@Override
	public void init() throws ServletException {
		setApplicationContext(WebApplicationContextUtils
		        .getRequiredWebApplicationContext(this.getServletContext()));
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {		
		ReportCommand reportCommand = null;
		String reportType = request.getParameter("typeReport");
		// cek reportType untuk menghindari direct akses ke /ReportServlet
		if (reportType != null) {
			reportCommand = GeneralReportFactory.getReportCommand(reportType);
			Class<? extends ReportCommand>[] clazzez = ClassUtils
			        .toClass(new Object[] { reportCommand });
			Class<? extends ReportCommand> clazz = clazzez[0];
			reportCommand = (ReportCommand) applicationContext
			        .getAutowireCapableBeanFactory().createBean(clazz,
			                AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);
			if (reportCommand == null) {
				printException(response, reportType);
				return;
			}
			try {
				reportCommand.execute(request, response);
			} catch (ReportException e) {
				printException(response, e);
			}
		} else {
			printIllegalAkses(response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		//doPost(req, resp);
	}

	public void printIllegalAkses(HttpServletResponse response) throws IOException {
		ServletOutputStream servletOutputStream = response.getOutputStream();
		servletOutputStream.print("<html>");
		servletOutputStream.print("<header>");
		servletOutputStream.print("</header>");
		servletOutputStream.print("<body>");
		servletOutputStream.print("Error: Illegal Akses...");
		servletOutputStream.print("</body>");
		servletOutputStream.print("</html>");
		servletOutputStream.flush();
		servletOutputStream.close();
	}

	/**
	 * This method is used to print out any exception text to a blank web page.
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param reportType
	 *            the report type
	 * @return void
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void printException(HttpServletResponse response, String reportType)
	        throws IOException {
		ServletOutputStream servletOutputStream = response.getOutputStream();
		servletOutputStream.print("<html>");
		servletOutputStream.print("<header>");
		servletOutputStream.print("</header>");
		servletOutputStream.print("<body>");
		servletOutputStream.print("	Mohon maaf, Laporan " + reportType
		        + " tidak ditemukan!");
		servletOutputStream.print("</body>");
		servletOutputStream.print("</html>");
		servletOutputStream.flush();
		servletOutputStream.close();
	}

	/**
	 * This method is used to print out any exception text to a blank web page.
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param e
	 *            the exception being sent to the client
	 * @return void
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void printException(HttpServletResponse response, Exception e)
	        throws IOException {
		ServletOutputStream servletOutputStream = response.getOutputStream();
		servletOutputStream.print("<html>");
		servletOutputStream.print("<header>");
		servletOutputStream.print("</header>");
		servletOutputStream.print("<body>");
		servletOutputStream.print("<h1>" + e.getMessage() + "</h1>");
		servletOutputStream.print("</body>");
		servletOutputStream.print("</html>");
		servletOutputStream.flush();
		servletOutputStream.close();
	}
}
