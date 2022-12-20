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
import team.asd.dto.PendingTransactionDto;
import team.asd.entity.PendingTransaction;
import team.asd.service.PendingTransactionService;
import team.asd.util.PendingTransactionConverterUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pending_transaction")
@AllArgsConstructor
@ApiOperation("Pending Transaction Api")
public class PendingTransactionController {
	private final PendingTransactionService pendingTransactionService;

	@ApiOperation(value = "Get a Pending Transaction by id", notes = "Require integer path variable. Returns a Pending Transaction as per the id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 404, message = "Not found - The Pending Transaction was not found") })
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getPendingTransactionById(@PathVariable(value = "id") Integer id) {
		return new ResponseEntity<>(PendingTransactionConverterUtil.convertToDto(pendingTransactionService.readById(id)), HttpStatus.OK);
	}

	@ApiOperation(value = "Create a new Pending Transaction", notes = "Returns a new Pending Transaction")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully added"), @ApiResponse(code = 404, message = "Invalid parameters were provided") })
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createPendingTransaction(@RequestBody @Valid PendingTransactionDto pendingTransactionDto) {
		PendingTransaction pendingTransaction = PendingTransactionConverterUtil.convertToEntity(pendingTransactionDto);
		pendingTransactionService.create(pendingTransaction);
		return new ResponseEntity<>(PendingTransactionConverterUtil.convertToDto(pendingTransaction), HttpStatus.OK);
	}

	@ApiOperation(value = "Update particular Pending Transaction by id", notes = "Returns an updated Pending Transaction")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated"),
			@ApiResponse(code = 404, message = "Invalid parameters were provided") })
	@PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updatePendingTransaction(@RequestBody @Valid PendingTransactionDto pendingTransactionDto) {
		PendingTransaction pendingTransaction = PendingTransactionConverterUtil.convertToEntity(pendingTransactionDto);
		pendingTransactionService.update(Objects.requireNonNull(pendingTransaction));
		return new ResponseEntity<>(PendingTransactionConverterUtil.convertToDto(pendingTransaction), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete Pending Transaction by id", notes = "Require integer path variable. Delete Pending Transaction from the table")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted"),
			@ApiResponse(code = 404, message = "Not found - The Pending Transaction was not found") })
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deletePendingTransaction(@PathVariable(value = "id") Integer id) {
		pendingTransactionService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Get a list of Pending Transactions by reservation id and status", notes = "Require reservation id and status variables. Returns a list of matching pending transactions")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 404, message = "Not found - The pending transaction was not found") })
	@GetMapping("/list/reservation_id/{reservationId}/status/{status}")
	public ResponseEntity<List<PendingTransactionDto>> readByReservationIdStatus(@PathVariable(value = "reservationId") Integer reservationId,
			@PathVariable(value = "status") Integer status) {
		return new ResponseEntity<>(pendingTransactionService.readByReservationIdStatus(reservationId, status)
				.stream()
				.map(PendingTransactionConverterUtil::convertToDto)
				.collect(Collectors.toList()), HttpStatus.OK);
	}

	@ApiOperation(value = "Add several new Pending Transactions to the table", notes = "Returns a list of Pending Transactions")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "List of Pending Transactions successfully created"),
			@ApiResponse(code = 404, message = "Invalid parameters were provided") })
	@PostMapping("/list")
	public ResponseEntity<List<PendingTransactionDto>> createList(@RequestBody List<@Valid PendingTransactionDto> pendingTransactionDtoList) {
		List<PendingTransaction> pendingTransactions = pendingTransactionDtoList.stream()
				.map(PendingTransactionConverterUtil::convertToEntity)
				.collect(Collectors.toList());

		pendingTransactionService.createList(pendingTransactions);

		return new ResponseEntity<>(pendingTransactions.stream()
				.map(PendingTransactionConverterUtil::convertToDto)
				.collect(Collectors.toList()), HttpStatus.OK);
	}

	@ApiOperation(value = "Get a list of Pending Transactions by date", notes = "Require first date and second date. Returns a list of matching pending transactions")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 404, message = "Not found - The pending transaction was not found") })
	@GetMapping("/list_dates")
	public ResponseEntity<List<PendingTransactionDto>> readByDateRange(@RequestParam(name = "from_date") String fromDate,
			@RequestParam(name = "to_date") String toDate) {
		return new ResponseEntity<>(pendingTransactionService.readByDateRange(fromDate, toDate)
				.stream()
				.map(PendingTransactionConverterUtil::convertToDto)
				.collect(Collectors.toList()), HttpStatus.OK);
	}

	@ApiOperation(value = "Get a list of Pending Transaction items with status Pending and change_date is in future and Payment Transaction items are not with state Accepted or Confirmed", notes = "Pending Transaction table is joined with Payment Transaction table by reservation_id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 404, message = "Not found - The item was not found") })
	@GetMapping("/list_with_payment_transaction/joined_by_reservation_id")
	public ResponseEntity<List<PendingTransactionDto>> readWithPaymentTransactionByStatusAndChargeDate() {
		return new ResponseEntity<>(pendingTransactionService.readWithPaymentTransactionByStatusAndChargeDate()
				.stream()
				.map(PendingTransactionConverterUtil::convertToDto)
				.collect(Collectors.toList()), HttpStatus.OK);
	}

}
