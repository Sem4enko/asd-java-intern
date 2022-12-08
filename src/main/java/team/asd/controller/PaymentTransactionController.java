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
import team.asd.util.PaymentTransactionConverterUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payment_transaction")
@AllArgsConstructor
public class PaymentTransactionController {
	private final PaymentTransactionService paymentTransactionService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getPaymentTransactionById(@PathVariable(value = "id") Integer id) {
		return new ResponseEntity<>(PaymentTransactionConverterUtil.convertToDto(paymentTransactionService.readById(id)), HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createPaymentTransaction(@RequestBody @Valid PaymentTransactionDto paymentTransactionDTO) {
		PaymentTransaction paymentTransaction = PaymentTransactionConverterUtil.convertToEntity(paymentTransactionDTO);

		paymentTransactionService.create(paymentTransaction);

		return new ResponseEntity<>(PaymentTransactionConverterUtil.convertToDto(paymentTransaction), HttpStatus.OK);
	}

	@PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updatePaymentTransaction(@RequestBody @Valid PaymentTransactionDto paymentTransactionDTO) {
		PaymentTransaction paymentTransaction = PaymentTransactionConverterUtil.convertToEntity(paymentTransactionDTO);

		paymentTransactionService.update(Objects.requireNonNull(paymentTransaction));

		return new ResponseEntity<>(PaymentTransactionConverterUtil.convertToDto(paymentTransaction), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deletePaymentTransaction(@PathVariable(value = "id") Integer id) {
		paymentTransactionService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/list")
	public ResponseEntity<List<PaymentTransactionDto>> createList(@RequestBody List<@Valid PaymentTransactionDto> paymentTransactionDtoList) {
		List<PaymentTransaction> paymentTransactions = paymentTransactionDtoList.stream()
				.map(PaymentTransactionConverterUtil::convertToEntity)
				.collect(Collectors.toList());

		paymentTransactionService.createList(paymentTransactions);

		return new ResponseEntity<>(paymentTransactions.stream()
				.map(PaymentTransactionConverterUtil::convertToDto)
				.collect(Collectors.toList()), HttpStatus.OK);
	}

	@GetMapping("/list/reservation_id/{reservationId}/status/{status}")
	public ResponseEntity<List<PaymentTransactionDto>> readByReservationIdStatus(@PathVariable(value = "reservationId") Integer reservationId,
			@PathVariable(value = "status") String status) {
		return new ResponseEntity<>(paymentTransactionService.readByReservationIdStatus(reservationId, status)
				.stream()
				.map(PaymentTransactionConverterUtil::convertToDto)
				.collect(Collectors.toList()), HttpStatus.OK);
	}

	@GetMapping("/list/charge_type/{chargeType}/partner_id/{partnerId}/funds_holder/{fundsHolder}/status/{status}")
	public ResponseEntity<List<PaymentTransactionDto>> readByChargeTypePartnerIdFundsHolderStatus(@PathVariable(value = "chargeType") String chargeType,
			@PathVariable(value = "partnerId") Integer partnerId, @PathVariable(value = "fundsHolder") Integer fundsHolder,
			@PathVariable(value = "status") String status) {
		return new ResponseEntity<>(paymentTransactionService.readByChargeTypePartnerIdFundsHolderStatus(chargeType, partnerId, fundsHolder, status)
				.stream()
				.map(PaymentTransactionConverterUtil::convertToDto)
				.collect(Collectors.toList()), HttpStatus.OK);
	}

}
