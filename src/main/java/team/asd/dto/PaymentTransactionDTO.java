package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentTransactionDTO {

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("payment_provider")
	private Integer paymentProvider;

	@JsonProperty("created_date")
	private String createdDate;

	@JsonProperty("reservation_id")
	private Integer reservationId;

	@JsonProperty("funds_holder")
	private Integer fundsHolder;

	@JsonProperty("partial_iin")
	private String partialIin;

	@JsonProperty("status")
	private String status;

	@JsonProperty("message")
	private String message;

	@JsonProperty("partner_id")
	private Integer partnerId;

	@JsonProperty("supplier_id")
	private Integer supplierId;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("total_commission")
	private Double totalCommission;

	@JsonProperty("partner_payment")
	private Double partnerPayment;

	@JsonProperty("bp_payment")
	private Double bpPayment;

	@JsonProperty("credit_card_fee")
	private Double creditCardFee;

	@JsonProperty("final_amount")
	private Double finalAmount;

	@JsonProperty("charge_type")
	private String chargeType;

	@JsonProperty("version")
	private String version;
}