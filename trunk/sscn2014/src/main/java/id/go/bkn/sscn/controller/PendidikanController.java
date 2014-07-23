package id.go.bkn.sscn.controller;

import id.go.bkn.sscn.persistence.entities.RefPendidikan;
import id.go.bkn.sscn.services.PendidikanService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PendidikanController {
	@Inject
	private PendidikanService pendidikanService;

	@RequestMapping(value = "/findPendidikanLikeByName.html", method = RequestMethod.GET)
	@ResponseBody
	public String findPendidikanLikeByName(
			@RequestParam("callback") String callBack,
			@RequestParam("name_startsWith") String name) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		List<RefPendidikan> pendidikans = pendidikanService
				.findPendidikanByLikeName(name);
		Map<String, Object> mapResult = new HashMap<String, Object>();
		mapResult.put("pendidikans", pendidikans);

		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				mapResult));
	}

}
