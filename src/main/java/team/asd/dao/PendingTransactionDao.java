package team.asd.dao;

import team.asd.entity.PendingTransaction;

import java.time.LocalDate;
import java.util.List;

public interface PendingTransactionDao {
	PendingTransaction readById(Integer id);

	void create(PendingTransaction pendingTransaction);

	void update(PendingTransaction pendingTransaction);

	void delete(Integer id);

	List<PendingTransaction> readByReservationIdStatus(Integer reservationId, Integer status);

	void createList(List<PendingTransaction> pendingTransactions);

	List<PendingTransaction> readByDateRange(LocalDate fromDate, LocalDate toDate);

	List<PendingTransaction> readWithPaymentTransactionByStatusAndChargeDate();
}
