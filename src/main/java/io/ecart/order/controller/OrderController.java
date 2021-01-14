package io.ecart.order.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ecart.dto.CreateOrderRequest;
import io.ecart.dto.CreateOrderResponse;
import io.ecart.order.service.OrderSevice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/orders")
@Api(tags = { "orders" })
public class OrderController {

	@Autowired
	private OrderSevice orderSevice;
	
	@ApiResponses(value = {
			@ApiResponse(code = 201, response = CreateOrderResponse.class, message = "order created") })
	@PostMapping
	public ResponseEntity<?> createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
		log.info("createOrder for {}",createOrderRequest);
		CreateOrderResponse createOrderResponse = orderSevice.placeOrder(createOrderRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(createOrderResponse);
	}

	@ApiResponses(value = {
			@ApiResponse(code = 201, response = CreateOrderResponse.class, message = "order deleted") })
	@DeleteMapping
	public ResponseEntity<?> deleteOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
		log.info("deleteOrder for {}");
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
