package team.asd.dao;

import team.asd.entity.PaymentTransaction;

public class TestPaymentTransactionDao implements PaymentTransactionDao{
	@Override
	public PaymentTransaction readById(Integer id) {
		return null;
	}

	@Override
	public void create(PaymentTransaction paymentTransaction) {
	}

	@Override
	public PaymentTransaction update(PaymentTransaction paymentTransaction) {
		return null;
	}

	@Override
	public void delete(Integer id) {

	}
}
