package id.go.bkn.sscn.servlet;

import id.go.bkn.sscn.persistence.entities.DtHasil;
import id.go.bkn.sscn.persistence.entities.DtPeserta;
import id.go.bkn.sscn.services.HasilService;
import id.go.bkn.sscn.util.json.HasilJsonMessage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class HasilServlet
 */

public class HasilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	
		
	@Inject
	private HasilService hasilService;
	
	/**
	 * Default constructor.
	 */
	public HasilServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		super.init();
		WebApplicationContext springContext = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
		hasilService = (HasilService) springContext.getBean("HasilService");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		// Get the printwriter object from response to write the required json object to the output stream      
		PrintWriter out = response.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
		      // display to console
		      out.println(objectMapper.writeValueAsString(new DtHasil("123456789")));
		    } catch (JsonGenerationException e) {
		      e.printStackTrace();
		    } catch (JsonMappingException e) {
		      e.printStackTrace();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
//		response.setContentType("application/json");
//		// Get the printwriter object from response to write the required json object to the output stream      
//		PrintWriter out = response.getWriter();
//		ObjectMapper objectMapper = new ObjectMapper();
////		objectMapper.writeValueAsString(new JSO)
//		out.print(objectMapper);
//		out.flush();
		HasilJsonMessage res = new HasilJsonMessage();
		if (request.getParameter("formID") != null) {
			// check form id dari form Pendaftaran web SSCN
			if (request.getParameter("formID").equals("32063786011852")) {
				try {
					String noPeserta = request.getParameter("noPeserta");
//					String tglLahir = request.getParameter("tglLahir");
					DtPeserta dtPeserta = hasilService.getDtPesertaByNoPeserta(noPeserta);
					
					if (dtPeserta != null){
//						tglLahir = request.getParameter("tglLahir");
						if (true){
							DtHasil hasil = hasilService.getDtHasil(dtPeserta);
							if (hasil != null){
								res.setHasil(hasil);
								res.setPeserta(dtPeserta);
								res.setResult(1);
								res.setMessage("SUCCESS");
							} else {
								// send error : E02 Hasil LJK Tidak Valid
								res.setResult(-1);
								res.setMessage("Hasil LJK Tidak Valid");
							}
						} else {
							// send error : Data input tidak valid
							res.setResult(-2);
							res.setMessage("Data input tidak valid");
						}
					} else {
						// send error : E01 Data Peserta Tidak Ditemukan
						res.setResult(-3);
						res.setMessage("Data Peserta Tidak Ditemukan");
					}
				} catch (NullPointerException ex){
					res.setResult(-4);
					res.setMessage(ex.getMessage());
				}
			} else {
				res.setResult(-5);
				res.setMessage("no post data");
			}
		}
		
		response.setContentType("application/json");
		// Get the printwriter object from response to write the required json object to the output stream      
		PrintWriter out = response.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
		      // display to console
		      out.println(objectMapper.writeValueAsString(res));
		    } catch (JsonGenerationException e) {
		      e.printStackTrace();
		    } catch (JsonMappingException e) {
		      e.printStackTrace();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		out.close();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
	
}
