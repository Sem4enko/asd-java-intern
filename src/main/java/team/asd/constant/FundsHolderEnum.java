package team.asd.constant;

public enum FundsHolderEnum {
	PropertyManager(0), BookingPal(1), Splitted(2), ChannelPartner(3);
	private final int fundStatus;
	FundsHolderEnum(int fundStatus) {
		this.fundStatus = fundStatus;
	}

	public int getFundStatus() {
		return fundStatus;
	}
}
