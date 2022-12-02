package team.asd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.asd.constant.PendingTransactionStatus;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
