package team.asd.constant;

import java.util.stream.Stream;

public enum PaymentProcessingTypeEnum {
	ManualPayment(0), AutoPayment(1);
	private final int value;
	PaymentProcessingTypeEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static PaymentProcessingTypeEnum getByInt(Integer intValue) {
		return Stream.of(PaymentProcessingTypeEnum.values())
				.filter(e -> intValue.equals(e.getValue()))
				.findAny()
				.orElse(null);
	}
}
