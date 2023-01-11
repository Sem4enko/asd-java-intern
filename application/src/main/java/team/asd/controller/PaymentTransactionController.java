package team.asd.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.asd.dto.PaymentTransactionDto;
import team.asd.dto.TransactionReservationPartyDto;
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
@ApiOperation("Payment Transaction Api")
public class PaymentTransactionController {
	private final PaymentTransactionService paymentTransactionService;

	@ApiOperation(value = "Get a Payment Transaction by id", notes = "Require integer path variable. Returns a Payment Transaction as per the id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 404, message = "Not found - The Payment Transaction was not found") })
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getPaymentTransactionById(@PathVariable(value = "id") Integer id) {
		return new ResponseEntity<>(PaymentTransactionConverterUtil.convertToDto(paymentTransactionService.readById(id)), HttpStatus.OK);
	}

	@ApiOperation(value = "Create a new Payment Transaction", notes = "Returns a new PaymentTransaction")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully added"), @ApiResponse(code = 404, message = "Invalid parameters were provided") })
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createPaymentTransaction(@RequestBody @Valid PaymentTransactionDto paymentTransactionDTO) {
		PaymentTransaction paymentTransaction = PaymentTransactionConverterUtil.convertToEntity(paymentTransactionDTO);

		paymentTransactionService.create(paymentTransaction);

		return new ResponseEntity<>(PaymentTransactionConverterUtil.convertToDto(paymentTransaction), HttpStatus.OK);
	}

	@ApiOperation(value = "Update particular Payment Transaction by id", notes = "Returns an updated Payment Transaction")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated"),
			@ApiResponse(code = 404, message = "Invalid parameters were provided") })
	@PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updatePaymentTransaction(@RequestBody @Valid PaymentTransactionDto paymentTransactionDTO) {
		PaymentTransaction paymentTransaction = PaymentTransactionConverterUtil.convertToEntity(paymentTransactionDTO);

		paymentTransactionService.update(Objects.requireNonNull(paymentTransaction));

		return new ResponseEntity<>(PaymentTransactionConverterUtil.convertToDto(paymentTransaction), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete Payment Transaction by id", notes = "Require integer path variable. Delete Payment Transaction from the table")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted"),
			@ApiResponse(code = 404, message = "Not found - The Payment Transaction was not found") })
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deletePaymentTransaction(@PathVariable(value = "id") Integer id) {
		paymentTransactionService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Add several new Payment Transactions to the table", notes = "Returns a list of Payment Transactions")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "List of Payment Transactions successfully created"),
			@ApiResponse(code = 404, message = "Invalid parameters were provided") })
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

	@ApiOperation(value = "Get a list of Payment Transactions by reservation id and status ", notes = "Require reservation id and status variables. Returns a list of matching payment transactions")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 404, message = "Not found - The payment transaction was not found") })
	@GetMapping("/list/reservation_id/{reservationId}/status/{status}")
	public ResponseEntity<List<PaymentTransactionDto>> readByReservationIdStatus(@PathVariable(value = "reservationId") Integer reservationId,
			@PathVariable(value = "status") String status) {
		return new ResponseEntity<>(paymentTransactionService.readByReservationIdStatus(reservationId, status)
				.stream()
				.map(PaymentTransactionConverterUtil::convertToDto)
				.collect(Collectors.toList()), HttpStatus.OK);
	}

	@ApiOperation(value = "Get a list of  Payment Transactions by charge type , partner id , funds holder and status ", notes = "Require charge type , partner id , funds holder and status variables. Returns a list of matching payment transactions")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 404, message = "Not found - The payment transaction was not found") })
	@GetMapping("/list/partner_id/{partnerId}")
	public ResponseEntity<List<PaymentTransactionDto>> readByChargeTypePartnerIdFundsHolderStatus(@PathVariable(value = "partnerId") Integer partnerId,
			@RequestParam(name = "chargeType", required = false) String chargeType, @RequestParam(name = "fundsHolder", required = false) Integer fundsHolder,
			@RequestParam(name = "status", required = false) String status) {
		return new ResponseEntity<>(paymentTransactionService.readByChargeTypePartnerIdFundsHolderStatus(chargeType, partnerId, fundsHolder, status)
				.stream()
				.map(PaymentTransactionConverterUtil::convertToDto)
				.collect(Collectors.toList()), HttpStatus.OK);
	}


	@ApiOperation(value = "Get information about transaction, its reservation and party by transaction id", notes = "Returns information about transaction ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"), @ApiResponse(code = 400, message = "Wrong id was provided") })
	@GetMapping("/transaction-reservation-party/{id}")
	public ResponseEntity<TransactionReservationPartyDto> getTransactionReservationPartyDtoById(@PathVariable(value = "id") Integer id) {
		return new ResponseEntity<> (paymentTransactionService.readTransactionReservationPartyDtoById(id), HttpStatus.OK);
	}

}
