package io.ecart.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.ecart.entity.Order;
import io.ecart.entity.OrderItem;
import io.ecart.enums.OrderStatus;
import io.ecart.enums.PaymentInstrument;
import lombok.Data;


@Data
public class CreateOrderRequest {

	@NotNull
	private Long customerId;

	@Valid
	private List<OrderItemDto> orderItems;

	@NotNull
	private Double totalAmount;
	
	@NotEmpty
	@Valid
	private String shippingAddress;

	@NotEmpty
	@Valid
	private String billingAddress;

	@NotNull
	@Valid
	private PaymentInstrument paymentInstrument;
	
	public Order toOrder() {
	    return Order.builder()
	      .billingAddress(billingAddress)
	      .shippingAddress(shippingAddress)
	      .orderItems(toOrderItemList())
	      .customerId(customerId)
	      .status(OrderStatus.INITIATED)
	      .totalAmount(totalAmount)
	      .build();
	  }
	
	private List<OrderItem> toOrderItemList() {
		List<OrderItem> orderItem = this.orderItems.stream().map(item -> convertToOrderItem(item)).collect(Collectors.toList());
		return orderItem;
		
	}
	
	private OrderItem convertToOrderItem(OrderItemDto item) {
		OrderItem orderItem = new OrderItem();
		orderItem.setProductId(item.getProductId());
		orderItem.setQuantity(item.getQuantity());
		return orderItem;
	}
}
