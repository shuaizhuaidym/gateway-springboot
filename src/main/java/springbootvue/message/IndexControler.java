package springbootvue.message;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexControler {
	@GetMapping(value = "/")
	public String authenticate(Map<String, Object> map, @RequestParam String name) {
		map.put("name", name);
		return "login";
	}

}
