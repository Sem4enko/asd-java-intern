package team.asd.dao;

import team.asd.entity.PaymentTransaction;

public interface PaymentTransactionDao {
	PaymentTransaction readById(Integer id);
	PaymentTransaction create(Integer id);
	PaymentTransaction update(Integer id);
	void delete(Integer id);
}
