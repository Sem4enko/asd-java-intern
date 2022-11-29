package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentGatewayProviderDto {

	@Positive
	@JsonProperty("id")
	private Integer id;

	@JsonProperty("name")
	private String name;

	@Positive
	@JsonProperty("fee")
	private Double fee;

	@JsonProperty("autopay")
	private Integer autoPay;

	@JsonProperty("support_split_payment")
	private Integer supportSplitPayment;

}
