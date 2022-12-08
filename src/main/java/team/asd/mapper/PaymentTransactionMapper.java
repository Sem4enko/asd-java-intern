package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.entity.PaymentTransaction;

import java.util.List;

@Mapper
public interface PaymentTransactionMapper {
	PaymentTransaction readById(Integer id);

	void create(PaymentTransaction paymentTransaction);

	void update(PaymentTransaction paymentTransaction);

	void delete(Integer id);

	List<PaymentTransaction> readByReservationIdStatus(Integer reservationId, String status);

	List<PaymentTransaction> readByChargeTypePartnerIdFundsHolderStatus(String chargeType, Integer partnerId, Integer fundsHolder, String status);

	void createList(List<PaymentTransaction> managerToChannels);
}
