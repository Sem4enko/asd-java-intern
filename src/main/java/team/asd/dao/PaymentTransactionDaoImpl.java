package team.asd.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import team.asd.entity.PaymentTransaction;
import team.asd.mapper.PaymentTransactionMapper;

@Repository
@AllArgsConstructor
public class PaymentTransactionDaoImpl implements PaymentTransactionDao {
	private PaymentTransactionMapper paymentTransactionMapper;

	@Override
	public PaymentTransaction readById(Integer id) {
		return paymentTransactionMapper.readById(id);
	}

	@Override
	public void create(PaymentTransaction paymentTransaction) {
		 paymentTransactionMapper.create(paymentTransaction);
	}

	@Override
	public void update(PaymentTransaction paymentTransaction) {
		 paymentTransactionMapper.update(paymentTransaction);
	}

	@Override
	public void delete(Integer id) {
		paymentTransactionMapper.delete(id);
	}
}
