package team.asd.constant;

public enum PendingTransactionStatus {
	Active(1), Pending(2), Cleared(3), Failed(4), Deleted(5), Canceled(6);
	private final int value;

	PendingTransactionStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
