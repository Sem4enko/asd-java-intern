package team.asd.dao;

import team.asd.entity.PendingTransaction;

public interface PendingTransactionDao {
	PendingTransaction readById(Integer id);

	PendingTransaction create(PendingTransaction pendingTransaction);

	PendingTransaction update(PendingTransaction pendingTransaction);

	void delete(Integer id);
}
