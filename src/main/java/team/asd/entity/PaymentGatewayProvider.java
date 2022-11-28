package team.asd.entity;

import lombok.Builder;
import lombok.Data;
import team.asd.constant.PaymentProcessingTypeEnum;
import team.asd.constant.SupportSplitPaymentEnum;

import java.util.Date;

@Data
@Builder
public class PaymentGatewayProvider {
	private Integer id;
	private String name;
	private Double fee;
	private PaymentProcessingTypeEnum autoPay;
	private SupportSplitPaymentEnum supportSplitPayment;
	private Date version;

}
