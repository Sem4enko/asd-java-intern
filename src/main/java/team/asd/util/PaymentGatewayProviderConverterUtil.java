package team.asd.util;

import team.asd.constant.PaymentProcessingTypeEnum;
import team.asd.constant.SupportSplitPaymentEnum;
import team.asd.dto.PaymentGatewayProviderDto;
import team.asd.entity.PaymentGatewayProvider;

import java.util.Objects;
import java.util.stream.Stream;

public class PaymentGatewayProviderConverterUtil {

	public static PaymentGatewayProvider convertToEntity(PaymentGatewayProviderDto paymentGatewayProviderDto) {
		if (Objects.isNull(paymentGatewayProviderDto)) {
			return null;
		}
		return PaymentGatewayProvider.builder()
				.id(paymentGatewayProviderDto.getId())
				.name(paymentGatewayProviderDto.getName())
				.fee(paymentGatewayProviderDto.getFee())
				.autoPay(convertIntegerToPaymentProcessingType(paymentGatewayProviderDto.getAutoPay()))
				.supportSplitPayment(convertIntegerToSupportSplitPayment(paymentGatewayProviderDto.getSupportSplitPayment()))
				.build();
	}

	public static PaymentGatewayProviderDto convertToDto(PaymentGatewayProvider paymentGatewayProvider) {
		if (Objects.isNull(paymentGatewayProvider)) {
			return null;
		}
		return PaymentGatewayProviderDto.builder()
				.id(paymentGatewayProvider.getId())
				.name(paymentGatewayProvider.getName())
				.fee(paymentGatewayProvider.getFee())
				.autoPay(convertPaymentProcessingTypeToInteger(paymentGatewayProvider.getAutoPay()))
				.supportSplitPayment(convertSupportSplitPaymentToInteger(paymentGatewayProvider.getSupportSplitPayment()))
				.build();
	}

	public static Integer convertPaymentProcessingTypeToInteger(PaymentProcessingTypeEnum paymentProcessingTypeEnum) {
		return Objects.isNull(paymentProcessingTypeEnum) ? null : paymentProcessingTypeEnum.getValue();
	}

	public static PaymentProcessingTypeEnum convertIntegerToPaymentProcessingType(Integer value) {
		if (value == null) {
			return null;
		}
		return Stream.of(PaymentProcessingTypeEnum.values())
				.filter(element -> element.getValue() == value)
				.findFirst()
				.orElse(null);
	}

	public static Integer convertSupportSplitPaymentToInteger(SupportSplitPaymentEnum supportSplitPaymentEnum) {
		return Objects.isNull(supportSplitPaymentEnum) ? null : supportSplitPaymentEnum.getValue();
	}

	public static SupportSplitPaymentEnum convertIntegerToSupportSplitPayment(Integer value) {
		if (value == null) {
			return null;
		}
		return Stream.of(SupportSplitPaymentEnum.values())
				.filter(element -> element.getValue() == value)
				.findFirst()
				.orElse(null);
	}
}
