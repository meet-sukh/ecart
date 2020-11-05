package io.ecart.payment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ecart.dto.PaymentEventDto;
import io.ecart.payment.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/payments")
@Api(tags = { "payments" })
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@ApiResponses(value = {
			@ApiResponse(code = 202, response = Void.class, message = "processing sucess event") })
	@PostMapping("/events/success")
	public ResponseEntity<?> processSuccessPayment(@Valid @RequestBody PaymentEventDto paymentEventDto) {
		log.info("processSuccessPayment for {}",paymentEventDto);
		paymentService.processPaymentSucess(paymentEventDto.getPaymentId());
		return ResponseEntity.accepted().build();
	}
	
	@ApiResponses(value = {
			@ApiResponse(code = 201, response = Void.class, message = "processing failed event") })
	@PostMapping("/events/failure")
	public ResponseEntity<?> processFailedPayment(@Valid @RequestBody PaymentEventDto paymentEventDto) {
		log.info("processFailedPayment for {}",paymentEventDto);
		paymentService.processPaymentSucess(paymentEventDto.getPaymentId());
		return ResponseEntity.accepted().build();
	}
}
