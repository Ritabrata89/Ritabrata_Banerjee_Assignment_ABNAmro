package com.abn.recipemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Exception DTO class for the custom Exception of the application
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeExceptionDTO {

	private String code;
	private HttpStatus status;
	private String message;
}
