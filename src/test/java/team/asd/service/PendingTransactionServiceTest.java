package team.asd.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.asd.constant.PendingTransactionStatus;
import team.asd.dao.TestPendingTransactionDao;
import team.asd.entity.PendingTransaction;
import team.asd.exception.ValidationException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PendingTransactionServiceTest {

	private PendingTransaction pendingTransaction;
	private static PendingTransactionService pendingTransactionService;

	@BeforeAll
	public static void setUp() {
		pendingTransactionService = new PendingTransactionService(new TestPendingTransactionDao());
	}

	@BeforeEach
	public void initPaymentTransaction() {
		pendingTransaction = PendingTransaction.builder()
				.id(1)
				.reservationId(1)
				.chargeDate(LocalDate.now())
				.chargeAmount(22.22)
				.status(PendingTransactionStatus.Pending)
				.build();
	}

	@Test
	void testReadById() throws ValidationException {
		assertNull(pendingTransactionService.readById(1), "Null value should be returned");
	}

	@Test
	void testReadByIdWithNull() {
		assertThrows(ValidationException.class, () -> pendingTransactionService.readById(null), "Validation exception should be thrown");
	}

	@Test
	void testReadByIdWithWrongId() {
		assertThrows(ValidationException.class, () -> pendingTransactionService.readById(0), "Validation exception should be thrown");
		assertThrows(ValidationException.class, () -> pendingTransactionService.readById(-1), "Validation exception should be thrown");
	}

	@Test
	void testCreate() throws ValidationException {
		assertEquals(pendingTransaction, pendingTransactionService.create(pendingTransaction));
		assertDoesNotThrow(() -> pendingTransactionService.create(pendingTransaction), "Validation should be passed");
	}

	@Test
	void testCreateWithNull() {
		assertThrows(ValidationException.class, () -> pendingTransactionService.create(null), "Validation exception should be thrown");
	}

	@Test
	void testCreateWithNullId() {
		pendingTransaction.setId(null);
		assertThrows(ValidationException.class, () -> pendingTransactionService.create(pendingTransaction), "Validation exception should be thrown");
	}

	@Test
	void testCreateWithNullReservationIdAndNullStatus() {
		pendingTransaction.setReservationId(null);
		pendingTransaction.setStatus(null);
		assertThrows(ValidationException.class, () -> pendingTransactionService.create(pendingTransaction), "Validation exception should be thrown");
	}

	@Test
	void testUpdate() {
		assertDoesNotThrow(() -> pendingTransactionService.update(pendingTransaction), "Validation should be passed");
	}

	@Test
	void testCheckPaymentTransactionNull() {
		pendingTransaction.setId(null);
		assertThrows(ValidationException.class, () -> pendingTransactionService.create(pendingTransaction), "Validation exception should be thrown");
	}

	@Test
	void testDelete() {
		assertDoesNotThrow(() -> pendingTransactionService.delete(2), "Validation should be passed");
	}

	@Test
	void testDeleteWithNull() {
		assertThrows(ValidationException.class, () -> pendingTransactionService.delete(null), "Validation exception should be thrown");
	}
}