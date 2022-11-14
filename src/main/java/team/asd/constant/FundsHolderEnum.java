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
}
