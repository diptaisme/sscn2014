package id.go.bkn.sscn.controller;

import id.go.bkn.sscn.persistence.entities.DtUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DownloadDataController {
	@RequestMapping(value = "/downloadData.do", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model,
			HttpSession session) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			request.setAttribute("pesan",
					"Session habis silahkan login kembali");
			return "login";
		}
		return "downloadData";
	}

}
