package team.asd.dao;

import team.asd.entity.PaymentGatewayProvider;

public interface PaymentGatewayProviderDao {

	PaymentGatewayProvider readById(Integer id);

	void create(PaymentGatewayProvider paymentGatewayProvider);

	void update(PaymentGatewayProvider paymentGatewayProvider);

	void delete(Integer id);
}
