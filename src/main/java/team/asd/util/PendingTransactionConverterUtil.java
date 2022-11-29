package team.asd.util;

import org.apache.commons.lang3.StringUtils;
import team.asd.constant.PendingTransactionStatus;
import team.asd.dto.PendingTransactionDto;
import team.asd.entity.PendingTransaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.stream.Stream;

public class PendingTransactionConverterUtil {
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static PendingTransaction convertToEntity(PendingTransactionDto pendingTransactionDTO) {
		if (Objects.isNull(pendingTransactionDTO)) {
			return null;
		}
		return PendingTransaction.builder()
				.id(pendingTransactionDTO.getId())
				.reservationId(pendingTransactionDTO.getReservationId())
				.partialIin(pendingTransactionDTO.getPartialIin())
				.chargeDate(convertStringToLocalDate(pendingTransactionDTO.getChargeDate()))
				.chargeAmount(pendingTransactionDTO.getChargeAmount())
				.status(convertStringToStatus(pendingTransactionDTO.getStatus()))
				.build();
	}

	public static PendingTransactionDto convertToDto(PendingTransaction pendingTransaction) {
		if (Objects.isNull(pendingTransaction)) {
			return null;
		}
		return PendingTransactionDto.builder()
				.id(pendingTransaction.getId())
				.reservationId(pendingTransaction.getReservationId())
				.partialIin(pendingTransaction.getPartialIin())
				.chargeDate(convertLocalDateToString(pendingTransaction.getChargeDate()))
				.chargeAmount(pendingTransaction.getChargeAmount())
				.status(convertStatusToString(pendingTransaction.getStatus()))
				.build();

	}

	public static LocalDate convertStringToLocalDate(String stringDate) {
		try {
			return StringUtils.isBlank(stringDate) ? null : LocalDate.parse(stringDate, DATE_FORMAT);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	public static String convertLocalDateToString(LocalDate date) {
		return date == null ? null : date.format(DATE_FORMAT);
	}

	public static String convertStatusToString(PendingTransactionStatus status) {
		return Objects.isNull(status) ? PendingTransactionStatus.Failed.name() : status.name();
	}

	public static PendingTransactionStatus convertStringToStatus(String status) {
		if (StringUtils.isBlank(status)) {
			return null;
		}
		return Stream.of(PendingTransactionStatus.values())
				.filter(element -> element.name()
						.equals(status))
				.findFirst()
				.orElse(PendingTransactionStatus.Failed);
	}
}
