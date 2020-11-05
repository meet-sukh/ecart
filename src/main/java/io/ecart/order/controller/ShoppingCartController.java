package io.ecart.order.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.ecart.dto.OrderItemDto;
import io.ecart.order.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/cart")
@Api(tags = { "carts" })
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = OrderItemDto.class, message = "cart items fetched",responseContainer = "List") })
	@GetMapping
	public ResponseEntity<?> getCart(
			@RequestParam(name = "cid",required = true) Long customerId) {
		log.info("getCart for customerId {}",customerId);
		List<OrderItemDto> orderItems = shoppingCartService.getCart(customerId);
		if(CollectionUtils.isEmpty(orderItems)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(orderItems);
	}
	
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = Void.class, message = "added to cart") })
	@PostMapping
	public ResponseEntity<?> addToCart(@Valid @RequestBody OrderItemDto orderItemDto) {
		log.info("addToCart for {}",orderItemDto);
		shoppingCartService.addUpdateOrderItemToCart(orderItemDto);
		return ResponseEntity.ok().build();
	}
	
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = Void.class, message = "removed from cart") })
	@DeleteMapping
	public ResponseEntity<?> removeFromCart(@Valid @RequestBody OrderItemDto orderItemDto) {
		log.info("removeFromCart for {}",orderItemDto);
		shoppingCartService.removeOrderItemFromCart(orderItemDto);
		return ResponseEntity.accepted().build();
	}
}
