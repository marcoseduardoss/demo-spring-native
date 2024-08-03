package br.com.marcoseduardo.demo_native;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping(path = "/ping")
	public String ping() {
		return "...pong";
	}
	
}
