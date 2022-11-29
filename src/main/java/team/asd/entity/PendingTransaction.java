package team.asd.entity;

import lombok.Builder;
import lombok.Data;
import team.asd.constant.PendingTransactionStatus;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
public class PendingTransaction {
	private Integer id;

	private Integer reservationId;

	private String partialIin;

	private LocalDate chargeDate;

	private Double chargeAmount;

	private PendingTransactionStatus status;

	private Date version;
}
