package team.asd.constant;

public enum PaymentTransactionFundsHolder {
	PropertyManager(0), BookingPal(1), Splitted(2), ChannelPartner(3);
	private int fundStatus;
	PaymentTransactionFundsHolder(int fundStatus) {
		this.fundStatus = fundStatus;
	}
}
