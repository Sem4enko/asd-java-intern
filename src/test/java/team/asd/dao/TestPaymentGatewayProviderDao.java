package team.asd.dao;

import team.asd.entity.PaymentGatewayProvider;

import java.util.List;

public class TestPaymentGatewayProviderDao implements PaymentGatewayProviderDao {

	@Override
	public PaymentGatewayProvider readById(Integer id) {
		return null;
	}

	@Override
	public void create(PaymentGatewayProvider paymentGatewayProvider) {

	}

	@Override
	public void update(PaymentGatewayProvider paymentGatewayProvider) {

	}

	@Override
	public void delete(Integer id) {

	}

	@Override
	public List<PaymentGatewayProvider> readByNameAutoPaySupportSplitPayment(Integer supportSplitPayment, Integer autoPay, String name) {
		return null;
	}

	@Override
	public List<PaymentGatewayProvider> readWithPaymentTransaction() {
		return null;
	}

	@Override
	public String threadUpdate(PaymentGatewayProvider paymentGatewayProvider) {
		return null;
	}
}
