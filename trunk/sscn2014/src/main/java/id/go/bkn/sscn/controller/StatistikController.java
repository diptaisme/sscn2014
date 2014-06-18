package id.go.bkn.sscn.controller;

import id.go.bkn.sscn.persistence.entities.DtUser;
import id.go.bkn.sscn.persistence.entities.view.StatInstansi;
import id.go.bkn.sscn.services.StatistikService;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StatistikController {
	@Inject
	private StatistikService statistikService;

	@RequestMapping(value = "/statistik.do", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model,
			HttpSession session) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			request.setAttribute("pesan",
					"Session habis silahkan login kembali");
			return "login";
		}
		/*
		 * model.addAttribute("statInstansis",
		 * statistikService.getStatistikPendaftaranInstansi());
		 */
		List<StatInstansi> resultInstansi = statistikService
				.getStatistikPendaftaranInstansi(user.getRefInstansi()
						.getKode());
		if(resultInstansi.size() > 0){
			model.addAttribute("jumlahPendaftar", resultInstansi.get(0).getJumlahPendaftar());
			model.addAttribute("jumlahLulus", resultInstansi.get(0).getJumlahLulus());
			model.addAttribute("jumlahTidakLulus", resultInstansi.get(0).getJumlahTidakLulus());
			int jumlahBelumVerifikasi = Integer.parseInt(resultInstansi.get(0).getJumlahPendaftar()) - Integer.parseInt(resultInstansi.get(0).getJumlahLulus())
					-Integer.parseInt(resultInstansi.get(0).getJumlahTidakLulus());			
			model.addAttribute("jumlahBelumVerifikasi", jumlahBelumVerifikasi);
		}
		model.addAttribute("statInstansi", resultInstansi);

		model.addAttribute("statJabatans", statistikService
				.getStatistikJabatanPendaftaranInstansi(user.getRefInstansi()
						.getKode()));
		return "statistik";
	}

}
