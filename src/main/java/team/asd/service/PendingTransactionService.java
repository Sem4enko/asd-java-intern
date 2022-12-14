package team.asd.service;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import team.asd.dao.PendingTransactionDao;
import team.asd.entity.PendingTransaction;
import team.asd.exception.ValidationException;
import team.asd.util.PendingTransactionConverterUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PendingTransactionService {
	PendingTransactionDao pendingTransactionDao;

	public PendingTransaction readById(Integer id) throws ValidationException {
		checkId(id);
		return pendingTransactionDao.readById(id);
	}

	public void create(PendingTransaction pendingTransaction) throws ValidationException {
		checkPendingTransaction(pendingTransaction);
		pendingTransactionDao.create(pendingTransaction);
	}

	public void update(PendingTransaction pendingTransaction) throws ValidationException {
		checkId(pendingTransaction.getId());
		pendingTransactionDao.update(pendingTransaction);
	}

	public void delete(Integer id) throws ValidationException {
		checkId(id);
		pendingTransactionDao.delete(id);
	}

	public List<PendingTransaction> readByReservationIdStatus(Integer reservationId, Integer status) {
		if (ObjectUtils.anyNull(reservationId, status)) {
			throw new ValidationException("Required parameters are not provided");
		}
		return pendingTransactionDao.readByReservationIdStatus(reservationId, status);
	}

	public void createList(List<PendingTransaction> pendingTransactions) {
		if (CollectionUtils.isEmpty(pendingTransactions)) {
			throw new ValidationException("Empty list is provided");
		}
		pendingTransactions.forEach(this::checkPendingTransaction);
		pendingTransactionDao.createList(pendingTransactions);
	}

	public List<PendingTransaction> readByDateRange(String stringFromDate, String stringToDate) {
		if (StringUtils.isAnyBlank(stringFromDate, stringToDate)) {
			throw new ValidationException("Required parameters are not provided");
		}

		LocalDate fromDate = PendingTransactionConverterUtil.convertStringToLocalDate(stringFromDate);
		LocalDate toDate = PendingTransactionConverterUtil.convertStringToLocalDate(stringToDate);

		if (Objects.requireNonNull(fromDate)
				.isAfter(Objects.requireNonNull(toDate))) {
			throw new ValidationException("Invalid parameters are provided");
		}
		return pendingTransactionDao.readByDateRange(fromDate, toDate);
	}

	public List<PendingTransaction> readWithPaymentTransactionByStatusAndChargeDate() {
		return pendingTransactionDao.readWithPaymentTransactionByStatusAndChargeDate();
	}

	private void checkId(Integer id) throws ValidationException {
		if (ObjectUtils.isEmpty(id) || id <= 0) {
			throw new ValidationException("Wrong id was provided");
		}
	}

	private void checkPendingTransaction(PendingTransaction pendingTransaction) throws ValidationException {
		if (Objects.isNull(pendingTransaction) || ObjectUtils.anyNull(pendingTransaction.getReservationId(), pendingTransaction.getChargeDate(),
				pendingTransaction.getChargeAmount(), pendingTransaction.getStatus())) {
			throw new ValidationException("Wrong field was provided");
		}
	}
}
