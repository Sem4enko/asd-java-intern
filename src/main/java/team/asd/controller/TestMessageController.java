package team.asd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class TestMessageController {
	@GetMapping(value = "/test/message", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TestMessage> message() {
		return new ResponseEntity<>(new TestMessage(LocalDate.now(), "Test message from Spring Boot Application!"), HttpStatus.OK);
	}
}
