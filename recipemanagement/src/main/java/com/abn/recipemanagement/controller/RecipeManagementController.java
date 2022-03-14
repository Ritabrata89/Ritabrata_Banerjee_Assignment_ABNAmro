package com.abn.recipemanagement.controller;


import com.abn.recipemanagement.model.Recipe;
import com.abn.recipemanagement.service.RecipeManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The RecipeManagement controller for the application which defines the end points.
 * @author rtbrt2009@gmail.com
 *
 */

@RestController
@RequestMapping(value = "/api/recipes")
public class RecipeManagementController {

    private RecipeManagementService service;

    public RecipeManagementController(RecipeManagementService service) {
        this.service = service;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All recipes have been fetched Successfully")})
    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes(){
        return new ResponseEntity(service.getAllRecipes(), HttpStatus.OK);
    }

    @Operation(summary = "Creates new recipe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New recipe is created")})
    @PostMapping
    public ResponseEntity<Recipe> saveRecipe(@Valid @RequestBody Recipe recipe){
        return new ResponseEntity(service.save(recipe), HttpStatus.CREATED);
    }

    @Operation(summary = "Updates recipe if supplied id is found or else saves the recipe.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "recipe is updated")})
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") Long id, @Valid @RequestBody Recipe recipe){
        return new ResponseEntity(service.updateRecipe(recipe, id), HttpStatus.OK);
    }

    @Operation(summary = "Deletes recipe if supplied id is found or else shows recipe not found.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Recipe has been deleted successfully")})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable("id") Long id){
        return new ResponseEntity(service.deleteRecipe(id), HttpStatus.ACCEPTED);
    }

}
