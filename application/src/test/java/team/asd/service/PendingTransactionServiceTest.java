package team.asd.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import team.asd.constant.PendingTransactionStatus;
import team.asd.dao.PendingTransactionDao;
import team.asd.entity.PendingTransaction;
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
class PendingTransactionServiceTest {

	@Mock
	private PendingTransactionDao pendingTransactionDao;
	private PendingTransaction pendingTransaction;
	private static PendingTransactionService pendingTransactionService;
	private static PendingTransaction mockPendingTransaction;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		pendingTransactionService = new PendingTransactionService(pendingTransactionDao);
		mockPendingTransaction = null;
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
		Mockito.when(pendingTransactionDao.readById(1))
				.thenReturn(pendingTransaction);

		PendingTransaction pendingTransaction1 = pendingTransactionService.readById(1);

		assertNotNull(pendingTransaction1, "PendingTransaction cannot be null");
		assertEquals(1, pendingTransaction1.getId(), "Ids are not equals");
		assertNotEquals(5, pendingTransaction1.getId(), "Ids are equals");
		assertNull(pendingTransactionService.readById(10), "Null should be returned");
		Mockito.verify(pendingTransactionDao, atLeast(2))
				.readById(any(Integer.class));
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
		Mockito.doAnswer(invocation -> {
					mockPendingTransaction = pendingTransaction;
					return null;
				})
				.when(pendingTransactionDao)
				.create(Mockito.any(PendingTransaction.class));

		assertNull(mockPendingTransaction, "Null should be returned");
		assertThrows(ValidationException.class, () -> pendingTransactionService.create(mockPendingTransaction), "Validation exception should be thrown");

		pendingTransactionService.create(pendingTransaction);

		assertNotNull(mockPendingTransaction, "PendingTransaction cannot be null");
		assertEquals(1, mockPendingTransaction.getId(), "Ids are not equals");
		assertEquals(2, mockPendingTransaction.getStatus()
				.getValue(), "Status values are not equals");
		Mockito.verify(pendingTransactionDao, atLeast(1))
				.create(any(PendingTransaction.class));
	}

	@Test
	void testCreateWithNull() {
		assertThrows(ValidationException.class, () -> pendingTransactionService.create(null), "Validation exception should be thrown");
	}

	@Test
	void testCreateWithNullReservationIdAndNullStatus() {
		pendingTransaction.setReservationId(null);
		pendingTransaction.setStatus(null);
		assertThrows(ValidationException.class, () -> pendingTransactionService.create(pendingTransaction), "Validation exception should be thrown");
	}

	@Test
	void testUpdate() {
		mockPendingTransaction = PendingTransaction.builder()
				.id(1)
				.reservationId(2)
				.status(PendingTransactionStatus.Active)
				.build();

		Mockito.doAnswer(invocation -> {
					mockPendingTransaction = pendingTransaction;
					return null;
				})
				.when(pendingTransactionDao)
				.update(Mockito.any(PendingTransaction.class));

		assertNotEquals(pendingTransaction.getStatus(), mockPendingTransaction.getStatus());
		assertNotEquals(pendingTransaction.getReservationId(), mockPendingTransaction.getReservationId());

		pendingTransaction.setReservationId(3);
		pendingTransaction.setStatus(PendingTransactionStatus.Failed);

		pendingTransactionService.update(pendingTransaction);

		assertEquals(pendingTransaction.getReservationId(), mockPendingTransaction.getReservationId());
		assertEquals(pendingTransaction.getStatus(), mockPendingTransaction.getStatus());

		Mockito.verify(pendingTransactionDao)
				.update(Mockito.any());
	}

	@Test
	void testCheckPendingTransactionNull() {
		assertThrows(ValidationException.class, () -> pendingTransactionService.create(null), "Validation exception should be thrown");
	}

	@Test
	void testDelete() {
		assertDoesNotThrow(() -> pendingTransactionService.delete(1), "Validation should be passed");

		pendingTransactionService.delete(1);
		verify(pendingTransactionDao, atLeast(1)).delete(Mockito.anyInt());
	}

	@Test
	void testDeleteWithNullAndWrongId() {
		assertThrows(ValidationException.class, () -> pendingTransactionService.delete(null), "Validation exception should be thrown");
		assertThrows(ValidationException.class, () -> pendingTransactionService.delete(0), "Validation exception should be thrown");
	}
}