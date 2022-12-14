package team.asd.dao;

import team.asd.entity.PaymentTransaction;

import java.util.List;

public interface PaymentTransactionDao {
	PaymentTransaction readById(Integer id);

	void create(PaymentTransaction paymentTransaction);

	void update(PaymentTransaction paymentTransaction);

	void delete(Integer id);

	List<PaymentTransaction> readByReservationIdStatus(Integer reservationId, String status);

	List<PaymentTransaction> readByChargeTypePartnerIdFundsHolderStatus(String chargeType, Integer partnerId, Integer fundsHolder, String status);

	void createList(List<PaymentTransaction> paymentTransactions);
}
