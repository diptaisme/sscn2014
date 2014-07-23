package id.go.bkn.sscn.controller;

import id.go.bkn.sscn.persistence.entities.DtPendaftaran;
import id.go.bkn.sscn.persistence.entities.MFormasi;
import id.go.bkn.sscn.persistence.entities.RefInstansi;
import id.go.bkn.sscn.persistence.entities.TabelPendaftar;
import id.go.bkn.sscn.services.RegistrasiService;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Class untuk action page 
 */


@Controller
public class ActionController {
	@Inject
	private RegistrasiService registrasiService;

	@RequestMapping(value = "/cetak.html", method = RequestMethod.GET)
	public String cetak(ModelMap map, ModelMap model, HttpSession session) {
		TabelPendaftar user = (TabelPendaftar) session
				.getAttribute("userLogin");
		if (user == null) {
			return "login";
		}
		if (user.getJumlahDaftar()==0) {
			return "halamanyangsalah";
		}
		List<DtPendaftaran> pendaftarans = registrasiService
				.getPendaftaransByUser(user.getId());		
		if(pendaftarans.size()>0){
			model.addAttribute("idRegistrasi", pendaftarans.get(0).getId());
		}				
		return "cetak";
	}

	@RequestMapping(value = "/informasi_umum.html", method = RequestMethod.GET)
	public String informasi_umum(ModelMap map, ModelMap model,
			HttpSession session) {
		return "informasi_umum";
	}
	
	@RequestMapping(value = "/alur.html", method = RequestMethod.GET)
	public String alur(ModelMap map, ModelMap model,
			HttpSession session) {
		return "alur";
	}
	
	@RequestMapping(value = "/calendar.html", method = RequestMethod.GET)
	public String calendar(ModelMap map, ModelMap model,
			HttpSession session) {
		return "calendar";
	}
	
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String index(ModelMap map, ModelMap model,
			HttpSession session) {
		return "index";
	}
	
	@RequestMapping(value = "/contacts.html", method = RequestMethod.GET)
	public String contacts(ModelMap map, ModelMap model,
			HttpSession session) {
		return "contacts";
	}
	
	@RequestMapping(value = "/daftar.html", method = RequestMethod.GET)
	public String daftar_new(ModelMap map, ModelMap model,
			HttpSession session) {
		TabelPendaftar user = (TabelPendaftar) session
				.getAttribute("userLogin");
		if (user == null) {
			return "login";
		}		
		if (user.getJumlahDaftar()>0) {
			return "halamanyangsalah";
		}
		RefInstansi instansi = registrasiService.getInstansibyId(user.getRefInstansi().getKode());
		if (!instansi.getStatus().equalsIgnoreCase("1")) { //pake flag atau status?
			model.addAttribute("namaInstansi",user.getRefInstansi().getNama());
			return "tutup_pendaftaran";
		}
		return "daftar";
	}
	
	@RequestMapping(value = "/peraturan.html", method = RequestMethod.GET)
	public String peraturan(ModelMap map, ModelMap model,
			HttpSession session) {
		return "peraturan";
	}
	
	//not used
	@RequestMapping(value = "/daftar_new1.html", method = RequestMethod.GET)
	public String daftar_new1(ModelMap map, ModelMap model,
			HttpSession session) {
		TabelPendaftar user = (TabelPendaftar) session
				.getAttribute("userLogin");
		if (user == null) {
			return "login";
		}		
		if (user.getJumlahDaftar()==3) {
			return "halamanyangsalah";
		}
		return "daftar_new1";
	}
	
	@RequestMapping(value = "/info_instansi.html", method = RequestMethod.GET)
	public String info_instansi() {
		return "info_instansi";
	}
	
	@RequestMapping(value = "/get_info_formasi.html", method = RequestMethod.POST)
	public String get_info_formasi(HttpServletRequest request, ModelMap model) {		
		MFormasi formasi = registrasiService
				.getFormasi(request.getParameter("instansiValue"), request.getParameter("lokasi_kerja"), request.getParameter("jabatan"));
		
		int countFormasiInPendaftaran = registrasiService.countFormasiInPendaftaran(formasi);
		
		model.addAttribute("countAvailableFormasi", formasi.getJumlah());
		model.addAttribute("countFormasiInPendaftaran", countFormasiInPendaftaran);
		
		return "info_instansi";
	}
	
}
