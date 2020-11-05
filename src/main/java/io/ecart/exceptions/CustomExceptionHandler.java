package io.ecart.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.ecart.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomExceptionHandler {

	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	@ExceptionHandler(ResourceNotFoundException.class)
	public ErrorDto handleResourceNotFoundException(ResourceNotFoundException ex) {
		log.error("handleResourceNotFoundException ",ex);
		ErrorDto errorDTO = new ErrorDto(ex.getMessage());
		return errorDTO;
	}
}
