package id.go.bkn.sscn.controller;

import id.go.bkn.sscn.core.persistence.tools.QueryOrder;
import id.go.bkn.sscn.persistence.entities.DtUser;
import id.go.bkn.sscn.persistence.entities.RefInstansi;
import id.go.bkn.sscn.persistence.entities.RefLokasi;
import id.go.bkn.sscn.services.LokasiService;
import id.go.bkn.sscn.util.json.StandardJsonMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LokasiController {

	@Inject
	private LokasiService lokasiService;
	
	@RequestMapping(value = "/lokasi.do", method = RequestMethod.GET)
	public String index(ModelMap model, HttpSession session, HttpServletRequest request) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			model.addAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}
		
		int indexAndCount[] = new int[2]; 
		int numRow = 10;		
		indexAndCount[0] = 1;
		String index = request.getParameter("activePage");
		if (index != null && !index.contentEquals("")){
			indexAndCount[0] = Integer.parseInt(index);			
		} 
		indexAndCount[0] = (indexAndCount[0] - 1) * numRow;			
		indexAndCount[1] = numRow;
		
		List<QueryOrder> orders = new ArrayList<QueryOrder>();
		orders.add(new QueryOrder("refLokasi.kode"));
		orders.add(new QueryOrder("refJabatan.nama"));
		
		List<RefLokasi> lokasis = lokasiService.findAllLokasiByInstansi(user.getRefInstansi().getKode(), indexAndCount);
		Integer count = lokasiService.countFindAllLokasiByInstansi(user.getRefInstansi().getKode());
		
		
		int numPage = (int) Math.ceil((double)count/indexAndCount[1]);		
		int activePage = (int) Math.ceil((double)(indexAndCount[0] + 1)/ indexAndCount[1]);
		int part2;
		if ((activePage * indexAndCount[1]) >= count){
			part2 = count;
		} else {
			part2 = activePage * indexAndCount[1];
		}		
		
		model.addAttribute("count",count);
		model.addAttribute("part2", part2);
		model.addAttribute("numpage",numPage);
		model.addAttribute("indexAndCount", indexAndCount);
		model.addAttribute("activePage", activePage);
		model.addAttribute("lokasis", lokasis);

		return "lokasimanagement";
	}

	@RequestMapping(value = "/lokasi.do", method = RequestMethod.POST)
	public String indexPost(ModelMap model, HttpSession session, HttpServletRequest request) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			model.addAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}
		
		int indexAndCount[] = new int[2]; 
		int numRow = 10;		
		indexAndCount[0] = 1;
		String index = request.getParameter("activePage");
		if (index != null && !index.contentEquals("")){
			indexAndCount[0] = Integer.parseInt(index);			
		} 
		indexAndCount[0] = (indexAndCount[0] - 1) * numRow;			
		indexAndCount[1] = numRow;
		
		List<QueryOrder> orders = new ArrayList<QueryOrder>();
		orders.add(new QueryOrder("refLokasi.kode"));
		orders.add(new QueryOrder("refJabatan.nama"));
		
		List<RefLokasi> lokasis = lokasiService.findAllLokasiByInstansi(user.getRefInstansi().getKode(), indexAndCount);
		Integer count = lokasiService.countFindAllLokasiByInstansi(user.getRefInstansi().getKode());
		
		
		int numPage = (int) Math.ceil((double)count/indexAndCount[1]);		
		int activePage = (int) Math.ceil((double)(indexAndCount[0] + 1)/ indexAndCount[1]);
		int part2;
		if ((activePage * indexAndCount[1]) >= count){
			part2 = count;
		} else {
			part2 = activePage * indexAndCount[1];
		}		
		
		model.addAttribute("count",count);
		model.addAttribute("part2", part2);
		model.addAttribute("numpage",numPage);
		model.addAttribute("indexAndCount", indexAndCount);
		model.addAttribute("activePage", activePage);
		model.addAttribute("lokasis", lokasis);

		return "lokasimanagement";
	}

	@RequestMapping(value = "/findLokasiLikeByName.do", method = RequestMethod.GET)
	@ResponseBody
	public String findInstansiLikeByName(
			@RequestParam("callback") String callBack,
			@RequestParam("name_startsWith") String name , HttpSession session) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		
		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Session habis");
			return objectMapper.writeValueAsString(new JSONPObject(callBack,
					res));
		}
		
		List<RefLokasi> lokasis = lokasiService.findLokasiByLikeNameInstansi(name, userLogin.getRefInstansi().getKode());
		for(int i=0;i<lokasis.size();i++){
			lokasis.get(i).setRefInstansi(null);
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lokasis", lokasis);

		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				resultMap));
	}
	
	@RequestMapping(value = "/lokasiSave.do", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage save(@RequestParam("kode") String kode,
			@RequestParam("nama") String name,
			@RequestParam("instansi") String instansiKd, HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Save Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		RefLokasi lokasi = null;
		try {
			lokasi = lokasiService.save(kode, name, instansiKd);
			if (lokasi != null) {
				RefInstansi pInstansi = new RefInstansi();
				pInstansi.setKode(lokasi.getRefInstansi().getKode());
				pInstansi.setNama(lokasi.getRefInstansi().getNama());
				lokasi.setRefInstansi(pInstansi);
				res = new StandardJsonMessage(1, lokasi, null, "Save Success");
			} else {
				res = new StandardJsonMessage(0, null, null, "Save Gagal");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Save Gagal");
		}
		return res;
	}

	@RequestMapping(value = "/lokasiUpdate.do", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage update(@RequestParam("kode") String kode,
			@RequestParam("nama") String name, HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Update Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		RefLokasi lokasi = null;
		try {
			lokasi = lokasiService.update(kode, name);
			if (lokasi != null) {			
				RefInstansi pInstansi = new RefInstansi();
				pInstansi.setKode(lokasi.getRefInstansi().getKode());
				pInstansi.setNama(lokasi.getRefInstansi().getNama());
				lokasi.setRefInstansi(pInstansi);
				res = new StandardJsonMessage(1, lokasi, null, "Update Success");
			} else {
				res = new StandardJsonMessage(0, null, null, "Update Gagal");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Update Gagal");
		}
		return res;
	}

	@RequestMapping(value = "/lokasiDelete.do", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage delete(@RequestParam("kode") String kode,
			HttpSession session) throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Delete Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		RefLokasi lokasi = null;
		try {
			lokasi = lokasiService.delete(kode);
			if (lokasi != null) {
				RefInstansi temp = new RefInstansi();
				temp.setKode(lokasi.getRefInstansi().getKode());
				temp.setNama(lokasi.getRefInstansi().getNama());
				lokasi.setRefInstansi(temp);
				res = new StandardJsonMessage(1, lokasi, null, "Delete Success");
			} else {
				res = new StandardJsonMessage(0, null, null, "Delete Gagal");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Delete Gagal");
		}
		return res;
	}

	@RequestMapping(value = "/getLokasi.do", method = RequestMethod.GET)
	@ResponseBody
	public StandardJsonMessage getLokasi(@RequestParam("kode") String kode,
			HttpSession session) throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Get Lokasi Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		RefLokasi lokasi = null;
		try {
			lokasi = lokasiService.findLokasiById(kode);
			RefInstansi pInstansi = new RefInstansi();
			pInstansi.setKode(lokasi.getRefInstansi().getKode());
			pInstansi.setNama(lokasi.getRefInstansi().getNama());
			lokasi.setRefInstansi(pInstansi);
			res = new StandardJsonMessage(1, lokasi, null, "Get Lokasi Success");
		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Get Lokasi Gagal");
		}

		return res;
	}

}
