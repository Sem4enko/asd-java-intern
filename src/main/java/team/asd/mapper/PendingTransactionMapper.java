package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.entity.PendingTransaction;

@Mapper
public interface PendingTransactionMapper {
	PendingTransaction readById(Integer id);
	void create(PendingTransaction pendingTransaction);
	void update(PendingTransaction pendingTransaction);
	void delete(Integer id);
}
