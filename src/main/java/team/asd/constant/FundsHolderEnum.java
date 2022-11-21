package team.asd.constant;

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
		for (FundsHolderEnum v : values()) {
			if (intValue.equals(v.value)) {
				return v;
			}
		}
		return null;
	}
}
