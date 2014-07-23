package id.go.bkn.sscn.controller;

import id.go.bkn.sscn.dao.DtPendaftaranDao;
import id.go.bkn.sscn.persistence.entities.DtPendaftaran;
import id.go.bkn.sscn.persistence.entities.DtPersyaratan;
import id.go.bkn.sscn.persistence.entities.DtUser;
import id.go.bkn.sscn.persistence.entities.DtVerifikasiNok;
import id.go.bkn.sscn.persistence.entities.RefPendidikan;
import id.go.bkn.sscn.services.PendidikanService;
import id.go.bkn.sscn.services.PersyaratanService;
import id.go.bkn.sscn.services.UserService;
import id.go.bkn.sscn.services.VerfikasiService;
import id.go.bkn.sscn.util.json.StandardJsonMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VerifikasiController {
	@Inject
	private UserService userService;

	@Inject
	private DtPendaftaranDao dtPendaftaranDao;

	@Inject
	private VerfikasiService verifikasiServices;
	
	@Inject
	private PendidikanService pendidikanServices;

	@Inject
	private PersyaratanService persyaratanServices;

	@RequestMapping(value = "/verifikasi.html", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model, HttpSession session) {
		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			request.setAttribute("pesan", "Session habis silahkan login kembali");
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
		
		DtUser user = userService.findByProperty("username", userLogin.getUsername(), null).get(0);
		List<DtPendaftaran> pendaftars = dtPendaftaranDao.findByInstansi(user.getRefInstansi(),
				indexAndCount);				
		int count = dtPendaftaranDao.countByInstansi(user.getRefInstansi());
		List<DtPersyaratan> persyaratans = persyaratanServices.findByProperty("refInstansi",
				user.getRefInstansi(), null, null);
		
		int numPage = (int) Math.ceil((double)count/indexAndCount[1]);		
		int activePage = (int) Math.ceil((double)(indexAndCount[0] + 1)/ indexAndCount[1]);
		int part2;
		if ((activePage * indexAndCount[1]) >= count){
			part2 = count;
		} else {
			part2 = activePage * indexAndCount[1];
		}		
		
		model.addAttribute("pendaftars", pendaftars);
		model.addAttribute("count",count);
		model.addAttribute("part2", part2);
		model.addAttribute("numpage",numPage);
		model.addAttribute("indexAndCount", indexAndCount);
		model.addAttribute("activePage", activePage);
		model.addAttribute("persyaratans", persyaratans);
		return "verifikasi";
	}
	
	@RequestMapping(value = "/verifikasi.html", method = RequestMethod.POST)
	public String indexPost(HttpServletRequest request, ModelMap model, HttpSession session) {
		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			request.setAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}
		
		DtUser user = userService.findByProperty("username", userLogin.getUsername(), null).get(0);
		
		int indexAndCount[] = new int[2]; 
		int numRow = 10;		
		indexAndCount[0] = 1;
		int count;
		
		String numRowPerPage = request.getParameter("numPage");
		if (numRowPerPage != null && !numRowPerPage.contentEquals("")){
			numRow = Integer.parseInt(numRowPerPage);
		}
		
		String index = request.getParameter("activePage");
		if (index != null && !index.contentEquals("")){
			indexAndCount[0] = Integer.parseInt(index);			
		} 
		indexAndCount[0] = (indexAndCount[0] - 1) * numRow;			
		indexAndCount[1] = numRow;
		
		String searchPar = request.getParameter("searchPar");
		String noReg = "";
		Boolean searchBy = false;
		if (searchPar != null && !searchPar.contentEquals("")){			
			noReg = request.getParameter("no_reg");
			
			if (noReg != null && !noReg.contentEquals("")){
				searchBy = true;
			}
			model.addAttribute("no_reg", noReg);
		}
		
		List<DtPendaftaran> pendaftars;
		if (searchBy){
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("noRegister", noReg);
			pendaftars = dtPendaftaranDao.findByInstansiAndMap(user.getRefInstansi(), maps, 
					indexAndCount);				
			count = dtPendaftaranDao.countByInstansiAndMap(user.getRefInstansi(), maps);
		} else {
			pendaftars = dtPendaftaranDao.findByInstansi(user.getRefInstansi(),
					indexAndCount);				
			count = dtPendaftaranDao.countByInstansi(user.getRefInstansi());
		}
		
		List<DtPersyaratan> persyaratans = persyaratanServices.findByProperty("refInstansi",
				user.getRefInstansi(), null, null);
		
		int numPage = (int) Math.ceil((double)count/indexAndCount[1]);		
		int activePage = (int) Math.ceil((double)(indexAndCount[0] + 1)/ indexAndCount[1]);
		int part2;
		if ((activePage * indexAndCount[1]) >= count){
			part2 = count;
		} else {
			part2 = activePage * indexAndCount[1];
		}		

		model.addAttribute("pendaftars", pendaftars);
		model.addAttribute("count",count);
		model.addAttribute("part2", part2);
		model.addAttribute("numpage",numPage);
		model.addAttribute("indexAndCount", indexAndCount);
		model.addAttribute("activePage", activePage);
		model.addAttribute("persyaratans", persyaratans);
		return "verifikasi";
	}

	@RequestMapping(value = "/verifikasiSave.html", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage save(HttpServletRequest request, HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null, "Save Gagal");
			return res;
		}
		
		
		String pendaftarId = request.getParameter("pendaftarId");
		
		try {
			DtUser user = userService.findByProperty("username", userLogin.getUsername(), null)
					.get(0);
			List<DtPersyaratan> persyaratans = persyaratanServices.findByProperty("refInstansi",
					user.getRefInstansi(), null);

			Iterator<DtPersyaratan> iterator = persyaratans.iterator();
			List<DtPersyaratan> gagalPersyaratan = new ArrayList<DtPersyaratan>();
			String[] persyaratanIds = new String[persyaratans.size()]; 			
			if (request.getParameterValues("persyaratanIds[]") != null){
				persyaratanIds = request.getParameterValues("persyaratanIds[]");
				int found = 0;
				while (iterator.hasNext()) {
					DtPersyaratan dtPersyaratan = iterator.next();
					found = 0;
					for (int i = 0; i < persyaratanIds.length; i++) {
						if (Integer.parseInt(persyaratanIds[i]) == dtPersyaratan.getId()) {
							found++;
						}
					}
					if (found == 0) {
						gagalPersyaratan.add(dtPersyaratan);
					}
				}
			} else {
				gagalPersyaratan.addAll(persyaratans);
			}
			
			DtPendaftaran pendaftar = dtPendaftaranDao.findUniqueByProperty("id",
					Long.parseLong(pendaftarId), null, null);

			List<DtVerifikasiNok> verNoks = new ArrayList<DtVerifikasiNok>();

			if (gagalPersyaratan.size() > 0) {
				for (int i = 0; i < gagalPersyaratan.size(); i++) {
					DtVerifikasiNok pverNok = new DtVerifikasiNok();
					pverNok.setPendaftar(pendaftar);
					pverNok.setPersyaratan(gagalPersyaratan.get(i));
					pverNok.setVerifikator(user);
					pverNok.setCreatedDate(new Date());
					verNoks.add(pverNok);
				}
			}
			pendaftar.setUserValidate(user.getUsername());

			try {
				Boolean resVer = verifikasiServices.simpanHasilVerifikasi(pendaftar, verNoks);

				if (resVer) {
					return (new StandardJsonMessage(1, null, null, "Save Success"));
				} else {
					return (new StandardJsonMessage(0, null, null, "Save Gagal"));
				}

			} catch (Exception e) {
				return (new StandardJsonMessage(0, null, null, "Save Gagal1 " + e.getMessage()));
			}
		} catch (Exception e) {
			return (new StandardJsonMessage(0, null, null, "Save Gagal2 " + e.getMessage()));
		}
	}
	
	@RequestMapping(value = "/getPendaftaran.html", method = RequestMethod.GET)
	@ResponseBody
	public StandardJsonMessage getUser(
			@RequestParam("id") String id, HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Get Pendaftaran Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		try {
			DtPendaftaran infoPendaftar = dtPendaftaranDao.findUniqueByProperty("id",
					Long.parseLong(id), null, null);
			List<DtVerifikasiNok> verNoks = null;
			if (infoPendaftar.getStatus().equalsIgnoreCase("0")){
				verNoks = verifikasiServices.findVerifikasiNoksByPendaftar(infoPendaftar);
			}
			
			List<DtVerifikasiNok> rverNoks = new ArrayList<DtVerifikasiNok>();
			if (verNoks != null){
				Iterator<DtVerifikasiNok> iter = verNoks.iterator();
				while (iter.hasNext()){
					DtVerifikasiNok temp = iter.next();
					temp.getPendaftar().setFormasi(null);
					temp.setPendaftar(null);
					temp.setVerifikator(null);
					DtPersyaratan temPersyaratan = new DtPersyaratan();
					temPersyaratan.setId(temp.getPersyaratan().getId());
					temPersyaratan.setRefInstansi(null);
					temPersyaratan.setSyarat(temp.getPersyaratan().getSyarat());
					temPersyaratan.setUrutan(temp.getPersyaratan().getUrutan());
					temPersyaratan.setUser(null);
					
					temp.setPersyaratan(temPersyaratan);
					rverNoks.add(temp);
				}
			} 
			Map<String, Object> mob = new HashMap<String, Object>();
			if (rverNoks.size() > 0){
				mob.put("verNoks", rverNoks);
			} else {
				mob = null;
			}
			String pddkn = "";
			if (infoPendaftar.getPendidikan() != null){
				RefPendidikan pddknTemp = pendidikanServices.findById(infoPendaftar.getPendidikan()); 
				pddkn = pddknTemp.getNama();
			}
			infoPendaftar.setPendidikan(pddkn);
			
			infoPendaftar.setFormasi(null);
			res = new StandardJsonMessage(1, infoPendaftar, mob, "Get Pendaftaran Success");
		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Get Pendaftaran Gagal " + ex.getMessage());
		}

		return res;
	}
	
	
}
