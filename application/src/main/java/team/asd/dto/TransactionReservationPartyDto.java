package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionReservationPartyDto {
	@Positive
	@JsonProperty("payment_transaction_id")
	@ApiModelProperty(notes = "Payment Transaction ID", example = "1", required = true)
	private Integer paymentTransactionId;

	@Size(max = 15)
	@JsonProperty("payment_transaction_state")
	@ApiModelProperty(notes = "Status of payment processing from Gateway provider", example = "Failed, Accepted, Declined, Confirmed", required = true)
	private String paymentTransactionState;

	@Size(max = 15)
	@JsonProperty("reservation_state")
	@ApiModelProperty(notes = "State of current record", example = "Created, Final", required = true)
	private String reservationState;


	@JsonProperty("reservation_quote")
	@ApiModelProperty(notes = "Full price for reservation with all the commissions. It's the price the customer pays",  required = true)
	private Double reservationQuote;

	@Size(max = 3)
	@JsonProperty("reservation_currency")
	@ApiModelProperty(notes = "Currency code of payment for reservation",  required = true)
	private String reservationCurrency;

	@Size(max = 45)
	@JsonProperty("supplier_name")
	@ApiModelProperty(notes = "Name of party", example = "test party", required = true)
	private String supplierName;

	@Size(max = 15)
	@JsonProperty("supplier_user_type")
	@ApiModelProperty(notes = "User type", example = "Customer")
	private String supplierUserType;

	@JsonProperty("partner_payment")
	@PositiveOrZero(message = "Should be positive or zero")
	@ApiModelProperty(notes = "Channel partner commission amount", example = "22.22")
	private Double partnerPayment;

	@JsonProperty("final_amount")
	@ApiModelProperty(notes = "Total reservation amount", example = "22.22")
	private Double finalAmount;

	@Size(max = 3)
	@JsonProperty("payment_transaction_currency")
	@ApiModelProperty(notes = "Currency that was used for transaction processing", example = "USD", required = true)
	private String paymentTransactionCurrency;
}
