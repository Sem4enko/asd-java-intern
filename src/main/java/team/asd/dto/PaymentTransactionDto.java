package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentTransactionDto {

	@Positive
	@JsonProperty("id")
	private Integer id;

	@Positive
	@JsonProperty("payment_provider")
	private Integer paymentProvider;

	@JsonProperty("created_date")
	private String createdDate;

	@Positive
	@JsonProperty("reservation_id")
	private Integer reservationId;

	@JsonProperty("funds_holder")
	private Integer fundsHolder;

	@Size(max = 4)
	@JsonProperty("partial_iin")
	private String partialIin;

	@Size(max = 15)
	@JsonProperty("status")
	private String status;

	@Size(max = 1000)
	@JsonProperty("message")
	private String message;

	@JsonProperty("partner_id")
	private Integer partnerId;

	@JsonProperty("supplier_id")
	private Integer supplierId;

	@Size(max = 3)
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

	@Size(max = 15)
	@JsonProperty("charge_type")
	private String chargeType;

}