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
import team.asd.dto.PaymentTransactionDto;
import team.asd.entity.PaymentTransaction;
import team.asd.service.PaymentTransactionService;
import team.asd.util.ConverterUtil;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/payment_transaction")
@AllArgsConstructor
public class PaymentTransactionController {
	private final PaymentTransactionService paymentTransactionService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getPaymentTransactionById(@PathVariable(value = "id") Integer id) {
		return new ResponseEntity<>(ConverterUtil.convertToDto(paymentTransactionService.readById(id)), HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createPaymentTransaction(@RequestBody @Valid PaymentTransactionDto paymentTransactionDTO) {
		PaymentTransaction paymentTransaction = ConverterUtil.convertToEntity(paymentTransactionDTO);
		paymentTransactionService.create(paymentTransaction);
		return new ResponseEntity<>(ConverterUtil.convertToDto(paymentTransaction), HttpStatus.OK);
	}

	@PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updatePaymentTransaction(@RequestBody @Valid PaymentTransactionDto paymentTransactionDTO) {
		PaymentTransaction paymentTransaction = ConverterUtil.convertToEntity(paymentTransactionDTO);
		paymentTransactionService.update(Objects.requireNonNull(paymentTransaction));
		return new ResponseEntity<>(ConverterUtil.convertToDto(paymentTransaction), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deletePaymentTransaction(@PathVariable(value = "id") Integer id) {
		paymentTransactionService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
