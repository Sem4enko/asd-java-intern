package team.asd.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import team.asd.entity.PaymentTransaction;
import team.asd.mapper.PaymentTransactionMapper;

@Repository
@AllArgsConstructor
public class ImplPaymentTransactionDao implements PaymentTransactionDao {
	private PaymentTransactionMapper paymentTransactionMapper;

	@Override
	public PaymentTransaction readById(Integer id) {
		return paymentTransactionMapper.readById(id);
	}

	@Override
	public PaymentTransaction create(PaymentTransaction paymentTransaction) {
		return paymentTransactionMapper.create(paymentTransaction);
	}

	@Override
	public PaymentTransaction update(PaymentTransaction paymentTransaction) {
		return paymentTransactionMapper.update(paymentTransaction);
	}

	@Override
	public void delete(Integer id) {
		paymentTransactionMapper.delete(id);
	}
}
