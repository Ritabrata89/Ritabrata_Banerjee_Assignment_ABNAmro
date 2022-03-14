package com.abn.recipemanagement.service;

import com.abn.recipemanagement.model.Recipe;

import java.util.List;

public interface RecipeManagementService {
    public List<Recipe> getAllRecipes();
    public Recipe updateRecipe(Recipe newRecipe, Long id);
    public Recipe save(Recipe recipe);
    public String deleteRecipe(Long id);
}
