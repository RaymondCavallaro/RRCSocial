package rrc.social.resource.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/gserver")
public class SayHelloApplication {

	@RequestMapping(value = "/greeting")
	public String greet() {
		List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations");
		Random rand = new Random();

		int randomNum = rand.nextInt(greetings.size());
		return greetings.get(randomNum);
	}

	@RequestMapping(value = "/")
	public String home() {
		return "Hi!";
	}
}