package id.go.bkn.sscn.servlet.reportcommand;

import id.go.bkn.sscn.core.report.command.ReportCommand;
import id.go.bkn.sscn.dao.DtPengumumanDao;
import id.go.bkn.sscn.dao.RefInstansiDao;
import id.go.bkn.sscn.manager.Constanta;
import id.go.bkn.sscn.persistence.entities.DtPengumuman;
import id.go.bkn.sscn.persistence.entities.RefInstansi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * @author efraim
 * 
 */
@Component(value = "pengumumanReportCommand")
public class PengumumanReportCommand extends ReportCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9108544678866277629L;

	@Inject
	private DtPengumumanDao dtPengumumanDao;

	@Inject
	private RefInstansiDao refInstansiDao;

	@Override
	protected byte[] generateXls(Object[] pMyData, Map<String, Object> myMap)
	        throws IOException {
		// NOP
		return null;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException {
		String instansi = request.getParameter("instansi");
		List<DtPengumuman> pengumumans = dtPengumumanDao.findByProperty(
		        "refInstansi.kode", instansi, null);
		if (pengumumans.size() > 0) {
			DtPengumuman thePengumuman = pengumumans.get(0);
			response.setHeader("Content-disposition",
			        "inline; filename=PengumumuanPendaftaran.pdf");
			response.setContentType("application/pdf");
			response.setContentLength(thePengumuman.getBerita().length);
			ServletOutputStream servletOutputStream = null;
			try {
				servletOutputStream = response.getOutputStream();
				servletOutputStream.write(thePengumuman.getBerita());
				servletOutputStream.flush();
				servletOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();		
			}				
			
		} else {
			RefInstansi refInstansi = refInstansiDao.findById(instansi);
			response.setContentType("text/html");
			PrintWriter out = null;
			if (refInstansi != null) {
				String namaInstansi = refInstansi.getNama();
				try {
					out = response.getWriter();
					out.println("<HTML><HEAD><TITLE>Laporan Pengumuman</TITLE>"
					        + "</HEAD><BODY>Maaf, Laporan Pengumuman Belum Tersedia.<br>Instansi Terpilih&nbsp;"
					        + namaInstansi
					        + "<hr size=\"1\">" +
					        "Klik <a href='"+ Constanta.URL_WEB_SSCN+ "'>link ini </a> untuk kembali"+
					        "<hr size=\"1\">Sistem Seleksi CPNS 2013, Badan Kepegawaian Negara</BODY></HTML>");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
					
				} finally {
					if (out != null){
						out.close();
					}					
				}
			} else {
				try {
					out = response.getWriter();
					out.println("<HTML><HEAD><TITLE>Laporan Pengumuman</TITLE>"
					        + "</HEAD><BODY>Maaf, Silahkan memilih instansi<hr size=\"1\">" +
					        "Klik <a href='"+ Constanta.URL_WEB_SSCN+ "'>link ini </a> untuk kembali"+
					        "<hr size=\"1\">Sistem Seleksi CPNS 2013, Badan Kepegawaian Negara</BODY></HTML>");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (out != null){
						out.close();
					}					
				}
			}
		}
	}
}
