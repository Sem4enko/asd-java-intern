package team.asd.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import team.asd.dao.PaymentGatewayProviderDao;
import team.asd.entity.PaymentGatewayProvider;
import team.asd.exception.ValidationException;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PaymentGatewayProviderService {
	PaymentGatewayProviderDao paymentGatewayProviderDao;

	public PaymentGatewayProvider readById(Integer id) throws ValidationException {
		checkId(id);
		return paymentGatewayProviderDao.readById(id);
	}

	public void create(PaymentGatewayProvider paymentGatewayProvider) throws ValidationException {
		checkPaymentGatewayProvider(paymentGatewayProvider);
		paymentGatewayProviderDao.create(paymentGatewayProvider);
	}

	public void update(PaymentGatewayProvider paymentGatewayProvider) throws ValidationException {
		checkId(paymentGatewayProvider.getId());
		paymentGatewayProviderDao.update(paymentGatewayProvider);
	}

	public void delete(Integer id) throws ValidationException {
		checkId(id);
		paymentGatewayProviderDao.delete(id);
	}

	public List<PaymentGatewayProvider> readByNameAutoPaySupportSplitPayment(Integer supportSplitPayment, Integer autoPay, String name) {
		if (ObjectUtils.anyNull(supportSplitPayment, autoPay, name)) {
			throw new ValidationException("Required parameters are not provided");
		}
		return paymentGatewayProviderDao.readByNameAutoPaySupportSplitPayment(supportSplitPayment, autoPay, name);
	}

	public List<PaymentGatewayProvider> readWithPaymentTransaction(Integer transactionCount) {
		if (paymentGatewayProviderDao.readWithPaymentTransaction()
				.size() < transactionCount) {
			throw new ValidationException("Count of providers is less than transaction count");
		}
		return paymentGatewayProviderDao.readWithPaymentTransaction();
	}

	private void checkId(Integer id) throws ValidationException {
		if (ObjectUtils.isEmpty(id) || id <= 0) {
			throw new ValidationException("Wrong id was provided");
		}
	}

	private void checkPaymentGatewayProvider(PaymentGatewayProvider paymentGatewayProvider) throws ValidationException {
		if (Objects.isNull(paymentGatewayProvider) || ObjectUtils.anyNull(paymentGatewayProvider.getName(), paymentGatewayProvider.getFee(),
				paymentGatewayProvider.getAutoPay(), paymentGatewayProvider.getSupportSplitPayment())) {
			throw new ValidationException("Wrong field was provided");
		}
	}
}
