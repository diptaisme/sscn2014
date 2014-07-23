package id.go.bkn.sscn.controller;

import id.go.bkn.sscn.core.persistence.tools.QueryOrder;
import id.go.bkn.sscn.persistence.entities.DtUser;
import id.go.bkn.sscn.persistence.entities.RefInstansi;
import id.go.bkn.sscn.services.UserService;
import id.go.bkn.sscn.util.json.StandardJsonMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
public class UserController {
	@Inject
	private UserService userService;

	@RequestMapping(value = "/user.html", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model,
			HttpSession session) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			request.setAttribute("pesan",
					"Session habis silahkan login kembali");
			return "login";
		}

		int indexAndCount[] = new int[2];
		int numRow = 10;
		indexAndCount[0] = 1;
		String index = request.getParameter("activePage");
		if (index != null && !index.contentEquals("")) {
			indexAndCount[0] = Integer.parseInt(index);
		}
		indexAndCount[0] = (indexAndCount[0] - 1) * numRow;
		indexAndCount[1] = numRow;

		List<QueryOrder> orders = new ArrayList<QueryOrder>();
		orders.add(new QueryOrder("refLokasi.kode"));
		orders.add(new QueryOrder("refJabatan.nama"));

		List<DtUser> users = null;
		Integer count;
		if (user.getKewenangan().equals("1")) {
			users = userService.getAllUser(indexAndCount);
			count = userService.countAllUser();
		} else {
			users = userService.getAllUserByInstansi(user.getRefInstansi()
					.getKode(), indexAndCount);
			count = userService.countAllUserByInstansi(user.getRefInstansi()
					.getKode());
		}

		int numPage = (int) Math.ceil((double) count / indexAndCount[1]);
		int activePage = (int) Math.ceil((double) (indexAndCount[0] + 1)
				/ indexAndCount[1]);
		int part2;
		if ((activePage * indexAndCount[1]) >= count) {
			part2 = count;
		} else {
			part2 = activePage * indexAndCount[1];
		}

		model.addAttribute("count", count);
		model.addAttribute("part2", part2);
		model.addAttribute("numpage", numPage);
		model.addAttribute("indexAndCount", indexAndCount);
		model.addAttribute("activePage", activePage);
		model.addAttribute("users", users);

		return "usermanagement";
	}
	
	@RequestMapping(value = "/user.html", method = RequestMethod.POST)
	public String indexPost(HttpServletRequest request, ModelMap model,
			HttpSession session) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			request.setAttribute("pesan",
					"Session habis silahkan login kembali");
			return "login";
		}

		int indexAndCount[] = new int[2];
		int numRow = 10;
		indexAndCount[0] = 1;
		String index = request.getParameter("activePage");
		if (index != null && !index.contentEquals("")) {
			indexAndCount[0] = Integer.parseInt(index);
		}
		indexAndCount[0] = (indexAndCount[0] - 1) * numRow;
		indexAndCount[1] = numRow;

		List<QueryOrder> orders = new ArrayList<QueryOrder>();
		orders.add(new QueryOrder("refLokasi.kode"));
		orders.add(new QueryOrder("refJabatan.nama"));

		List<DtUser> users = null;
		Integer count;
		if (user.getKewenangan().equals("1")) {
			users = userService.getAllUser(indexAndCount);
			count = userService.countAllUser();
		} else {
			users = userService.getAllUserByInstansi(user.getRefInstansi()
					.getKode(), indexAndCount);
			count = userService.countAllUserByInstansi(user.getRefInstansi()
					.getKode());
		}

		int numPage = (int) Math.ceil((double) count / indexAndCount[1]);
		int activePage = (int) Math.ceil((double) (indexAndCount[0] + 1)
				/ indexAndCount[1]);
		int part2;
		if ((activePage * indexAndCount[1]) >= count) {
			part2 = count;
		} else {
			part2 = activePage * indexAndCount[1];
		}

		model.addAttribute("count", count);
		model.addAttribute("part2", part2);
		model.addAttribute("numpage", numPage);
		model.addAttribute("indexAndCount", indexAndCount);
		model.addAttribute("activePage", activePage);
		model.addAttribute("users", users);

		return "usermanagement";
	}

	@RequestMapping(value = "/userSave.html", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage save(@RequestParam("username") String username,
			@RequestParam("nip") String nip, @RequestParam("name") String name,
			@RequestParam("password") String password,
			@RequestParam("instansi") String instansiKd,
			@RequestParam("profile") String profile, HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Save Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		DtUser user = null;
		try {
			user = new DtUser();
			user.setNip(nip);
			user.setNama(name);
			user.setUsername(username);
			user.setPassword(password);
			user.setKewenangan(profile);
			user.setTglCreated(new Date());
			user.setTglUpdated(new Date());
			user.setNipAdmin(userLogin.getNip());

			if (userService.addUser(user, instansiKd)) {
				RefInstansi pInstansi = new RefInstansi();
				pInstansi.setKode(user.getRefInstansi().getKode());
				pInstansi.setNama(user.getRefInstansi().getNama());
				user.setRefInstansi(pInstansi);
				res = new StandardJsonMessage(1, user, null, "Save Success");
			} else {
				res = new StandardJsonMessage(0, null, null, "Save Gagal");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Save Gagal "
					+ "Periksa kembali data user. Username mungkin telah terpakai.");
		}
		return res;
	}

	// roberto
	@RequestMapping(value = "/userUpdate.html", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage update(
			@RequestParam("username") String username,
			@RequestParam("nip") String nip, @RequestParam("name") String name,
			@RequestParam("instansi") String instansiKd,
			@RequestParam("profile") String profile, HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Update Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		DtUser user = null;
		try {
			user = userService.findByProperty("username", username, null)
					.get(0);

			user.setNip(nip);
			user.setNama(name);
			user.setUsername(username);
			// user.setPassword(password);
			user.setKewenangan(profile);
			user.setTglCreated(new Date());
			user.setTglUpdated(new Date());
			user.setNipAdmin(userLogin.getNip());

			if (userService.editUser(user, instansiKd)) {
				RefInstansi temp = new RefInstansi();
				temp.setKode(user.getRefInstansi().getKode());
				temp.setNama(user.getRefInstansi().getNama());
				user.setRefInstansi(temp);
				res = new StandardJsonMessage(1, user, null, "Update Success");
			} else {
				res = new StandardJsonMessage(0, null, null, "Update Gagal");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Update Gagal "
					+ ex.getMessage());
		}
		return res;
	}

	@RequestMapping(value = "/userDelete.html", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage delete(
			@RequestParam("username") String username, HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Delete Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		DtUser user = null;
		try {
			List<DtUser> listUser = userService.findByProperty("username",
					username, null);
			if (listUser.size() == 0) {
				res = new StandardJsonMessage(0, null, null, "Delete Gagal");
			} else {
				user = listUser.get(0);

				if (userService.deleteUserByUsername(user.getUsername())) {
					RefInstansi temp = new RefInstansi();
					temp.setKode(user.getRefInstansi().getKode());
					temp.setNama(user.getRefInstansi().getNama());
					user.setRefInstansi(temp);
					res = new StandardJsonMessage(1, user, null,
							"Delete Success");
				} else {
					res = new StandardJsonMessage(0, null, null, "Delete Gagal");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Delete Gagal");
		}
		return res;
	}

	@RequestMapping(value = "/getUser.do", method = RequestMethod.GET)
	@ResponseBody
	public StandardJsonMessage getUser(
			@RequestParam("username") String username, HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Get User Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		DtUser dtUser = null;
		try {
			dtUser = userService.findByProperty("username", username, null)
					.get(0);

			RefInstansi pInstansi = new RefInstansi();
			pInstansi.setKode(dtUser.getRefInstansi().getKode());
			pInstansi.setNama(dtUser.getRefInstansi().getNama());
			dtUser.setRefInstansi(pInstansi);

			res = new StandardJsonMessage(1, dtUser, null, "Get User Success");
		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Get User Gagal");
		}

		return res;
	}

	@RequestMapping(value = "/userChangePassword.html", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage changePassword(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("old_password") String oldPassword,
			HttpSession session) throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Update Password Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		DtUser user = null;
		try {
			user = userService.findByProperty("username", username, null)
					.get(0);
			user.setTglUpdated(new Date());

			if (userService.isSamePassword(oldPassword, user.getPassword())) {
				if (userService.changePassword(user, password)) {
					RefInstansi temp = new RefInstansi();
					temp.setKode(user.getRefInstansi().getKode());
					temp.setNama(user.getRefInstansi().getNama());
					user.setRefInstansi(temp);
					res = new StandardJsonMessage(1, user, null,
							"Update Password Success");
				} else {
					res = new StandardJsonMessage(0, null, null,
							"Update Password Gagal");
				}
			} else {
				res = new StandardJsonMessage(0, null, null,
						"Password lama salah");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(0, null, null,
					"Update Password Gagal" + ex.getMessage());
		}
		return res;
	}

}
