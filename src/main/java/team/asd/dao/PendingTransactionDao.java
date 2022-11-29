package team.asd.dao;

import team.asd.entity.PendingTransaction;

public interface PendingTransactionDao {
	PendingTransaction readById(Integer id);

	void create(PendingTransaction pendingTransaction);

	void update(PendingTransaction pendingTransaction);

	void delete(Integer id);
}
