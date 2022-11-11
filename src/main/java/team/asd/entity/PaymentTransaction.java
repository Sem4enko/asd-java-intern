package team.asd.entity;

import lombok.Builder;
import lombok.Data;
import team.asd.constant.PaymentTransactionChargeType;
import team.asd.constant.FundsHolderEnum;
import team.asd.constant.PaymentTransactionStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentTransaction {
	private Integer id;
	private Integer paymentProvider;
	private LocalDate createdDate;
	private Integer reservationId;
	private FundsHolderEnum fundsHolder;
	private String partialIin;
	private PaymentTransactionStatus status;
	private String message;
	private Integer partnerId;
	private Integer supplierId;
	private String currency;
	private Double totalCommission;
	private Double partnerPayment;
	private Double bpPayment;
	private Double creditCardFee;
	private Double finalAmount;
	private PaymentTransactionChargeType chargeType;
	private LocalDateTime version;
}
