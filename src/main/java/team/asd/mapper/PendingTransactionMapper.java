package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.entity.PendingTransaction;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface PendingTransactionMapper {
	PendingTransaction readById(Integer id);

	void create(PendingTransaction pendingTransaction);

	void update(PendingTransaction pendingTransaction);

	void delete(Integer id);

	List<PendingTransaction> readByReservationIdStatus(Integer reservationId, Integer status);

	void createList(List<PendingTransaction> pendingTransactions);

	List<PendingTransaction> readByDateRange(LocalDate fromDate, LocalDate toDate);

	List<PendingTransaction> readWithPaymentTransactionByStatusAndChargeDate();
}
