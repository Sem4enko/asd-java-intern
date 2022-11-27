package team.asd.constant;

import java.util.stream.Stream;

public enum PendingTransactionStatus {
	Active(1), Pending(2), Cleared(3), Failed(4), Deleted(5), Canceled(6);
	private final int value;

	PendingTransactionStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static PendingTransactionStatus getByInt(Integer intValue) {
		return Stream.of(PendingTransactionStatus.values())
				.filter(e -> intValue.equals(e.getValue()))
				.findAny()
				.orElse(null);
	}
}
