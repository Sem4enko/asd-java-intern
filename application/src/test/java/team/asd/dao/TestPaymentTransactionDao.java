package team.asd.dao;

import team.asd.entity.PaymentTransaction;

import java.util.List;

public class TestPaymentTransactionDao implements PaymentTransactionDao{
	@Override
	public PaymentTransaction readById(Integer id) {
		return null;
	}

	@Override
	public void create(PaymentTransaction paymentTransaction) {
	}

	@Override
	public void update(PaymentTransaction paymentTransaction) {

	}

	@Override
	public void delete(Integer id) {

	}

	@Override
	public List<PaymentTransaction> readByReservationIdStatus(Integer reservationId, String status) {
		return null;
	}

	@Override
	public List<PaymentTransaction> readByChargeTypePartnerIdFundsHolderStatus(String chargeType, Integer partnerId, Integer fundsHolder, String status) {
		return null;
	}

	@Override
	public void createList(List<PaymentTransaction> paymentTransactions) {

	}
}
