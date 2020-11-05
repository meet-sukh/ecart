package io.ecart.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PaymentEventDto {

	@NotNull
	private Long paymentId;
}
