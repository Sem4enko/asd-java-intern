package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(notes = "Payment Transaction ID", example = "1", required = true)
	private Integer id;

	@Positive
	@JsonProperty("payment_provider")
	@ApiModelProperty(notes = "Relation to payment_gateway_provider record", example = "1", required = true)
	private Integer paymentProvider;

	@JsonProperty("created_date")
	@ApiModelProperty(notes = "Date and time when this row was created", example = "10-12-2022", required = true)
	private String createdDate;

	@Positive
	@JsonProperty("reservation_id")
	@ApiModelProperty(notes = "Relation to reservation record", example = "1", required = true)
	private Integer reservationId;

	@JsonProperty("funds_holder")
	@ApiModelProperty(notes = "FundsHolder setting value at the time when reservation was processed.", example = "0 - PropertyManager, 1 - BookingPal, 2 - Splitted, 3 - ChannelPartner", required = true)
	private Integer fundsHolder;

	@Size(max = 4)
	@JsonProperty("partial_iin")
	@ApiModelProperty(notes = "Last four digits of credit card used on payment processing", example = "1234")
	private String partialIin;

	@Size(max = 15)
	@JsonProperty("status")
	@ApiModelProperty(notes = "Status of payment processing from Gateway provider", example = "Failed, Accepted, Declined, Confirmed", required = true)
	private String status;

	@Size(max = 1000)
	@JsonProperty("message")
	@ApiModelProperty(notes = "Message from Gateway provider")
	private String message;

	@JsonProperty("partner_id")
	@ApiModelProperty(notes = "Channel partner who provided the reservation", example = "1")
	private Integer partnerId;

	@JsonProperty("supplier_id")
	@ApiModelProperty(notes = "Property manager who is the owner of product", example = "1")
	private Integer supplierId;

	@Size(max = 3)
	@JsonProperty("currency")
	@ApiModelProperty(notes = "Currency that was used for transaction processing", example = "USD", required = true)
	private String currency;

	@JsonProperty("total_commission")
	@ApiModelProperty(notes = "Sum of all commissions for related reservation", example = "22.22")
	private Double totalCommission;

	@JsonProperty("partner_payment")
	@ApiModelProperty(notes = "Channel partner commission amount", example = "22.22")
	private Double partnerPayment;

	@JsonProperty("bp_payment")
	@ApiModelProperty(notes = "BookingPal commission amount", example = "22.22")
	private Double bpPayment;

	@JsonProperty("credit_card_fee")
	@ApiModelProperty(notes = "Commission amount for using the payment system", example = "22.22")
	private Double creditCardFee;

	@JsonProperty("final_amount")
	@ApiModelProperty(notes = "Total reservation amount", example = "22.22")
	private Double finalAmount;

	@Size(max = 15)
	@JsonProperty("charge_type")
	@ApiModelProperty(notes = "Type of transaction processing", example = "ull – one payment; Deposit – first payment, split payment case; Second – second payment, split payment case; Modification – transaction update")
	private String chargeType;

}