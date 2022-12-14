package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.entity.PaymentGatewayProvider;

import java.util.List;

@Mapper
public interface PaymentGatewayProviderMapper {
	PaymentGatewayProvider readById(Integer id);

	void create(PaymentGatewayProvider paymentGatewayProvider);

	void update(PaymentGatewayProvider paymentGatewayProvider);

	void delete(Integer id);

	List<PaymentGatewayProvider> readByNameAutoPaySupportSplitPayment(Integer supportSplitPayment, Integer autoPay, String name);

	List<PaymentGatewayProvider> readWithPaymentTransaction();
}
