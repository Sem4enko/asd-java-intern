package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.entity.PaymentTransaction;

@Mapper
public interface PaymentTransactionMapper {
	PaymentTransaction readById(Integer id);
	void create(PaymentTransaction paymentTransaction);
	void update(PaymentTransaction paymentTransaction);
	void delete(Integer id);
}
