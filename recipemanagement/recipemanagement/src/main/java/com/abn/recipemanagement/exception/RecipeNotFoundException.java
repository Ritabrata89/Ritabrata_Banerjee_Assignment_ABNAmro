package com.abn.recipemanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Custom exception class of the application
 *@author rtbrt2009@gmail.com
 */

@AllArgsConstructor
@Getter
@Setter
public class RecipeNotFoundException extends RuntimeException{
	
	String code;
    HttpStatus status;
    String message;

}
