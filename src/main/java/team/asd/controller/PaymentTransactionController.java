package team.asd.controller;

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
import team.asd.dto.PaymentTransactionDTO;
import team.asd.entity.PaymentTransaction;
import team.asd.service.PaymentTransactionService;
import team.asd.util.ConverterUtil;

import java.util.Objects;

@RestController
@RequestMapping("/payment_transaction")
public class PaymentTransactionController {
	private final PaymentTransactionService paymentTransactionService = new PaymentTransactionService(null);

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getPaymentTransactionById(@PathVariable(value = "id") Integer id) {
		try {
			return new ResponseEntity<>(ConverterUtil.convertToDto(paymentTransactionService.readById(id)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createPaymentTransaction(@RequestBody PaymentTransactionDTO paymentTransactionDTO) {
		try {
			PaymentTransaction paymentTransaction = paymentTransactionService.create(ConverterUtil.convertToEntity(paymentTransactionDTO));
			return new ResponseEntity<>(ConverterUtil.convertToDto(paymentTransaction), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updatePaymentTransaction(@RequestBody PaymentTransactionDTO paymentTransactionDTO) {
		try {
			PaymentTransaction paymentTransaction = paymentTransactionService.update(
					Objects.requireNonNull(ConverterUtil.convertToEntity(paymentTransactionDTO)));
			return new ResponseEntity<>(ConverterUtil.convertToDto(paymentTransaction), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deletePaymentTransaction(@PathVariable(value = "id") Integer id) {
		try {
			paymentTransactionService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
