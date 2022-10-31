package team.asd.service;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import team.asd.constants.DateElement;
import team.asd.exceptions.WrongArgumentException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class CalendarService implements IsCalendarService {

	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public String toString(LocalDate date) {
		return date == null ? null : date.format(DATE_FORMAT);
	}

	@Override
	public LocalDate toLocalDate(String stringDate) {
		try {
			return StringUtils.isBlank(stringDate) ? null : LocalDate.parse(stringDate, DATE_FORMAT);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	@Override
	public long defineCountInRange(LocalDate fromDate, LocalDate toDate, ChronoUnit unit) {
		if (ObjectUtils.anyNull(fromDate, toDate, unit)) {
			throw new WrongArgumentException("Wrong parameters were provided");
		}
		return unit.between(fromDate.atStartOfDay(), toDate.atStartOfDay());
	}

	@Override
	public String getInfo(LocalDate date, DateElement dateElement) {
		if (ObjectUtils.anyNull(date, dateElement)) {
			throw new WrongArgumentException("Wrong parameters were provided");
		}
		switch (dateElement) {
		case DAY_OF_WEEK:
			return date.getDayOfWeek()
					.name();
		case WEEK_NUMBER:
			return String.valueOf(date.get(WeekFields.of(Locale.getDefault())
					.weekOfWeekBasedYear()));
		case MONTH:
			return date.getMonth()
					.toString();
		case IS_LEAP_YEAR:
			return date.isLeapYear() ? "Yes" : "No";
		default:
			throw new WrongArgumentException("Wrong parameters were provided");
		}
	}

	@Override
	public LocalDate reformatToLocalDate(String dateString) throws DateTimeException {
		if (StringUtils.isBlank(dateString)) {
			throw new DateTimeException("Wrong parameters were provided");
		}

		String[] parsedDate = dateString.split(" ");
		if (parsedDate.length != 3) {
			throw new DateTimeException("Wrong parameters were provided");
		}

		String day = parsedDate[0].replaceAll("\\D", "");
		String month = parsedDate[1];
		int year = NumberUtils.toInt(parsedDate[2]);

		if (year < 1000 || year > 3000) {
			throw new DateTimeException("Wrong parameters were provided");
		}

		String newDate = day + " " + month + " " + year;

		return LocalDate.parse(newDate, DateTimeFormatter.ofPattern("d MMM uuuu", Locale.ENGLISH));
	}
}
