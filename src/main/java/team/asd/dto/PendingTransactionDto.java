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
public class PendingTransactionDto {

	@Positive
	@JsonProperty("id")
	@ApiModelProperty(notes = "Pending Transaction ID", example = "1", required = true)
	private Integer id;

	@Positive
	@JsonProperty("reservation_id")
	@ApiModelProperty(notes = "Relation to reservation record", example = "1", required = true)
	private Integer reservationId;

	@Size(max = 4)
	@JsonProperty("partial_iin")
	@ApiModelProperty(notes = "Last four digits of processed credit card", example = "1234")
	private String partialIin;

	@JsonProperty("charge_date")
	@ApiModelProperty(notes = "Date and time when a payment will be processed by the payment gateway", example = "10-12-2022", required = true)
	private String chargeDate;

	@JsonProperty("charge_amount")
	@ApiModelProperty(notes = "An amount remaining to be charged", example = "1.0", required = true)
	private Double chargeAmount;

	@Size(max = 15)
	@JsonProperty("status")
	@ApiModelProperty(notes = "Status of pending transaction", example = "1 -Active,2 -Pending,3 -Cleared,4 -Failed,5 -Deleted,6 -Canceled" ,required = true)
	private String status;

}
