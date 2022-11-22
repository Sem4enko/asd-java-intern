package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PendingTransactionDto {

	@NotEmpty
	@Positive
	@JsonProperty("id")
	private Integer id;

	@NotEmpty
	@Positive
	@JsonProperty("reservation_id")
	private Integer reservationId;

	@Size(max = 4)
	@JsonProperty("partial_iin")
	private String partialIin;

	@NotEmpty
	@JsonProperty("charge_date")
	private String chargeDate;

	@NotEmpty
	@JsonProperty("charge_amount")
	private Double chargeAmount;

	@NotEmpty
	@Size(max = 15)
	@JsonProperty("status")
	private String status;

}
