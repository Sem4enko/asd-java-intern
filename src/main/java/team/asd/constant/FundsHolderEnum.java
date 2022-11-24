package team.asd.constant;

import java.util.stream.Stream;

public enum FundsHolderEnum {
	PropertyManager(0), BookingPal(1), Splitted(2), ChannelPartner(3);
	private final int value;
	FundsHolderEnum(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public static FundsHolderEnum getByInt(Integer intValue) {
		return Stream.of(FundsHolderEnum.values())
				.filter(e -> intValue.equals(e.getValue()))
				.findAny()
				.orElse(null);
	}
}