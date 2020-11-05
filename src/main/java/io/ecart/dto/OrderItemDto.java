package io.ecart.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderItemDto {
	
	private Long customerId;
	
	@NotNull
	private Long productId;

	@Min(1)
	private Integer quantity;
}
