package team.asd.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import team.asd.entity.TestMessage;
import team.asd.mapper.TestMapper;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
public class TestMessageController {
	private final TestMapper testMapper;

	@GetMapping(value = "/test/message", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TestMessage> message() {
		return new ResponseEntity<>(new TestMessage(LocalDate.now(), "Test message from Spring Boot Application!"), HttpStatus.OK);
	}

	@PostMapping(value = "/test/insert/{s}")
	public ResponseEntity<String> insertValue(@PathVariable(value = "s") String s) {
		testMapper.insertValue(s);
		return new ResponseEntity<>(s, HttpStatus.OK);
	}
}
