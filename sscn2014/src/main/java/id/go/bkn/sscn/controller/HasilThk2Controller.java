package id.go.bkn.sscn.controller;

import id.go.bkn.sscn.core.persistence.tools.QueryOrder;
import id.go.bkn.sscn.dao.InstansiThk2Dao;
import id.go.bkn.sscn.persistence.entities.DtPersyaratan;
import id.go.bkn.sscn.persistence.entities.HasilThk2;
import id.go.bkn.sscn.persistence.entities.InstansiThk2;
import id.go.bkn.sscn.persistence.entities.PropThk2;
import id.go.bkn.sscn.persistence.entities.RefJabatan;
import id.go.bkn.sscn.services.HasilThk2Service;
import id.go.bkn.sscn.services.JabatanService;
import id.go.bkn.sscn.util.json.HasilThk2JsonMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HasilThk2Controller {

	@Inject
	private HasilThk2Service hasilThk2Service;
	
	@Inject
	private InstansiThk2Dao instansiThk2Dao;

	@RequestMapping(value = "/cb_provinsi.do", method = RequestMethod.GET)
	@ResponseBody
	public String findProvinsiThk2ByJenis(
			@RequestParam("callback") String callBack,
			@RequestParam("jenis") String jenis) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		List<PropThk2> provinsiList = hasilThk2Service.findProvinsiByJenis(jenis); 
		Map<String, Object> mapResult = new HashMap<String, Object>();
		mapResult.put("provinsiList", provinsiList);

		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				mapResult));
	}

	@RequestMapping(value = "/cb_instansi_kerja.do", method = RequestMethod.GET)
	@ResponseBody
	public String findInstansiKerjaByProvinsi(
			@RequestParam("callback") String callBack,
			@RequestParam("provinsi") String provinsi) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		List<InstansiThk2> instansiList = hasilThk2Service.findInstansiKerjaThk2ByProvinsi(provinsi); 
		Map<String, Object> mapResult = new HashMap<String, Object>();
		mapResult.put("instansiKerjaList", instansiList);

		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				mapResult));
	}

	@RequestMapping(value = "/hasil_thk2.do", method = RequestMethod.GET)
	@ResponseBody
	public String findHasilKerjaThk2ByInstansi(
			@RequestParam("callback") String callBack,
			@RequestParam("instansi") String instansiKerja, HttpServletRequest request) throws Exception {
		
		int indexAndCount[] = new int[2]; 
		int numRow = 10;		
		indexAndCount[0] = 1;
		String index = request.getParameter("activePage");
		String order = request.getParameter("order");
		if (index != null && !index.contentEquals("")){
			indexAndCount[0] = Integer.parseInt(index);			
		}
		if (order == null){
			order = "no";			
		}
		indexAndCount[0] = (indexAndCount[0] - 1) * numRow;			
		indexAndCount[1] = numRow;
		
		List<HasilThk2> hasil = hasilThk2Service.findHasilThk2ByInstansi(instansiKerja, order, indexAndCount);
		Integer count = hasilThk2Service.countHasilThk2ByInstansi(instansiKerja);
		
		int numPage = (int) Math.ceil((double)count/indexAndCount[1]);		
		int activePage = (int) Math.ceil((double)(indexAndCount[0] + 1)/ indexAndCount[1]);
		int part2;
		if ((activePage * indexAndCount[1]) >= count){
			part2 = count;
		} else {
			part2 = activePage * indexAndCount[1];
		}		
		
		ObjectMapper objectMapper = new ObjectMapper();
		 
		HasilThk2JsonMessage hasilMessage = new HasilThk2JsonMessage();
		if (count > 0){
			hasilMessage.setResult(1);
			hasilMessage.setMessage("SUKSES");
			hasilMessage.setHasil(hasil);
			hasilMessage.setCount(count);
			hasilMessage.setActivePage(activePage);
			hasilMessage.setPart2(part2);
			hasilMessage.setNumpage(numPage);
			hasilMessage.setIndex(indexAndCount[0]);
		} else {
			hasilMessage.setResult(0);
			hasilMessage.setMessage("TIDAK ADA DATA");			
		}
		
		
		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				hasilMessage));
	}

	@RequestMapping(value = "/cari_hasil_thk2.do", method = RequestMethod.GET)
	@ResponseBody
	public String findHasilKerjaThk2ByNoTest(
			@RequestParam("callback") String callBack,
			@RequestParam("cari") String noTest, HttpServletRequest request) throws Exception {
		
		List<HasilThk2> hasil = hasilThk2Service.findHasilThk2ByNoTest(noTest);
		int count = hasil.size();
		ObjectMapper objectMapper = new ObjectMapper();
		 
		HasilThk2JsonMessage hasilMessage = new HasilThk2JsonMessage();
		if (count > 0){
			hasilMessage.setResult(1);
			hasilMessage.setMessage("SUKSES");
			hasilMessage.setHasil(hasil);			
		} else {
			hasilMessage.setResult(0);
			hasilMessage.setMessage("TIDAK ADA DATA");			
		}
		
		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				hasilMessage));
	}

	@RequestMapping(value = "/cari_x_hasil_thk2.do", method = RequestMethod.GET)
	@ResponseBody
	public String findHasilKerjaThk2ByInstansiNoTest(
			@RequestParam("callback") String callBack,
			@RequestParam("cari") String noTest, HttpServletRequest request) throws Exception {

		String instansiId = request.getParameter("instansi");
		
		List<HasilThk2> hasil = hasilThk2Service.findHasilThk2ByInstansiNoTest(noTest, instansiId);
		int count = hasil.size();
		ObjectMapper objectMapper = new ObjectMapper();
		 
		HasilThk2JsonMessage hasilMessage = new HasilThk2JsonMessage();
		if (count > 0){
			hasilMessage.setResult(1);
			hasilMessage.setMessage("SUKSES");
			hasilMessage.setHasil(hasil);			
		} else {
			hasilMessage.setResult(0);
			hasilMessage.setMessage("Data Tidak Ditemukan");			
		}
		
		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				hasilMessage));
	}
}
