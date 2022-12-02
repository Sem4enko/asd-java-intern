package team.asd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.asd.constant.PaymentProcessingTypeEnum;
import team.asd.constant.SupportSplitPaymentEnum;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentGatewayProvider {
	private Integer id;
	private String name;
	private Double fee;
	private PaymentProcessingTypeEnum autoPay;
	private SupportSplitPaymentEnum supportSplitPayment;
	private Date version;

}
