package team.asd.dao;

import team.asd.entity.PendingTransaction;

import java.time.LocalDate;
import java.util.List;

public class TestPendingTransactionDao implements PendingTransactionDao{
	@Override
	public PendingTransaction readById(Integer id) {
		return null;
	}

	@Override
	public void create(PendingTransaction pendingTransaction) {

	}

	@Override
	public void update(PendingTransaction pendingTransaction) {

	}

	@Override
	public void delete(Integer id) {

	}

	@Override
	public List<PendingTransaction> readByReservationIdStatus(Integer reservationId, Integer status) {
		return null;
	}

	@Override
	public void createList(List<PendingTransaction> pendingTransactions) {

	}

	@Override
	public List<PendingTransaction> readByDateRange(LocalDate fromDate, LocalDate toDate) {
		return null;
	}
}
