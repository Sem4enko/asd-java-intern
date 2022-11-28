package team.asd.constant;

public enum SupportSplitPaymentEnum {
	FullPaymentOnly(0), SupportSplitPayment(1);
	private final int value;
	SupportSplitPaymentEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
