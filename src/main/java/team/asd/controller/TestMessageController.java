package team.asd.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class TestMessageController {

	@GetMapping(value = "/test/message", produces = MediaType.APPLICATION_JSON_VALUE)
	public TestMessage message() {
		return new TestMessage(LocalDate.now(), "Test message from Spring Boot Application!");
	}
}
