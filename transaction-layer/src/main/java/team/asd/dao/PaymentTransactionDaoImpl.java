package team.asd.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import team.asd.entity.PaymentTransaction;
import team.asd.mapper.PaymentTransactionMapper;

import java.util.List;

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

	@Override
	public List<PaymentTransaction> readByReservationIdStatus(Integer reservationId, String status) {
		return paymentTransactionMapper.readByReservationIdStatus(reservationId, status);
	}

	@Override
	public List<PaymentTransaction> readByChargeTypePartnerIdFundsHolderStatus(String chargeType, Integer partnerId, Integer fundsHolder, String status) {
		return paymentTransactionMapper.readByChargeTypePartnerIdFundsHolderStatus(chargeType, partnerId, fundsHolder, status);
	}

	@Override
	public void createList(List<PaymentTransaction> paymentTransactions) {
		paymentTransactionMapper.createList(paymentTransactions);
	}
}
