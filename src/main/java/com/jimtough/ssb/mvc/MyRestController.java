package com.jimtough.ssb.mvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

	static final String CANNED_REPLY_STRING = "Greetings from my Spring Boot app!";

	@RequestMapping("/")
	public String index() {
		return CANNED_REPLY_STRING;
	}

}
