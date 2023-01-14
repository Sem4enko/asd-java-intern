package team.asd.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import team.asd.constant.FundsHolderEnum;
import team.asd.constant.PaymentTransactionStatus;
import team.asd.dao.ChannelPartnerDao;
import team.asd.dao.PartyDao;
import team.asd.dao.PaymentTransactionDao;
import team.asd.dao.ReservationDao;
import team.asd.entity.PaymentTransaction;
import team.asd.exception.ValidationException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
class PaymentTransactionServiceTest {
	@Mock
	private PaymentTransactionDao paymentTransactionDao;
	private PaymentTransaction paymentTransaction;
	private static PaymentTransactionService paymentTransactionService;
	private static PaymentTransaction mockPaymentTransaction;
	@Mock
	private ReservationDao reservationDao;
	@Mock
	private PartyDao partyDao;
	@Mock
	private ChannelPartnerDao channelPartnerDao;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		paymentTransactionService = new PaymentTransactionService(paymentTransactionDao, reservationDao, partyDao, channelPartnerDao);
		mockPaymentTransaction = null;
		paymentTransaction = PaymentTransaction.builder()
				.id(1)
				.paymentProvider(2)
				.createdDate(LocalDate.now())
				.reservationId(3)
				.fundsHolder(FundsHolderEnum.PropertyManager)
				.status(PaymentTransactionStatus.Accepted)
				.currency("USD")
				.build();
	}

	@Test
	void testReadById() throws ValidationException {
		Mockito.when(paymentTransactionDao.readById(1))
				.thenReturn(paymentTransaction);

		PaymentTransaction paymentTransaction1 = paymentTransactionService.readById(1);

		assertNotNull(paymentTransaction1, "PaymentTransaction cannot be null");
		assertEquals(1, paymentTransaction1.getId(), "Ids are not equals");
		assertNotEquals(5, paymentTransaction1.getId(), "Ids are equals");
		assertNull(paymentTransactionService.readById(10), "Null should be returned");
		Mockito.verify(paymentTransactionDao, atLeast(2))
				.readById(Mockito.anyInt());
	}

	@Test
	void testReadByIdWithNull() {
		assertThrows(ValidationException.class, () -> paymentTransactionService.readById(null), "Validation exception should be thrown");
	}

	@Test
	void testReadByIdWithWrongId() {
		assertThrows(ValidationException.class, () -> paymentTransactionService.readById(0), "Validation exception should be thrown");
		assertThrows(ValidationException.class, () -> paymentTransactionService.readById(-1), "Validation exception should be thrown");
	}

	@Test
	void testCreate() throws ValidationException {
		Mockito.doAnswer(invocation -> {
					mockPaymentTransaction = paymentTransaction;
					return null;
				})
				.when(paymentTransactionDao)
				.create(Mockito.any(PaymentTransaction.class));

		assertNull(mockPaymentTransaction, "Null should be returned");
		assertThrows(ValidationException.class, () -> paymentTransactionService.create(mockPaymentTransaction), "Validation exception should be thrown");

		paymentTransactionService.create(paymentTransaction);

		assertNotNull(mockPaymentTransaction, "PaymentTransaction cannot be null");
		assertEquals(1, mockPaymentTransaction.getId(), "Ids are not equals");
		assertEquals(0, mockPaymentTransaction.getFundsHolder()
				.getValue(), "FundsHolder values are not equals");
		Mockito.verify(paymentTransactionDao, atLeast(1))
				.create(any(PaymentTransaction.class));
	}

	@Test
	void testCreateWithNull() {
		assertThrows(ValidationException.class, () -> paymentTransactionService.create(null), "Validation exception should be thrown");
	}

	@Test
	void testCreateWithNullReservationIdAndNullStatus() {
		paymentTransaction.setReservationId(null);
		paymentTransaction.setStatus(null);
		assertThrows(ValidationException.class, () -> paymentTransactionService.create(paymentTransaction), "Validation exception should be thrown");
	}

	@Test
	void testUpdate() {
		mockPaymentTransaction = PaymentTransaction.builder()
				.id(1)
				.paymentProvider(1)
				.status(PaymentTransactionStatus.Failed)
				.build();
		Mockito.doAnswer(invocation -> {
					mockPaymentTransaction = paymentTransaction;
					return null;
				})
				.when(paymentTransactionDao)
				.update(Mockito.any(PaymentTransaction.class));

		assertNotEquals(paymentTransaction.getStatus(), mockPaymentTransaction.getStatus());
		assertNotEquals(paymentTransaction.getPaymentProvider(), mockPaymentTransaction.getPaymentProvider());

		paymentTransaction.setPaymentProvider(3);
		paymentTransaction.setStatus(PaymentTransactionStatus.Failed);

		paymentTransactionService.update(paymentTransaction);

		assertEquals(paymentTransaction.getPaymentProvider(), mockPaymentTransaction.getPaymentProvider());
		assertEquals(paymentTransaction.getStatus(), mockPaymentTransaction.getStatus());

		Mockito.verify(paymentTransactionDao)
				.update(Mockito.any());
	}

	@Test
	void testCheckPaymentTransactionNull() {
		assertThrows(ValidationException.class, () -> paymentTransactionService.create(null), "Validation exception should be thrown");
	}

	@Test
	void testDelete() {
		assertDoesNotThrow(() -> paymentTransactionService.delete(2), "Validation should be passed");

		paymentTransactionService.delete(1);
		verify(paymentTransactionDao, atLeast(1)).delete(Mockito.anyInt());
	}

	@Test
	void testDeleteWithNull() {
		assertThrows(ValidationException.class, () -> paymentTransactionService.delete(null), "Validation exception should be thrown");
	}

}