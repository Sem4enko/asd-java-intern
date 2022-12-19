package team.asd.dao;

import team.asd.entity.PaymentGatewayProvider;

import java.util.List;

public interface PaymentGatewayProviderDao {

	PaymentGatewayProvider readById(Integer id);

	void create(PaymentGatewayProvider paymentGatewayProvider);

	void update(PaymentGatewayProvider paymentGatewayProvider);

	void delete(Integer id);

	List<PaymentGatewayProvider> readByNameAutoPaySupportSplitPayment(Integer supportSplitPayment, Integer autoPay, String name);

	List<PaymentGatewayProvider> readWithPaymentTransaction();

	String threadUpdate(PaymentGatewayProvider paymentGatewayProvider);
}
