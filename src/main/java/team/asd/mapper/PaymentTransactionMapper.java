package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.entity.PaymentTransaction;

@Mapper
public interface PaymentTransactionMapper {
	PaymentTransaction readById(Integer id);

	PaymentTransaction create(PaymentTransaction paymentTransaction);
	PaymentTransaction update(PaymentTransaction paymentTransaction);
	void delete(Integer id);
}
