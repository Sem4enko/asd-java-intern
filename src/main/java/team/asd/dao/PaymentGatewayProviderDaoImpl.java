package team.asd.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import team.asd.entity.PaymentGatewayProvider;
import team.asd.mapper.PaymentGatewayProviderMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class PaymentGatewayProviderDaoImpl implements PaymentGatewayProviderDao {
	private PaymentGatewayProviderMapper paymentGatewayProviderMapper;

	@Override
	public PaymentGatewayProvider readById(Integer id) {
		return paymentGatewayProviderMapper.readById(id);
	}

	@Override
	public void create(PaymentGatewayProvider paymentGatewayProvider) {
		paymentGatewayProviderMapper.create(paymentGatewayProvider);
	}

	@Override
	public void update(PaymentGatewayProvider paymentGatewayProvider) {
		paymentGatewayProviderMapper.update(paymentGatewayProvider);
	}

	@Override
	public void delete(Integer id) {
		paymentGatewayProviderMapper.delete(id);
	}

	@Override
	public List<PaymentGatewayProvider> readByNameAutoPaySupportSplitPayment(Integer supportSplitPayment, Integer autoPay, String name) {
		return paymentGatewayProviderMapper.readByNameAutoPaySupportSplitPayment(supportSplitPayment,autoPay,name);
	}

}
