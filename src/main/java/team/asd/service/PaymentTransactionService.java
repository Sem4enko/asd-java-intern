package team.asd.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import team.asd.dao.PaymentTransactionDao;
import team.asd.entity.PaymentTransaction;
import team.asd.exception.ValidationException;

import java.util.Objects;

@Service
@AllArgsConstructor
public class PaymentTransactionService {
	PaymentTransactionDao paymentTransactionDao;

	public PaymentTransaction readById(Integer id) throws ValidationException {
		checkId(id);
		return paymentTransactionDao.readById(id);
	}

	public void create(PaymentTransaction paymentTransaction) throws ValidationException {
		checkPaymentTransaction(paymentTransaction);
		paymentTransactionDao.create(paymentTransaction);
	}

	public void update(PaymentTransaction paymentTransaction) throws ValidationException {
		checkId(paymentTransaction.getId());
		paymentTransactionDao.update(paymentTransaction);
	}

	public void delete(Integer id) throws ValidationException {
		checkId(id);
		paymentTransactionDao.delete(id);
	}

	private void checkId(Integer id) throws ValidationException {
		if (ObjectUtils.isEmpty(id) || id <= 0) {
			throw new ValidationException("Wrong id was provided");
		}
	}

	private void checkPaymentTransaction(PaymentTransaction paymentTransaction) throws ValidationException {
		if (Objects.isNull(paymentTransaction) || ObjectUtils.anyNull(paymentTransaction.getPaymentProvider(), paymentTransaction.getCreatedDate(),
				paymentTransaction.getReservationId(), paymentTransaction.getFundsHolder(), paymentTransaction.getStatus(), paymentTransaction.getCurrency())) {
			throw new ValidationException("Wrong field was provided");
		}
	}
}
