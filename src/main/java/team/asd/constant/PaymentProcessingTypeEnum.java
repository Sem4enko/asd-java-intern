package team.asd.constant;

public enum PaymentProcessingTypeEnum {
	ManualPayment(0), AutoPayment(1);
	private final int value;
	PaymentProcessingTypeEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
