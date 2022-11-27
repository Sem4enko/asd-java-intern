package team.asd.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import team.asd.entity.PendingTransaction;
import team.asd.mapper.PendingTransactionMapper;

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
}
