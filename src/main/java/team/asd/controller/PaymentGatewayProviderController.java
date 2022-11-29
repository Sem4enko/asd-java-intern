package team.asd.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.asd.dto.PaymentGatewayProviderDto;
import team.asd.entity.PaymentGatewayProvider;
import team.asd.service.PaymentGatewayProviderService;
import team.asd.util.PaymentGatewayProviderConverterUtil;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/payment_gateway_provider")
@AllArgsConstructor
public class PaymentGatewayProviderController {
	private final PaymentGatewayProviderService paymentGatewayProviderService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getPaymentGatewayProviderById(@PathVariable(value = "id") Integer id) {
		return new ResponseEntity<>(PaymentGatewayProviderConverterUtil.convertToDto(paymentGatewayProviderService.readById(id)), HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createPaymentGatewayProvider(@RequestBody @Valid PaymentGatewayProviderDto paymentGatewayProviderDto) {
		PaymentGatewayProvider paymentGatewayProvider = PaymentGatewayProviderConverterUtil.convertToEntity(paymentGatewayProviderDto);
		paymentGatewayProviderService.create(paymentGatewayProvider);
		return new ResponseEntity<>(PaymentGatewayProviderConverterUtil.convertToDto(paymentGatewayProvider), HttpStatus.OK);
	}

	@PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updatePaymentGatewayProvider(@RequestBody @Valid PaymentGatewayProviderDto paymentGatewayProviderDto) {
		PaymentGatewayProvider paymentGatewayProvider = PaymentGatewayProviderConverterUtil.convertToEntity(paymentGatewayProviderDto);
		paymentGatewayProviderService.update(Objects.requireNonNull(paymentGatewayProvider));
		return new ResponseEntity<>(PaymentGatewayProviderConverterUtil.convertToDto(paymentGatewayProvider), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deletePaymentGatewayProvider(@PathVariable(value = "id") Integer id) {
		paymentGatewayProviderService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
