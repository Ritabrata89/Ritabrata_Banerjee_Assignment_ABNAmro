package com.abn.recipemanagement.exception;

import com.abn.recipemanagement.model.RecipeExceptionDTO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Custom exception handler class of the application
 *@author rtbrt2009@gmail.com
 */

@RestControllerAdvice
public class RecipeExceptionHandler {
	
	@ExceptionHandler(RecipeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public RecipeExceptionDTO handleProductNotFoundException(RecipeNotFoundException ex) {
		return new RecipeExceptionDTO(ex.code, ex.status, ex.getMessage());
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
	public RecipeExceptionDTO handleInvalidRequestMethodException(HttpRequestMethodNotSupportedException ex) {
		return new RecipeExceptionDTO(RecipeErrorDetails.RECIPE_APPLICATION_INVALID_METHOD_ERROR.getCode(),
				RecipeErrorDetails.RECIPE_APPLICATION_INVALID_METHOD_ERROR.getStatus(),
				RecipeErrorDetails.RECIPE_APPLICATION_INVALID_METHOD_ERROR.getMessage());
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public RecipeExceptionDTO handleBadRequestMethodException(MissingServletRequestParameterException ex) {
		return new RecipeExceptionDTO(RecipeErrorDetails.RECIPE_APPLICATION_BAD_REQUEST_ERROR.getCode(),
				RecipeErrorDetails.RECIPE_APPLICATION_BAD_REQUEST_ERROR.getStatus(),
				RecipeErrorDetails.RECIPE_APPLICATION_BAD_REQUEST_ERROR.getMessage());
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public RecipeExceptionDTO handleInternalServerError(EmptyResultDataAccessException ex) {
		return new RecipeExceptionDTO(RecipeErrorDetails.RECIPE_NOT_FOUND_EXCEPTION.getCode(),
				RecipeErrorDetails.RECIPE_NOT_FOUND_EXCEPTION.getStatus(),
				RecipeErrorDetails.RECIPE_NOT_FOUND_EXCEPTION.getMessage());
	}

}
