package id.go.bkn.sscn.controller;

import id.go.bkn.sscn.persistence.entities.DtUser;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {

	@RequestMapping(value = "/dashboard.do", method = RequestMethod.GET)
	public String index(ModelMap map, ModelMap model, HttpSession session) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			// if(session.getAttribute("pesan")!=null){
			// session.setAttribute("pesan",
			// "Session habis silahkan login kembali");
			map.addAttribute("pesan", "Session habis silahkan login kembali");
			// }
			return "login";
		}

		model.addAttribute("username", user.getNama()); // ?
		return "dashboard";
	}

}
