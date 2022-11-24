package team.asd.dao;

import team.asd.entity.PaymentTransaction;

public interface PaymentTransactionDao {
	PaymentTransaction readById(Integer id);

	void create(PaymentTransaction paymentTransaction);

	PaymentTransaction update(PaymentTransaction paymentTransaction);

	void delete(Integer id);
}
