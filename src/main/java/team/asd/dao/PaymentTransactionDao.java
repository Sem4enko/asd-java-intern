package team.asd.dao;

import team.asd.entity.PaymentTransaction;

public interface PaymentTransactionDao {
	PaymentTransaction readById(Integer id);

	PaymentTransaction create(PaymentTransaction paymentTransaction);

	PaymentTransaction update(PaymentTransaction paymentTransaction);

	void delete(Integer id);
}
