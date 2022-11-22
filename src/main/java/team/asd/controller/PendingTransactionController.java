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
import team.asd.dto.PendingTransactionDto;
import team.asd.entity.PendingTransaction;
import team.asd.service.PendingTransactionService;
import team.asd.util.PendingTransactionConverterUtil;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/pending_transaction")
@AllArgsConstructor
public class PendingTransactionController {
	private final PendingTransactionService pendingTransactionService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getPendingTransactionById(@PathVariable(value = "id") Integer id) {
		return new ResponseEntity<>(PendingTransactionConverterUtil.convertToDto(pendingTransactionService.readById(id)), HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createPendingTransaction(@RequestBody @Valid PendingTransactionDto pendingTransactionDto) {
		PendingTransaction pendingTransaction = pendingTransactionService.create(PendingTransactionConverterUtil.convertToEntity(pendingTransactionDto));
		return new ResponseEntity<>(PendingTransactionConverterUtil.convertToDto(pendingTransaction), HttpStatus.OK);
	}

	@PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updatePendingTransaction(@RequestBody @Valid PendingTransactionDto pendingTransactionDto) {
		PendingTransaction pendingTransaction = pendingTransactionService.update(
				Objects.requireNonNull(PendingTransactionConverterUtil.convertToEntity(pendingTransactionDto)));
		return new ResponseEntity<>(PendingTransactionConverterUtil.convertToDto(pendingTransaction), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deletePendingTransaction(@PathVariable(value = "id") Integer id) {
		pendingTransactionService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
