package id.go.bkn.sscn.controller;

import id.go.bkn.sscn.persistence.entities.TabelPendaftar;
import id.go.bkn.sscn.services.AuthenticateService;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticateController {
	@Inject
	private AuthenticateService authenticateService;

	@RequestMapping(value = "/processLogin.do", method = RequestMethod.POST)
	public String login(ModelMap map, HttpSession session,
			@RequestParam("username") String username,
			@RequestParam("password") String password) {

		try {
			TabelPendaftar pendaftar = authenticateService.login(username,
					password);
			if (pendaftar != null) {
				if (session.getAttribute("userLogin") != null) {
					session.removeAttribute("userLogin");
				}
				session.setAttribute("userLogin", pendaftar);
				return "index";
			} else {
				map.addAttribute("pesan",
						"Login anda gagal, silahkan ulangi kembali");
				return "login";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.addAttribute("pesan",
					"Login anda gagal, silahkan ulangi kembali");
			return "login";
		}
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(ModelMap map, HttpSession session) {
		if (session.getAttribute("userLogin") != null) {
			session.removeAttribute("userLogin");
			// session.invalidate();
		}
		map.addAttribute("pesan", "Anda telah logout");
		return "login";
	}
}
