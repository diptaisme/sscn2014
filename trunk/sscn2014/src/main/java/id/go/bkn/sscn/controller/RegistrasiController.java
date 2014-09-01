package id.go.bkn.sscn.controller;

import id.go.bkn.sscn.dao.RefPendidikanDao;
import id.go.bkn.sscn.manager.Constanta;
import id.go.bkn.sscn.model.json.JabatanJson;
import id.go.bkn.sscn.model.json.LokasiJson;
import id.go.bkn.sscn.model.json.LokasiTestJson;
import id.go.bkn.sscn.model.json.PendidikanJson;
import id.go.bkn.sscn.persistence.entities.DtPendaftaran;
import id.go.bkn.sscn.persistence.entities.MFormasi;
import id.go.bkn.sscn.persistence.entities.RefInstansi;
import id.go.bkn.sscn.persistence.entities.RefJabatan;
import id.go.bkn.sscn.persistence.entities.RefLokasi;
import id.go.bkn.sscn.persistence.entities.RefLokasiTest;
import id.go.bkn.sscn.persistence.entities.RefPendidikan;
import id.go.bkn.sscn.services.RegistrasiService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegistrasiController {
	@Inject
	private RegistrasiService registrasiService;

	@Inject
	private RefPendidikanDao refPendidikanDao;

	// belum selesai
	@RequestMapping(value = "/registrasi.html", method = RequestMethod.POST)
	public String registrasi(@RequestParam("no_ktp") String noKtp,
			@RequestParam("nama") String nama,
			@RequestParam("tempat_lahir") String tempatLahir,
			@RequestParam("tglMonth") String tglMonth,
			@RequestParam("tglDay") String tglDay,
			@RequestParam("tglYear") String tglYear,
			@RequestParam("alamat") String alamat,
			@RequestParam("kota") String kota,
			@RequestParam("propinsi") String propinsi,
			@RequestParam("kode_pos") String kodePos,
			@RequestParam("area_telpon") String areaTelpon,
			@RequestParam("nomor_telpon") String nomorTelpon,
			@RequestParam("email") String email,
			@RequestParam("formasi") String formasi,
			@RequestParam("pendidikan") String pendidikan,
			@RequestParam("no_ijazah") String noIjazah, Model model) {
		DtPendaftaran pendaftaran = new DtPendaftaran();
		pendaftaran.setAlamat(alamat);
		pendaftaran.setJnsKelamin("P");
		pendaftaran.setKeterangan("sdsadsa keterangan");
		pendaftaran.setLembaga("lem");
		pendaftaran.setFormasi(new MFormasi());
		pendaftaran.setLokasiTest("lokT");

		int minute = Calendar.getInstance().getTime().getMinutes();
		int day = Calendar.getInstance().getTime().getDay();
		int second = Calendar.getInstance().getTime().getSeconds();
		int x = noKtp.length() > 7 ? 2 : 3;
		int y = noKtp.length() % 2 > 7 ? 0 : 1;
		String noPeserta = "" + x + minute + "0" + day + y + second; // random
																		// example
		String noRegister = "" + y + day + minute + x + second; // random
																// example
		if (noPeserta.length() > 10) {
			noPeserta = noPeserta.substring(0, 10);
		}
		if (noRegister.length() > 10) {
			noRegister = noRegister.substring(0, 10);
		}
		pendaftaran.setNoPeserta(noPeserta);
		pendaftaran.setNoRegister(noRegister); // example
		pendaftaran.setStatus("V");
		pendaftaran.setTglTest(new Date());
		pendaftaran.setTglUpdated(new Date());
		pendaftaran.setTglValidate(new Date());
		pendaftaran.setUserValidate("kosong");

		pendaftaran.setEmail(email);
		pendaftaran.setKodePos(kodePos);
		pendaftaran.setKota(kota);
		pendaftaran.setNama(nama);
		pendaftaran.setNoIjazah(noIjazah);
		pendaftaran.setNoNik(noKtp);
		pendaftaran.setPendidikan(pendidikan);
		pendaftaran.setPropinsi(propinsi);
		pendaftaran.setTelpon(areaTelpon + nomorTelpon);
		pendaftaran.setTglCreated(Calendar.getInstance().getTime());
		pendaftaran.setTmpLahir(tempatLahir);
		// dd-MM-yyyy
		String strDate = tglDay + "-" + "10" + "-" + tglYear;
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date tglLahir = null;
		try {
			tglLahir = formatter.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pendaftaran.setTglLahir(tglLahir);

		pendaftaran = registrasiService.insertNewRegistrasi(pendaftaran);

		model.addAttribute("pendaftaran", pendaftaran);
		return "RegistrasiSuccess";
	}

	private String generateNoRegistrasi() {
		return "";
	}

	@RequestMapping(value = "/ac_instansi.html", method = RequestMethod.GET)
	@ResponseBody
	public String findInstansiLikeByName(
			@RequestParam("callback") String callBack,
			@RequestParam("maxRows") String maxRows,
			@RequestParam("startsWith") String startsWith) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		List<RefInstansi> instansis = registrasiService.getInstansi(
				Integer.parseInt(maxRows), startsWith);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("instansis", instansis);
		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				resultMap));
	}

	/*
	 * @RequestMapping(value = "/cb_lokasi_by_instansi.do", method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseBody public Map<String, List<LokasiJson>> getLokasis(
	 * 
	 * @RequestParam("instansi") String instansi) {
	 * 
	 * List<RefLokasi> lokasis = registrasiService.getLokasi(instansi);
	 * Map<String, RefLokasi> mapLokasis = new Hashtable<String, RefLokasi>();
	 * for (RefLokasi lokasi : lokasis) {
	 * if(!mapLokasis.containsKey(lokasi.getKode())){
	 * mapLokasis.put(lokasi.getKode(), lokasi); } } lokasis = new
	 * ArrayList<RefLokasi>(mapLokasis.values()); List<LokasiJson> newLokasis =
	 * new ArrayList<LokasiJson>(); for (RefLokasi lokasi : lokasis) { //
	 * lokasi.
	 * setRefInstansi(null);http://localhost:8080/sscnServer/dashboard.dohttp
	 * ://localhost:8080/sscnServer/dashboard.do newLokasis.add(new
	 * LokasiJson(lokasi.getKode(), lokasi.getNama())); } Map<String,
	 * List<LokasiJson>> lokasiMap = new HashMap<String, List<LokasiJson>>();
	 * lokasiMap.put("lokasis", newLokasis);
	 * 
	 * return lokasiMap; }
	 */
	@RequestMapping(value = "/cb_lokasi_by_instansi.html", method = RequestMethod.GET)
	@ResponseBody
	public String getLokasis(@RequestParam("callback") String callBack,
			@RequestParam("instansi") String instansi) throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		List<RefLokasi> lokasis = registrasiService.getLokasi(instansi);

		Map<String, RefLokasi> mapLokasis = new Hashtable<String, RefLokasi>();
		for (RefLokasi lokasi : lokasis) {
			if (!mapLokasis.containsKey(lokasi.getKode())) {
				mapLokasis.put(lokasi.getKode(), lokasi);
			}
		}
		lokasis = new ArrayList<RefLokasi>(mapLokasis.values());
		List<LokasiJson> newLokasis = new ArrayList<LokasiJson>();
		for (RefLokasi lokasi : lokasis) {
			// lokasi.setRefInstansi(null);http://localhost:8080/sscnServer/dashboard.dohttp://localhost:8080/sscnServer/dashboard.do
			newLokasis.add(new LokasiJson(lokasi.getKode(), lokasi.getNama()));
		}
		Map<String, List<LokasiJson>> lokasiMap = new HashMap<String, List<LokasiJson>>();
		lokasiMap.put("lokasis", newLokasis);

		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				lokasiMap));
	}

	/*
	 * @RequestMapping(value = "/cb_lokasi_by_instansi.do", method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseBody public Map<String, List<RefLokasi>> getLokasis(
	 * 
	 * @RequestParam("instansi") String instansi) {
	 * 
	 * List<RefLokasi> lokasis = registrasiService.getLokasi(instansi);
	 * List<RefLokasi> newLokasis = new ArrayList<RefLokasi>(); for (RefLokasi
	 * lokasi : lokasis) { lokasi.setRefInstansi(null); newLokasis.add(lokasi);
	 * } Map<String, List<RefLokasi>> lokasiMap = new HashMap<String,
	 * List<RefLokasi>>(); lokasiMap.put("lokasis", newLokasis);
	 * 
	 * return lokasiMap; }
	 */

	/*
	 * @RequestMapping(value = "/cb_lokasi_by_instansi.do", method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseBody public String getLokasis(@RequestParam("callback") String
	 * callBack,
	 * 
	 * @RequestParam("instansi") String instansi) throws Exception {
	 * ObjectMapper objectMapper = new ObjectMapper(); List<RefLokasi>
	 * refLokasis = registrasiService.getLokasi(instansi); List<LokasiJson>
	 * lokasis = new ArrayList<LokasiJson>(); for (RefLokasi refLokasi :
	 * refLokasis) { lokasis.add(new LokasiJson(refLokasi.getKode(),
	 * refLokasi.getNama())); } Map<String, Object> lokasiMap = new
	 * HashMap<String, Object>(); lokasiMap.put("lokasis", lokasis); return
	 * objectMapper.writeValueAsString(new JSONPObject(callBack, lokasiMap)); }
	 */

	/*
	 * @RequestMapping(value = "/cb_jabatan_by_instansi_lokasi.do", method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseBody public Map<String, List<RefJabatan>> getJabatans(
	 * 
	 * @RequestParam("instansi") String instansi,
	 * 
	 * @RequestParam("lokasi") String lokasi) {
	 * 
	 * List<RefJabatan> jabatans = registrasiService.getJabatan(instansi,
	 * lokasi); Map<String, List<RefJabatan>> lokasiMap = new HashMap<String,
	 * List<RefJabatan>>(); lokasiMap.put("jabatans", jabatans); return
	 * lokasiMap; }
	 */

	/*
	 * @RequestMapping(value = "/cb_jabatan_by_instansi_lokasi.do", method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseBody public Map<String, List<JabatanJson>> getJabatans(
	 * 
	 * @RequestParam("instansi") String instansi,
	 * 
	 * @RequestParam("lokasi") String lokasi) {
	 * 
	 * List<RefJabatan> jabatans = registrasiService.getJabatan(instansi,
	 * lokasi); List<JabatanJson> newJabatans = new ArrayList<JabatanJson>();
	 * for (RefJabatan jabatan : jabatans) { newJabatans.add(new
	 * JabatanJson(jabatan.getKode(), jabatan .getNama())); } Map<String,
	 * List<JabatanJson>> lokasiMap = new HashMap<String, List<JabatanJson>>();
	 * lokasiMap.put("jabatans", newJabatans); return lokasiMap; }
	 */
	@RequestMapping(value = "/cb_jabatan_by_instansi_lokasi.html", method = RequestMethod.GET)
	@ResponseBody
	public String getJabatans(@RequestParam("callback") String callBack,
			@RequestParam("instansi") String instansi,
			@RequestParam("lokasi") String lokasi) throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		List<RefJabatan> jabatans = registrasiService.getJabatan(instansi,
				lokasi);
		List<JabatanJson> newJabatans = new ArrayList<JabatanJson>();
		for (RefJabatan jabatan : jabatans) {
			newJabatans.add(new JabatanJson(jabatan.getKode(), jabatan
					.getNama()));
		}

		Map<String, List<JabatanJson>> jabatanMap = new HashMap<String, List<JabatanJson>>();
		jabatanMap.put("jabatans", newJabatans);

		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				jabatanMap));
		//
	}

	/*
	 * @RequestMapping(value = "/cb_pendidikan_by_instansi_lokasi_jabatan.do",
	 * method = RequestMethod.GET)
	 * 
	 * @ResponseBody public Map<String, List<RefPendidikan>> getPendidikans(
	 * 
	 * @RequestParam("instansi") String instansi,
	 * 
	 * @RequestParam("lokasi") String lokasi,
	 * 
	 * @RequestParam("jabatan") String jabatan) {
	 * 
	 * List<RefPendidikan> pendidikans = registrasiService.getPendidikan(
	 * instansi, lokasi, jabatan); Map<String, List<RefPendidikan>>
	 * pendidikanMap = new HashMap<String, List<RefPendidikan>>();
	 * pendidikanMap.put("pendidikans", pendidikans); return pendidikanMap; }
	 */
	/*
	 * @RequestMapping(value = "/cb_pendidikan_by_instansi_lokasi_jabatan.do",
	 * method = RequestMethod.GET)
	 * 
	 * @ResponseBody public Map<String, List<PendidikanJson>> getPendidikans(
	 * 
	 * @RequestParam("instansi") String instansi,
	 * 
	 * @RequestParam("lokasi") String lokasi,
	 * 
	 * @RequestParam("jabatan") String jabatan) {
	 * 
	 * List<RefPendidikan> pendidikans = registrasiService.getPendidikan(
	 * instansi, lokasi, jabatan); List<PendidikanJson> newPendidikans = new
	 * ArrayList<PendidikanJson>(); for (RefPendidikan pendidikan : pendidikans)
	 * { newPendidikans.add(new PendidikanJson(pendidikan.getKode(),
	 * pendidikan.getNama(), pendidikan.getTingkat())); } Map<String,
	 * List<PendidikanJson>> pendidikanMap = new HashMap<String,
	 * List<PendidikanJson>>(); pendidikanMap.put("pendidikans",
	 * newPendidikans); return pendidikanMap; }
	 */

	@RequestMapping(value = "/cb_pendidikan_by_instansi_lokasi_jabatan.html", method = RequestMethod.GET)
	@ResponseBody
	public String getPendidikans(@RequestParam("callback") String callBack,
			@RequestParam("instansi") String instansi,
			@RequestParam("lokasi") String lokasi,
			@RequestParam("jabatan") String jabatan) throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		List<RefPendidikan> pendidikans = registrasiService.getPendidikan(
				instansi, lokasi, jabatan);
		List<PendidikanJson> newPendidikans = new ArrayList<PendidikanJson>();
		for (RefPendidikan pendidikan : pendidikans) {
			newPendidikans.add(new PendidikanJson(pendidikan.getKode(),
					pendidikan.getNama(), pendidikan.getTingkat()));
		}
		Map<String, List<PendidikanJson>> pendidikanMap = new HashMap<String, List<PendidikanJson>>();
		pendidikanMap.put("pendidikans", newPendidikans);

		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				pendidikanMap));
	}

	// after registrasi yang lama
	/*
	 * @RequestMapping(value = "/afterRegistrasi.do", method =
	 * RequestMethod.GET) public String
	 * afterRegistrasi(@RequestParam("idRegistrasi") String idRegistrasi,
	 * ModelMap model) { model.addAttribute("idRegistrasi", idRegistrasi);
	 * return "afterRegistrasi"; }
	 */

	// updated
	@RequestMapping(value = "/cb_pendidikan_by_instansi_lokasi.html", method = RequestMethod.GET)
	@ResponseBody
	public String getPendidikans(@RequestParam("callback") String callBack,
			@RequestParam("instansi") String instansi,
			@RequestParam("lokasi") String lokasi) throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		List<RefPendidikan> pendidikans = registrasiService.getPendidikan(
				instansi, lokasi);
		List<PendidikanJson> newPendidikans = new ArrayList<PendidikanJson>();
		for (RefPendidikan pendidikan : pendidikans) {
			newPendidikans.add(new PendidikanJson(pendidikan.getKode(),
					pendidikan.getNama(), pendidikan.getTingkat()));
		}
		Map<String, List<PendidikanJson>> pendidikanMap = new HashMap<String, List<PendidikanJson>>();
		pendidikanMap.put("pendidikans", newPendidikans);

		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				pendidikanMap));
	}

	@RequestMapping(value = "/cb_jabatan_by_instansi_lokasi_pendidikan.html", method = RequestMethod.GET)
	@ResponseBody
	public String getJabatans(@RequestParam("callback") String callBack,
			@RequestParam("instansi") String instansi,
			@RequestParam("lokasi") String lokasi,
			@RequestParam("pendidikan") String pendidikan) throws Exception {

		RefPendidikan pendidikanChoose = refPendidikanDao.findByProperty(
				"kode", pendidikan, null).get(0);
		ObjectMapper objectMapper = new ObjectMapper();
		List<RefJabatan> jabatans = registrasiService.getJabatan(instansi,
				lokasi, pendidikan);
		List<JabatanJson> newJabatans = new ArrayList<JabatanJson>();
		for (RefJabatan jabatan : jabatans) {
			newJabatans.add(new JabatanJson(jabatan.getKode(), jabatan
					.getNama()));
		}

		// get jabatan pendidikan umum
		List<RefJabatan> jabatanPendidikanUmum = null;
		// D3
		if (pendidikanChoose.getTingkat().equalsIgnoreCase("20")) {
			if (!pendidikanChoose.getKode().equals(Constanta.D3SEMUAJURUSAN[0])) {
				jabatanPendidikanUmum = registrasiService.getJabatan(instansi,
						lokasi, Constanta.D3SEMUAJURUSAN[0]);
			}
		}
		// D4 atau S1
		else if (pendidikanChoose.getTingkat().equalsIgnoreCase("30")
				&& !pendidikanChoose.getKode().startsWith("71")) {
			if (!pendidikanChoose.getKode().equals(Constanta.D4SEMUAJURUSAN[0])
					&& !pendidikanChoose.getKode().equals(
							Constanta.S1SEMUAJURUSAN[0])) {
				jabatanPendidikanUmum = registrasiService.getJabatan(instansi,
						lokasi, Constanta.D4SEMUAJURUSAN[0]);
				jabatanPendidikanUmum.addAll(registrasiService.getJabatan(
						instansi, lokasi, Constanta.S1SEMUAJURUSAN[0]));
			}
		}
		// S2
		else if (pendidikanChoose.getTingkat().equalsIgnoreCase("30")
				&& pendidikanChoose.getKode().startsWith("71")) {
			if (!pendidikanChoose.getKode().equals(Constanta.S2SEMUAJURUSAN[0])) {
				jabatanPendidikanUmum = registrasiService.getJabatan(instansi,
						lokasi, Constanta.S2SEMUAJURUSAN[0]);
			}
		}

		if (jabatanPendidikanUmum != null && jabatanPendidikanUmum.size() > 0) {
			for (RefJabatan jabatan : jabatanPendidikanUmum) {
				newJabatans.add(new JabatanJson(jabatan.getKode(), jabatan
						.getNama()));
			}
		}

		Map<String, List<JabatanJson>> jabatanMap = new HashMap<String, List<JabatanJson>>();
		jabatanMap.put("jabatans", newJabatans);

		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				jabatanMap));
	}

	// after registrasi
	@RequestMapping(value = "/cetakPendaftaran.html", method = RequestMethod.GET)
	public String afterRegistrasi(HttpServletRequest request, ModelMap model) {
		if (request.getParameter("idRegistrasi") != null) {
			model.addAttribute("idRegistrasi",
					request.getParameter("idRegistrasi"));
		}
		return "cetakPendaftaran";
	}

	@RequestMapping(value = "/cb_pendidikan_by_instansi.html", method = RequestMethod.GET)
	@ResponseBody
	public String getPendidikans(@RequestParam("callback") String callBack,
			@RequestParam("instansi") String instansi) throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		List<RefPendidikan> pendidikans = registrasiService
				.getPendidikanByInstansi(instansi);
		Map<String, RefPendidikan> mapPendidikans = new Hashtable<String, RefPendidikan>();
		for (RefPendidikan pendidikan : pendidikans) {
			if (!mapPendidikans.containsKey(pendidikan.getKode())) {
				mapPendidikans.put(pendidikan.getKode(), pendidikan);
			}
		}
		pendidikans = new ArrayList<RefPendidikan>(mapPendidikans.values());

		List<PendidikanJson> newPendidikans = new ArrayList<PendidikanJson>();
		for (RefPendidikan pendidikan : pendidikans) {
			newPendidikans.add(new PendidikanJson(pendidikan.getKode(),
					pendidikan.getNama(), pendidikan.getTingkat()));
		}
		// add pendidikan untuk D-3 SEMUA JURUSAN
		newPendidikans.add(new PendidikanJson(Constanta.D3SEMUAJURUSAN[0],
				Constanta.D3SEMUAJURUSAN[1], Constanta.D3SEMUAJURUSAN[2]));
		// add pendidikan untuk D-4 SEMUA JURUSAN
		newPendidikans.add(new PendidikanJson(Constanta.D4SEMUAJURUSAN[0],
				Constanta.D4SEMUAJURUSAN[1], Constanta.D4SEMUAJURUSAN[2]));
		// add pendidikan untuk S-1 SEMUA JURUSAN
		newPendidikans.add(new PendidikanJson(Constanta.S1SEMUAJURUSAN[0],
				Constanta.S1SEMUAJURUSAN[1], Constanta.S1SEMUAJURUSAN[2]));

		// add pendidikan untuk S-2 SEMUA JURUSAN
		newPendidikans.add(new PendidikanJson(Constanta.S2SEMUAJURUSAN[0],
				Constanta.S2SEMUAJURUSAN[1], Constanta.S2SEMUAJURUSAN[2]));

		Map<String, List<PendidikanJson>> pendidikanMap = new HashMap<String, List<PendidikanJson>>();
		pendidikanMap.put("pendidikans", newPendidikans);

		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				pendidikanMap));
	}

	@RequestMapping(value = "/cb_lokasi_by_instansi_pendidikan.html", method = RequestMethod.GET)
	@ResponseBody
	public String getLokasis(@RequestParam("callback") String callBack,
			@RequestParam("instansi") String instansi,
			@RequestParam("pendidikan") String pendidikan) throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		List<RefLokasi> lokasis = registrasiService.getLokasi(instansi,
				pendidikan);

		Map<String, RefLokasi> mapLokasis = new Hashtable<String, RefLokasi>();
		for (RefLokasi lokasi : lokasis) {
			if (!mapLokasis.containsKey(lokasi.getKode())) {
				mapLokasis.put(lokasi.getKode(), lokasi);
			}
		}
		lokasis = new ArrayList<RefLokasi>(mapLokasis.values());
		List<LokasiJson> newLokasis = new ArrayList<LokasiJson>();
		for (RefLokasi lokasi : lokasis) {
			newLokasis.add(new LokasiJson(lokasi.getKode(), lokasi.getNama()));
		}
		// get lokasi untuk pendidikan umum
		RefPendidikan pendidikanChoose = refPendidikanDao.findByProperty(
				"kode", pendidikan, null).get(0);
		List<RefLokasi> lokasiPendidikanUmum = null;
		// D3
		if (pendidikanChoose.getTingkat().equalsIgnoreCase("20")) {
			if (!pendidikanChoose.getKode().equals(Constanta.D3SEMUAJURUSAN[0])) {
				lokasiPendidikanUmum = registrasiService.getLokasi(instansi,
						Constanta.D3SEMUAJURUSAN[0]);
			}
		}
		// D4 atau S1
		else if (pendidikanChoose.getTingkat().equalsIgnoreCase("30")
				&& !pendidikanChoose.getKode().startsWith("71")) {
			if (!pendidikanChoose.getKode().equals(Constanta.D4SEMUAJURUSAN[0])
					&& !pendidikanChoose.getKode().equals(
							Constanta.S1SEMUAJURUSAN[0])) {
				lokasiPendidikanUmum = registrasiService.getLokasi(instansi,
						Constanta.D4SEMUAJURUSAN[0]);
				lokasiPendidikanUmum.addAll(registrasiService.getLokasi(
						instansi, Constanta.S1SEMUAJURUSAN[0]));
			}
		}
		// S2
		else if (pendidikanChoose.getTingkat().equalsIgnoreCase("30")
				&& pendidikanChoose.getKode().startsWith("71")) {
			if (!pendidikanChoose.getKode().equals(Constanta.S2SEMUAJURUSAN[0])) {
				lokasiPendidikanUmum = registrasiService.getLokasi(instansi,
						Constanta.S2SEMUAJURUSAN[0]);
			}
		}

		if (lokasiPendidikanUmum != null && lokasiPendidikanUmum.size() > 0) {
			for (RefLokasi lokasi : lokasiPendidikanUmum) {
				newLokasis.add(new LokasiJson(lokasi.getKode(), lokasi
						.getNama()));
			}
		}
		//
		Map<String, List<LokasiJson>> lokasiMap = new HashMap<String, List<LokasiJson>>();
		lokasiMap.put("lokasis", newLokasis);

		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				lokasiMap));
	}

	@RequestMapping(value = "/cb_lokasi_test_by_instansi.html", method = RequestMethod.GET)
	@ResponseBody
	public String getLokasiTests(@RequestParam("callback") String callBack,
			@RequestParam("instansi") String instansi) throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		List<RefLokasiTest> lokasiTests = registrasiService
				.getLokasiTestByInstansi(instansi);

		List<LokasiTestJson> newLokasiTests = new ArrayList<LokasiTestJson>();
		for (RefLokasiTest lokasiTest : lokasiTests) {
			newLokasiTests.add(new LokasiTestJson(lokasiTest.getKode(),
					lokasiTest.getNama(), lokasiTest.getStatus(), lokasiTest
							.getInstansi().getKode()));
		}
		Map<String, List<LokasiTestJson>> lokasiTestMap = new HashMap<String, List<LokasiTestJson>>();
		lokasiTestMap.put("lokasiTests", newLokasiTests);

		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				lokasiTestMap));
	}

}
