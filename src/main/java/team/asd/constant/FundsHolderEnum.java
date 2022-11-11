package team.asd.constant;

public enum FundsHolderEnum {
	PropertyManager(0), BookingPal(1), Splitted(2), ChannelPartner(3);
	private int fundStatus;
	FundsHolderEnum(int fundStatus) {
		this.fundStatus = fundStatus;
	}
}
