package team.asd.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import team.asd.constant.PaymentProcessingTypeEnum;
import team.asd.constant.SupportSplitPaymentEnum;
import team.asd.dao.PaymentGatewayProviderDao;
import team.asd.entity.PaymentGatewayProvider;
import team.asd.exception.ValidationException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

public class PaymentGatewayProviderServiceTest {

	@Mock
	private PaymentGatewayProviderDao paymentGatewayProviderDao;
	private PaymentGatewayProvider paymentGatewayProvider;

	private static PaymentGatewayProviderService paymentGatewayProviderService;

	private static PaymentGatewayProvider mockPaymentGatewayProvider;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		paymentGatewayProviderService = new PaymentGatewayProviderService(paymentGatewayProviderDao);
		mockPaymentGatewayProvider = null;
		paymentGatewayProvider = PaymentGatewayProvider.builder()
				.id(1)
				.name("paymentGatewayProvider")
				.fee(0.0)
				.autoPay(PaymentProcessingTypeEnum.ManualPayment)
				.supportSplitPayment(SupportSplitPaymentEnum.FullPaymentOnly)
				.build();
	}

	@Test
	void testReadById() throws ValidationException {
		Mockito.when(paymentGatewayProviderDao.readById(1))
				.thenReturn(paymentGatewayProvider);

		PaymentGatewayProvider paymentGatewayProvider1 = paymentGatewayProviderService.readById(1);

		assertNotNull(paymentGatewayProvider1, "PaymentGatewayProvider cannot be null");
		assertEquals(1, paymentGatewayProvider1.getId(), "Ids are not equalS");
		assertNotEquals(5, paymentGatewayProvider1.getId(), "Ids are equalS");
		assertNull(paymentGatewayProviderService.readById(10), "Null should be returned");
		Mockito.verify(paymentGatewayProviderDao, atLeast(2))
				.readById(Mockito.anyInt());
	}

	@Test
	void testReadByIdWithNull() {
		assertThrows(ValidationException.class, () -> paymentGatewayProviderService.readById(null), "Validation exception should be thrown");
	}

	@Test
	void testReadByIdWithWrongId() {
		assertThrows(ValidationException.class, () -> paymentGatewayProviderService.readById(0), "Validation exception should be thrown");
		assertThrows(ValidationException.class, () -> paymentGatewayProviderService.readById(-1), "Validation exception should be thrown");
	}

	@Test
	void testCreate() throws ValidationException {
		Mockito.doAnswer(invocation -> {
					mockPaymentGatewayProvider = paymentGatewayProvider;
					return null;
				})
				.when(paymentGatewayProviderDao)
				.create(Mockito.any(PaymentGatewayProvider.class));

		assertNull(mockPaymentGatewayProvider, "Null should be returned");
		assertThrows(ValidationException.class, () -> paymentGatewayProviderService.create(mockPaymentGatewayProvider),
				"Validation exception should be thrown");

		paymentGatewayProviderService.create(paymentGatewayProvider);

		assertNotNull(mockPaymentGatewayProvider, "PaymentGatewayProvider cannot be null");
		assertEquals(1, mockPaymentGatewayProvider.getId(), "Ids are not equals");
		assertEquals(0, mockPaymentGatewayProvider.getAutoPay()
				.getValue(), "PaymentProcessingType values are not equals");
		Mockito.verify(paymentGatewayProviderDao, atLeast(1))
				.create(any(PaymentGatewayProvider.class));
	}

	@Test
	void testCreateWithNull() {
		assertThrows(ValidationException.class, () -> paymentGatewayProviderService.create(null), "Validation exception should be thrown");
	}

	@Test
	void testCreateWithNullReservationIdAndNullStatus() {
		paymentGatewayProvider.setName(null);
		assertThrows(ValidationException.class, () -> paymentGatewayProviderService.create(paymentGatewayProvider), "Validation exception should be thrown");
	}

	@Test
	void testUpdate() {
		mockPaymentGatewayProvider = PaymentGatewayProvider.builder()
				.id(1)
				.autoPay(PaymentProcessingTypeEnum.AutoPayment)
				.supportSplitPayment(SupportSplitPaymentEnum.SupportSplitPayment)
				.build();
		Mockito.doAnswer(invocation -> {
					mockPaymentGatewayProvider = paymentGatewayProvider;
					return null;
				})
				.when(paymentGatewayProviderDao)
				.update(Mockito.any(PaymentGatewayProvider.class));

		assertNotEquals(paymentGatewayProvider.getAutoPay(), mockPaymentGatewayProvider.getAutoPay());
		assertNotEquals(paymentGatewayProvider.getSupportSplitPayment(), mockPaymentGatewayProvider.getSupportSplitPayment());

		paymentGatewayProvider.setAutoPay(PaymentProcessingTypeEnum.AutoPayment);
		paymentGatewayProvider.setSupportSplitPayment(SupportSplitPaymentEnum.SupportSplitPayment);

		paymentGatewayProviderService.update(paymentGatewayProvider);

		assertEquals(paymentGatewayProvider.getAutoPay(), mockPaymentGatewayProvider.getAutoPay());
		assertEquals(paymentGatewayProvider.getSupportSplitPayment(), mockPaymentGatewayProvider.getSupportSplitPayment());

		Mockito.verify(paymentGatewayProviderDao)
				.update(Mockito.any());
	}

	@Test
	void testCheckPaymentGatewayProviderNull() {
		assertThrows(ValidationException.class, () -> paymentGatewayProviderService.create(null), "Validation exception should be thrown");
	}

	@Test
	void testDelete() {
		assertDoesNotThrow(() -> paymentGatewayProviderService.delete(2), "Validation should be passed");

		paymentGatewayProviderService.delete(1);
		verify(paymentGatewayProviderDao, atLeast(1)).delete(Mockito.anyInt());
	}

	@Test
	void testDeleteWithNull() {
		assertThrows(ValidationException.class, () -> paymentGatewayProviderService.delete(null), "Validation exception should be thrown");
	}
}

