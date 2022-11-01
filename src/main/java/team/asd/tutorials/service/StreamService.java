package team.asd.tutorials.service;

import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import team.asd.tutorials.service.IsStreamApiService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamService implements IsStreamApiService {
	@Override
	public @NonNull Stream<?> getNonNullStreamItems(Collection<?> collection) {
		return CollectionUtils.isEmpty(collection) ?
				Stream.empty() :
				collection.stream()
						.filter(Objects::nonNull);
	}

	@Override
	public @NonNull List<Integer> defineListFromRange(Integer start, Integer end) throws NumberFormatException {
		if (ObjectUtils.anyNull(start, end)) {
			return Collections.emptyList();
		}

		if (start > end) {
			Integer temp = start;
			start = end;
			end = temp;
		}

		return IntStream.rangeClosed(start, end)
				.boxed()
				.collect(Collectors.toList());
	}

	@Override
	public @NonNull List<Integer> convertStringListToIntegerList(List<String> stringList) {
		if (CollectionUtils.isEmpty(stringList)) {
			return Collections.emptyList();
		}
		try {
			return stringList.stream()
					.filter(Objects::nonNull)
					.filter(str -> str.matches("-?[0-9]+"))
					.map(Integer::valueOf)
					.collect(Collectors.toList());
		} catch (NumberFormatException n) {
			return Collections.emptyList();
		}
	}

	@Override
	public @NonNull IntStream convertStringToLegalChars(String value) {
		if (StringUtils.isBlank(value)) {
			return IntStream.empty();
		}
		return value.chars()
				.filter(ch -> CharUtils.isAsciiAlpha((char) ch) || Character.isDigit(ch));
	}

	@Override
	public @NonNull BigDecimal sumAllValues(List<BigDecimal> values) {
		return values.stream()
				.filter(Objects::nonNull)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public @NonNull Stream<LocalDate> sortLocalDateList(List<LocalDate> listOfDates) {
		if (CollectionUtils.isEmpty(listOfDates)) {
			return Stream.empty();
		}
		return listOfDates.stream()
				.filter(Objects::nonNull)
				.sorted();
	}

	@Override
	public @NonNull Stream<LocalDate> skipDaysFromSpecifiedDate(List<LocalDate> listOfDates, LocalDate date, Integer daysToSkip) {
		if (ObjectUtils.anyNull(listOfDates, date, daysToSkip) || daysToSkip < 0) {
			return Stream.empty();
		}
		return listOfDates.stream()
				.filter(Objects::nonNull)
				.sorted()
				.filter(element -> element.isAfter(date) || date.equals(element))
				.skip(daysToSkip);
	}

	@Override
	public @NonNull List<? extends Object> collectLists(List<?>... lists) {
		return Arrays.stream(lists)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

	@Override
	public @NonNull List<? extends Object> removeDuplicates(List<?> listWithDuplicates) {
		if (CollectionUtils.isEmpty(listWithDuplicates)) {
			return Collections.emptyList();
		}
		return listWithDuplicates.stream()
				.distinct()
				.collect(Collectors.toList());
	}
}
