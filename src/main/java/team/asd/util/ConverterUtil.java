package team.asd.util;

import org.apache.commons.lang3.StringUtils;
import team.asd.constant.FundsHolderEnum;
import team.asd.constant.PaymentTransactionChargeType;
import team.asd.constant.PaymentTransactionStatus;
import team.asd.dto.PaymentTransactionDto;
import team.asd.entity.PaymentTransaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.stream.Stream;

public class ConverterUtil {
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static PaymentTransaction convertToEntity(PaymentTransactionDto paymentTransactionDTO) {
		if (Objects.isNull(paymentTransactionDTO)) {
			return null;
		}
		return PaymentTransaction.builder()
				.id(paymentTransactionDTO.getId())
				.paymentProvider(paymentTransactionDTO.getPaymentProvider())
				.createdDate(convertStringToLocalDate(paymentTransactionDTO.getCreatedDate()))
				.reservationId(paymentTransactionDTO.getReservationId())
				.fundsHolder(convertIntegerToFundsHolder(paymentTransactionDTO.getFundsHolder()))
				.partialIin(paymentTransactionDTO.getPartialIin())
				.status(convertStringToStatus(paymentTransactionDTO.getStatus()))
				.message(paymentTransactionDTO.getMessage())
				.partnerId(paymentTransactionDTO.getPartnerId())
				.supplierId(paymentTransactionDTO.getSupplierId())
				.currency(paymentTransactionDTO.getCurrency())
				.totalCommission(paymentTransactionDTO.getTotalCommission())
				.partnerPayment(paymentTransactionDTO.getPartnerPayment())
				.bpPayment(paymentTransactionDTO.getBpPayment())
				.creditCardFee(paymentTransactionDTO.getCreditCardFee())
				.finalAmount(paymentTransactionDTO.getFinalAmount())
				.chargeType(convertStringToChargeType(paymentTransactionDTO.getChargeType()))
				.build();
	}

	public static PaymentTransactionDto convertToDto(PaymentTransaction paymentTransaction) {
		if (Objects.isNull(paymentTransaction)) {
			return null;
		}
		return PaymentTransactionDto.builder()
				.id(paymentTransaction.getId())
				.paymentProvider(paymentTransaction.getPaymentProvider())
				.createdDate(convertLocalDateToString(paymentTransaction.getCreatedDate()))
				.reservationId(paymentTransaction.getReservationId())
				.fundsHolder(convertFundsHolderToInteger(paymentTransaction.getFundsHolder()))
				.partialIin(paymentTransaction.getPartialIin())
				.status(convertStatusToString(paymentTransaction.getStatus()))
				.message(paymentTransaction.getMessage())
				.partnerId(paymentTransaction.getPartnerId())
				.supplierId(paymentTransaction.getSupplierId())
				.currency(paymentTransaction.getCurrency())
				.totalCommission(paymentTransaction.getTotalCommission())
				.partnerPayment(paymentTransaction.getPartnerPayment())
				.bpPayment(paymentTransaction.getBpPayment())
				.creditCardFee(paymentTransaction.getCreditCardFee())
				.finalAmount(paymentTransaction.getFinalAmount())
				.chargeType(convertChargeTypeToString(paymentTransaction.getChargeType()))
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

	public static String convertStatusToString(PaymentTransactionStatus status) {
		return Objects.isNull(status) ? PaymentTransactionStatus.Failed.name() : status.name();
	}

	public static PaymentTransactionStatus convertStringToStatus(String status) {
		return Stream.of(PaymentTransactionStatus.values())
				.filter(element -> element.name()
						.equals(status))
				.findFirst()
				.orElse(PaymentTransactionStatus.Failed);
	}

	public static String convertChargeTypeToString(PaymentTransactionChargeType chargeType) {
		return Objects.isNull(chargeType) ? null : chargeType.name();
	}

	public static PaymentTransactionChargeType convertStringToChargeType(String str) {
		return Stream.of(PaymentTransactionChargeType.values())
				.filter(element -> element.name()
						.equals(str))
				.findFirst()
				.orElse(null);
	}

	public static Integer convertFundsHolderToInteger(FundsHolderEnum fundsHolderEnum) {
		return Objects.isNull(fundsHolderEnum) ? null : fundsHolderEnum.getValue();
	}

	public static FundsHolderEnum convertIntegerToFundsHolder(Integer value) {
		return Stream.of(FundsHolderEnum.values())
				.filter(element -> element.getValue() == value)
				.findFirst()
				.orElse(null);
	}
}
