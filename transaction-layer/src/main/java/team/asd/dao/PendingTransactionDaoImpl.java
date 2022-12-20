package team.asd.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import team.asd.entity.PendingTransaction;
import team.asd.mapper.PendingTransactionMapper;

import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
public class PendingTransactionDaoImpl implements PendingTransactionDao {
	private PendingTransactionMapper pendingTransactionMapper;

	@Override
	public PendingTransaction readById(Integer id) {
		return pendingTransactionMapper.readById(id);
	}

	@Override
	public void create(PendingTransaction pendingTransaction) {
		pendingTransactionMapper.create(pendingTransaction);
	}

	@Override
	public void update(PendingTransaction pendingTransaction) {
		pendingTransactionMapper.update(pendingTransaction);
	}

	@Override
	public void delete(Integer id) {
		pendingTransactionMapper.delete(id);
	}

	@Override
	public List<PendingTransaction> readByReservationIdStatus(Integer reservationId, Integer status) {
		return pendingTransactionMapper.readByReservationIdStatus(reservationId, status);
	}

	@Override
	public void createList(List<PendingTransaction> pendingTransactions) {
		pendingTransactionMapper.createList(pendingTransactions);
	}

	@Override
	public List<PendingTransaction> readByDateRange(LocalDate fromDate, LocalDate toDate) {
		return pendingTransactionMapper.readByDateRange(fromDate, toDate);
	}

	@Override
	public List<PendingTransaction> readWithPaymentTransactionByStatusAndChargeDate() {
		return pendingTransactionMapper.readWithPaymentTransactionByStatusAndChargeDate();
	}
}
