package team.asd.constant;

import java.util.stream.Stream;

public enum SupportSplitPaymentEnum {
	FullPaymentOnly(0), SupportSplitPayment(1);
	private final int value;
	SupportSplitPaymentEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static SupportSplitPaymentEnum getByInt(Integer intValue) {
		return Stream.of(SupportSplitPaymentEnum.values())
				.filter(e -> intValue.equals(e.getValue()))
				.findAny()
				.orElse(null);
	}
}
