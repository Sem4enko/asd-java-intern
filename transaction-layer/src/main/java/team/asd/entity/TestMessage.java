package team.asd.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TestMessage {
	private final LocalDate date;
	private final String content;
}
