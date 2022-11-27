package team.asd.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.asd.constant.PaymentProcessingTypeEnum;
import team.asd.constant.SupportSplitPaymentEnum;
import team.asd.dao.TestPaymentGatewayProviderDao;
import team.asd.entity.PaymentGatewayProvider;
import team.asd.exception.ValidationException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentGatewayProviderServiceTest {
	private PaymentGatewayProvider paymentGatewayProvider;

	private static PaymentGatewayProviderService paymentGatewayProviderService;

	@BeforeAll
	public static void setUp() {
		paymentGatewayProviderService = new PaymentGatewayProviderService(new TestPaymentGatewayProviderDao());
	}

	@BeforeEach
	public void initPaymentTransaction() {
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
		assertNull(paymentGatewayProviderService.readById(1), "Null value should be returned");
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
		assertDoesNotThrow(() -> paymentGatewayProviderService.create(paymentGatewayProvider), "Validation should be passed");
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
		assertDoesNotThrow(() -> paymentGatewayProviderService.update(paymentGatewayProvider), "Validation should be passed");
	}

	@Test
	void testCheckPaymentTransactionNull() {
		assertThrows(ValidationException.class, () -> paymentGatewayProviderService.create(null), "Validation exception should be thrown");
	}

	@Test
	void testDelete() {
		assertDoesNotThrow(() -> paymentGatewayProviderService.delete(2), "Validation should be passed");
	}

	@Test
	void testDeleteWithNull() {
		assertThrows(ValidationException.class, () -> paymentGatewayProviderService.delete(null), "Validation exception should be thrown");
	}
}

