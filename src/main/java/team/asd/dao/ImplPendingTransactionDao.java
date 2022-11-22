package team.asd.dao;

import org.springframework.stereotype.Repository;
import team.asd.entity.PendingTransaction;

@Repository
public class ImplPendingTransactionDao implements PendingTransactionDao {
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
