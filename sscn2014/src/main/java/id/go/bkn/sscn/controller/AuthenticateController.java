package id.go.bkn.sscn.controller;

import id.go.bkn.sscn.persistence.entities.TabelPendaftar;
import id.go.bkn.sscn.services.AuthenticateService;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticateController {
	private String PESAN = "PESAN";
	private String RESULT = "RESULT";

	@Inject
	private AuthenticateService authenticateService;

	@RequestMapping(value = "/process_login.html", method = RequestMethod.POST)
	public String login(ModelMap map, HttpSession session,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpServletRequest request) {
		// print IP address login action
		System.out.println("[LOGIN] request.getRemoteAddr()= "
				+ request.getRemoteAddr()+" - USERNAME = "+username);
		try {
			Map<String, Object> result = authenticateService.login(username,
					password);
			TabelPendaftar pendaftar = null;
			if (result.get(RESULT) != null) {
				pendaftar = (TabelPendaftar) result.get(RESULT);
			}

			if (pendaftar != null) {
				if (session.getAttribute("userLogin") != null) {
					session.removeAttribute("userLogin");
				}
				session.setAttribute("userLogin", pendaftar);
				return "index";
			} else {
				map.addAttribute("pesan", result.get(PESAN));
				return "login";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.addAttribute("pesan", "Terjadi Kesalahan: " + ex.getMessage());
			return "login";
		}
	}

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/logout.html", method = RequestMethod.GET)
	public String logout(ModelMap map, HttpSession session) {
		if (session.getAttribute("userLogin") != null) {
			session.removeAttribute("userLogin");
			// session.invalidate();
		}
		map.addAttribute("pesan", "Anda telah logout");
		return "login";
	}
}
