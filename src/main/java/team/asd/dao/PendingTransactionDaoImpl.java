package team.asd.dao;

import org.springframework.stereotype.Repository;
import team.asd.entity.PendingTransaction;

@Repository
public class PendingTransactionDaoImpl implements PendingTransactionDao {
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
}
