package team.asd.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.asd.constant.FundsHolderEnum;
import team.asd.constant.PaymentTransactionStatus;
import team.asd.dao.TestPaymentTransactionDao;
import team.asd.entity.PaymentTransaction;
import team.asd.exception.ValidationException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaymentTransactionServiceTest {
	private PaymentTransaction paymentTransaction;
	private static PaymentTransactionService paymentTransactionService;

	@BeforeAll
	public static void setUp() {
		paymentTransactionService = new PaymentTransactionService(new TestPaymentTransactionDao());
	}

	@BeforeEach
	public void initParty() {
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
		assertNull(paymentTransactionService.readById(1), "Null value should be returned");
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
		assertEquals(paymentTransaction, paymentTransactionService.create(paymentTransaction));
		assertDoesNotThrow(() -> paymentTransactionService.create(paymentTransaction), "Validation should be passed");
	}

	@Test
	void testCreateWithNull() {
		assertThrows(ValidationException.class, () -> paymentTransactionService.create(null), "Validation exception should be thrown");
	}

	@Test
	void testCreateWithNullId() {
		paymentTransaction.setId(null);
		assertThrows(ValidationException.class, () -> paymentTransactionService.create(paymentTransaction), "Validation exception should be thrown");
	}

	@Test
	void testCreateWithNullReservationIdAndNullStatus() {
		paymentTransaction.setReservationId(null);
		paymentTransaction.setStatus(null);
		assertThrows(ValidationException.class, () -> paymentTransactionService.create(paymentTransaction), "Validation exception should be thrown");
	}

	@Test
	void testUpdate() {
		assertDoesNotThrow(() -> paymentTransactionService.update(paymentTransaction), "Validation should be passed");
	}

	@Test
	void testUpdateWithNull() {
		assertThrows(ValidationException.class, () -> paymentTransactionService.update(null), "Validation exception should be thrown");
	}

	@Test
	void testCheckPaymentTransactionNull() {
		paymentTransaction.setId(null);
		assertThrows(ValidationException.class, () -> paymentTransactionService.create(paymentTransaction), "Validation exception should be thrown");
	}

	@Test
	void testDelete() {
		assertDoesNotThrow(() -> paymentTransactionService.delete(2), "Validation should be passed");
	}

	@Test
	void testDeleteWithNull() {
		assertThrows(ValidationException.class, () -> paymentTransactionService.delete(null), "Validation exception should be thrown");
	}

}