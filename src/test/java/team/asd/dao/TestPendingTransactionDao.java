package team.asd.dao;

import team.asd.entity.PendingTransaction;

public class TestPendingTransactionDao implements PendingTransactionDao{
	@Override
	public PendingTransaction readById(Integer id) {
		return null;
	}

	@Override
	public PendingTransaction create(PendingTransaction pendingTransaction) {
		return pendingTransaction;
	}

	@Override
	public PendingTransaction update(PendingTransaction pendingTransaction) {
		return null;
	}

	@Override
	public void delete(Integer id) {

	}
}
