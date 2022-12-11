package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(notes = "Payment Gateway Provider ID", example = "1", required = true)
	private Integer id;

	@JsonProperty("name")
	@ApiModelProperty(notes = "Name of gateway provider", example = "First Payment Gateway Provider", required = true)
	private String name;

	@Positive
	@JsonProperty("fee")
	@ApiModelProperty(notes = "Fee amount for payment processing", example = "1.0", required = true)
	private Double fee;

	@JsonProperty("autopay")
	@ApiModelProperty(notes = "Type of payment processing", example = "0 - Manual payment, 1 - Auto payment", required = true)
	private Integer autoPay;

	@JsonProperty("support_split_payment")
	@ApiModelProperty(notes = "Defines if provider supports split payment.", example = "0 - Full payment only, 1 - Support split payment", required = true)
	private Integer supportSplitPayment;

}
