package rrc.social.resource.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController("/gclient")
public class UserApplication {

	static RestTemplate restTemplate = new RestTemplate();

	@RequestMapping("/hi")
	public String hi(@RequestParam(value = "name", defaultValue = "Artaban") String name) {
		String greeting = restTemplate.getForObject("http://localhost:8080/gserver/greeting", String.class);
		return String.format("%s, %s!", greeting, name);
	}
}