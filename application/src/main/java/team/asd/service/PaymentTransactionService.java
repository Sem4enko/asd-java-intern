package team.asd.service;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import team.asd.dao.ChannelPartnerDao;
import team.asd.dao.PartyDao;
import team.asd.dao.PaymentTransactionDao;
import team.asd.dao.ReservationDao;
import team.asd.dto.TransactionReservationPartyDto;
import team.asd.entity.ChannelPartner;
import team.asd.entity.Party;
import team.asd.entity.PaymentTransaction;
import team.asd.entity.Reservation;
import team.asd.exception.ValidationException;
import team.asd.util.PaymentTransactionConverterUtil;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PaymentTransactionService {
	PaymentTransactionDao paymentTransactionDao;
	ReservationDao reservationDao;
	PartyDao partyDao;
	ChannelPartnerDao channelPartnerDao;

	public PaymentTransaction readById(Integer id) throws ValidationException {
		checkId(id);
		return paymentTransactionDao.readById(id);
	}

	public void create(PaymentTransaction paymentTransaction) throws ValidationException {
		checkPaymentTransaction(paymentTransaction);
		paymentTransactionDao.create(paymentTransaction);
	}

	public void update(PaymentTransaction paymentTransaction) throws ValidationException {
		checkId(paymentTransaction.getId());
		paymentTransactionDao.update(paymentTransaction);
	}

	public void delete(Integer id) throws ValidationException {
		checkId(id);
		paymentTransactionDao.delete(id);
	}

	public List<PaymentTransaction> readByReservationIdStatus(Integer reservationId, String status) {
		if (ObjectUtils.anyNull(reservationId, status)) {
			throw new ValidationException("Required parameters are not provided");
		}
		return paymentTransactionDao.readByReservationIdStatus(reservationId, status);
	}

	public List<PaymentTransaction> readByChargeTypePartnerIdFundsHolderStatus(String chargeType, Integer partnerId, Integer fundsHolder, String status) {
		if (ObjectUtils.isEmpty(partnerId)) {
			throw new ValidationException("Partner id is not provided");
		}
		return paymentTransactionDao.readByChargeTypePartnerIdFundsHolderStatus(chargeType, partnerId, fundsHolder, status);
	}

	public void createList(List<PaymentTransaction> paymentTransactions) {
		if (CollectionUtils.isEmpty(paymentTransactions)) {
			throw new ValidationException("Empty list is provided");
		}
		paymentTransactions.forEach(this::checkPaymentTransaction);
		paymentTransactionDao.createList(paymentTransactions);
	}

	public TransactionReservationPartyDto readTransactionReservationPartyDtoById(Integer paymentTransactionId) {
		checkId(paymentTransactionId);

		PaymentTransaction paymentTransaction = paymentTransactionDao.readById(paymentTransactionId);
		if (Objects.isNull(paymentTransaction)) {
			return null;
		}

		if(ObjectUtils.anyNull(paymentTransaction.getReservationId(),paymentTransaction.getSupplierId(),paymentTransaction.getPartnerId())){
			throw new ValidationException("Wrong field was provided");
		}

		Reservation reservation = reservationDao.readById(paymentTransaction.getReservationId());
		Party party = partyDao.readById(paymentTransaction.getSupplierId());
		ChannelPartner channelPartner = channelPartnerDao.readById(paymentTransaction.getPartnerId());

		return TransactionReservationPartyDto.builder()
				.paymentTransactionId(paymentTransaction.getId())
				.paymentTransactionState(PaymentTransactionConverterUtil.convertStatusToString(paymentTransaction.getStatus()))
				.reservationState(String.valueOf(reservation.getState()))
				.reservationQuote(reservation.getQuote())
				.reservationCurrency(reservation.getCurrency())
				.supplierName(party.getName())
				.supplierUserType(String.valueOf(party.getUserType()))
				.partnerPayment(channelPartner.getCommission())
				.finalAmount(reservation.getQuote())
				.paymentTransactionCurrency(paymentTransaction.getCurrency())
				.build();
	}

	private void checkId(Integer id) throws ValidationException {
		if (ObjectUtils.isEmpty(id) || id <= 0) {
			throw new ValidationException("Wrong id was provided");
		}
	}

	private void checkPaymentTransaction(PaymentTransaction paymentTransaction) throws ValidationException {
		if (Objects.isNull(paymentTransaction) || ObjectUtils.anyNull(paymentTransaction.getPaymentProvider(), paymentTransaction.getReservationId(),
				paymentTransaction.getFundsHolder(), paymentTransaction.getStatus(), paymentTransaction.getCurrency())) {
			throw new ValidationException("Wrong field was provided");
		}
	}
}
