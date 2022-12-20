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
import org.springframework.web.bind.annotation.RestController;
import team.asd.dto.PaymentGatewayProviderDto;
import team.asd.entity.PaymentGatewayProvider;
import team.asd.service.PaymentGatewayProviderService;
import team.asd.util.PaymentGatewayProviderConverterUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payment_gateway_provider")
@AllArgsConstructor
@ApiOperation("Payment Gateway Api")
public class PaymentGatewayProviderController {
	private final PaymentGatewayProviderService paymentGatewayProviderService;

	@ApiOperation(value = "Get a Payment Gateway Provider by id", notes = "Require integer path variable. Returns a Payment Gateway Provider as per the id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 404, message = "Not found - The provider was not found") })
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getPaymentGatewayProviderById(@PathVariable(value = "id") Integer id) {
		return new ResponseEntity<>(PaymentGatewayProviderConverterUtil.convertToDto(paymentGatewayProviderService.readById(id)), HttpStatus.OK);
	}

	@ApiOperation(value = "Create a new Payment Gateway Provider", notes = "Returns a new Payment Gateway Provider")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully added"), @ApiResponse(code = 404, message = "Invalid parameters were provided") })
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createPaymentGatewayProvider(@RequestBody @Valid PaymentGatewayProviderDto paymentGatewayProviderDto) {
		PaymentGatewayProvider paymentGatewayProvider = PaymentGatewayProviderConverterUtil.convertToEntity(paymentGatewayProviderDto);
		paymentGatewayProviderService.create(paymentGatewayProvider);
		return new ResponseEntity<>(PaymentGatewayProviderConverterUtil.convertToDto(paymentGatewayProvider), HttpStatus.OK);
	}

	@ApiOperation(value = "Update particular Payment Gateway Provider by id", notes = "Returns an updated Payment Gateway Provider")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated"),
			@ApiResponse(code = 404, message = "Invalid parameters were provided") })
	@PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updatePaymentGatewayProvider(@RequestBody @Valid PaymentGatewayProviderDto paymentGatewayProviderDto) {
		PaymentGatewayProvider paymentGatewayProvider = PaymentGatewayProviderConverterUtil.convertToEntity(paymentGatewayProviderDto);
		paymentGatewayProviderService.update(Objects.requireNonNull(paymentGatewayProvider));
		return new ResponseEntity<>(PaymentGatewayProviderConverterUtil.convertToDto(paymentGatewayProvider), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete Payment Gateway Provider by id", notes = "Require integer path variable. Delete provider from the table")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted"),
			@ApiResponse(code = 404, message = "Not found - The provider was not found") })
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deletePaymentGatewayProvider(@PathVariable(value = "id") Integer id) {
		paymentGatewayProviderService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Get a list of Payment Gateway Providers by support split payment , autoPay and name ", notes = "Require support split payment , autoPay and name variables. Returns a list of matching providers")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 404, message = "Not found - The provider was not found") })
	@GetMapping("/list/support_split_payment/{supportSplitPayment}/autopay/{autoPay}/name/{name}")
	public ResponseEntity<List<PaymentGatewayProviderDto>> readByNameAutoPaySupportSplitPayment(
			@PathVariable(value = "supportSplitPayment") Integer supportSplitPayment, @PathVariable(value = "autoPay") Integer autoPay,
			@PathVariable(value = "name") String name) {
		return new ResponseEntity<>(paymentGatewayProviderService.readByNameAutoPaySupportSplitPayment(supportSplitPayment, autoPay, name)
				.stream()
				.map(PaymentGatewayProviderConverterUtil::convertToDto)
				.collect(Collectors.toList()), HttpStatus.OK);
	}

	@ApiOperation(value = "Get a list of Payment Gateway Providers if count of PaymentTransaction items by PaymentGatewayProvider is not less than transactionCount parameter", notes = "Require transactionCount parameter. Returns a list of matching providers")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 404, message = "Not found - The provider was not found") })
	@GetMapping("/list_with_payment_transaction/payment_transaction_count/{transactionCount}")
	public ResponseEntity<List<PaymentGatewayProviderDto>> readWithPaymentTransaction(@PathVariable(value = "transactionCount") Integer transactionCount) {
		return new ResponseEntity<>(paymentGatewayProviderService.readWithPaymentTransaction(transactionCount)
				.stream()
				.map(PaymentGatewayProviderConverterUtil::convertToDto)
				.collect(Collectors.toList()), HttpStatus.OK);
	}

	@ApiOperation(value = "Update particular Payment Gateway Provider by id with 15 seconds delay", notes = "Returns an updated Payment Gateway Provider")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated"),
			@ApiResponse(code = 404, message = "Invalid parameters were provided") })
	@PutMapping(value = "/update_with_delay", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateWithDelay(@RequestBody @Valid PaymentGatewayProviderDto paymentGatewayProviderDto) {
		PaymentGatewayProvider paymentGatewayProvider = PaymentGatewayProviderConverterUtil.convertToEntity(paymentGatewayProviderDto);
		return paymentGatewayProviderService.updateWithDelay(paymentGatewayProvider);
	}
}
