package team.asd.dao;

import org.springframework.stereotype.Repository;
import team.asd.entity.PaymentTransaction;

@Repository
public class ImplPaymentTransactionDao implements PaymentTransactionDao{
	@Override
	public PaymentTransaction readById(Integer id) {
		return null;
	}

	@Override
	public PaymentTransaction create(PaymentTransaction paymentTransaction) {
		return paymentTransaction;
	}

	@Override
	public PaymentTransaction update(PaymentTransaction paymentTransaction) {
		return null;
	}

	@Override
	public void delete(Integer id) {

	}
}
