package io.ecart.dto;

import lombok.Data;

@Data
public class ErrorDto {

	private String message;

	public ErrorDto(String message) {
		super();
		this.message = message;
	}
	
	
}
