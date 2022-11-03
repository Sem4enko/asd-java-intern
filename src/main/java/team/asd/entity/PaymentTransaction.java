package team.asd.entity;

import lombok.Data;
import team.asd.constant.PaymentTransactionChargeType;
import team.asd.constant.PaymentTransactionFundsHolder;
import team.asd.constant.PaymentTransactionStatus;

import java.time.LocalDate;
import java.util.Date;

@Data
public class PaymentTransaction {
	private Integer id;
	private Integer payment_provider;
	private LocalDate created_date;
	private Integer reservation_id;
	private PaymentTransactionFundsHolder funds_holder;
	private String partial_iin;
	private PaymentTransactionStatus status;
	private String message;
	private Integer partner_id;
	private Integer supplier_id;
	private String currency;
	private Double total_commission;
	private Double partner_payment;
	private Double bp_payment;
	private Double credit_card_fee;
	private Double final_amount;
	private PaymentTransactionChargeType charge_type;
	private Date version;
}
