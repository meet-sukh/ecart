package io.ecart.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateOrderResponse {

	@NotNull
	private Long orderId;
	
	@NotNull
	private Long paymentTransactionId;
}
