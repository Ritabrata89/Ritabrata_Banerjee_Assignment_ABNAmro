package com.abn.recipemanagement.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RecipeErrorDetails {

	RECIPE_APPLICATION_INVALID_METHOD_ERROR("TPA-0001", HttpStatus.METHOD_NOT_ALLOWED, "Invalid method error"),
	RECIPE_APPLICATION_BAD_REQUEST_ERROR("TPA-0002", HttpStatus.BAD_REQUEST, "Bad request error"),
	RECIPE_NOT_FOUND_EXCEPTION("TPA-0003", HttpStatus.NOT_FOUND, "Invalid request exception");
	
	private final String code;
	private final HttpStatus status;
    private final String message;
}
