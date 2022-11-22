package team.asd.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import team.asd.dao.PendingTransactionDao;
import team.asd.entity.PendingTransaction;
import team.asd.exception.ValidationException;

import java.util.Objects;

@Service
public class PendingTransactionService {

	PendingTransactionDao pendingTransactionDao;

	public PendingTransactionService(PendingTransactionDao pendingTransactionDao) {
		this.pendingTransactionDao = pendingTransactionDao;
	}

	public PendingTransaction readById(Integer id) throws ValidationException {
		checkId(id);
		return pendingTransactionDao.readById(id);
	}

	public PendingTransaction create(PendingTransaction pendingTransaction) throws ValidationException {
		checkPaymentTransaction(pendingTransaction);
		return pendingTransactionDao.create(pendingTransaction);
	}

	public PendingTransaction update(PendingTransaction pendingTransaction) throws ValidationException {
		checkId(pendingTransaction.getId());
		return pendingTransactionDao.update(pendingTransaction);
	}

	public void delete(Integer id) throws ValidationException {
		checkId(id);
		pendingTransactionDao.delete(id);
	}

	private void checkId(Integer id) throws ValidationException {
		if (ObjectUtils.isEmpty(id) || id <= 0) {
			throw new ValidationException("Wrong id was provided");
		}
	}

	private void checkPaymentTransaction(PendingTransaction pendingTransaction) throws ValidationException {
		if (Objects.isNull(pendingTransaction) || ObjectUtils.anyNull(pendingTransaction.getReservationId(), pendingTransaction.getChargeDate(),
				pendingTransaction.getChargeAmount(), pendingTransaction.getStatus())) {
			throw new ValidationException("Wrong field was provided");
		}
		checkId(pendingTransaction.getId());
	}
}
