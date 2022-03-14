package com.abn.recipemanagement.service;

import com.abn.recipemanagement.exception.RecipeNotFoundException;
import com.abn.recipemanagement.model.Recipe;
import com.abn.recipemanagement.repository.RecipeManagementRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

import static com.abn.recipemanagement.exception.RecipeErrorDetails.RECIPE_NOT_FOUND_EXCEPTION;

@Service
public class RecipeManagementServiceImpl implements RecipeManagementService{

    private RecipeManagementRepository repo;

    public RecipeManagementServiceImpl(RecipeManagementRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return repo.findAll();
    }

    @Override
    public Recipe updateRecipe(Recipe newRecipe, Long id) {
        return repo.findById(id)
                .map(recipe -> {
                    recipe.setCategory(newRecipe.getCategory());
                    recipe.setServes(newRecipe.getServes());
                    recipe.setIngredients(newRecipe.getIngredients());
                    recipe.setLastUpdated(LocalDateTime.now());
                    recipe.setInstruction(newRecipe.getInstruction());
                    return repo.save(recipe);
                })
                .orElseGet(() -> {
                    newRecipe.setLastUpdated(LocalDateTime.now());
                    return repo.save(newRecipe);
                });
    }

    @Override
    public Recipe save(Recipe recipe) {
        recipe.setLastUpdated(LocalDateTime.now());
        return repo.save(recipe);
    }

    @Override
    public String deleteRecipe(Long id) {
        Recipe recipe = repo.findById(id).orElseThrow(() -> new RecipeNotFoundException(RECIPE_NOT_FOUND_EXCEPTION.getCode(),
                RECIPE_NOT_FOUND_EXCEPTION.getStatus(),
                "Recipe not found for id: "+id));
        repo.deleteById(id);
        return "Recipe has been deleted successfully";
    }
}
