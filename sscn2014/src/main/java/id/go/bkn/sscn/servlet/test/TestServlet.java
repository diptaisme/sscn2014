package id.go.bkn.sscn.servlet.test;

import id.go.bkn.sscn.services.TestService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Component("TestServlet")
public class TestServlet extends HttpServlet {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5472782840801581612L;

	private TestService testService;
	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	@Override
	public void init() throws ServletException {
		WebApplicationContext springContext = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
		testService = (TestService) springContext.getBean("TestService");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doPost(req, resp);
		System.out.print("test saja");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// List<Pns> listPns = testService.getAllPns(null);
		// for (Pns pns : listPns) {
		// System.out.println("nip baru " + pns.getPnsNipbaru());
		//
		// }
		//Pns pns = testService.findPnsByNip("197909232006042028", true);

		//System.out.println("neww baru nama " + pns.getNama());
		

	}

}
