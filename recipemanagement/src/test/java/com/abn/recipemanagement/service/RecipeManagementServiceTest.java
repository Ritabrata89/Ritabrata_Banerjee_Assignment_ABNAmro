package com.abn.recipemanagement.service;

import com.abn.recipemanagement.config.Category;
import com.abn.recipemanagement.model.Ingredient;
import com.abn.recipemanagement.model.Recipe;
import com.abn.recipemanagement.repository.RecipeManagementRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class RecipeManagementServiceTest {
    @Autowired
    RecipeManagementService service;
    @MockBean
    RecipeManagementRepository repo;

    @Test
    public void getAllRecipesTest() throws Exception{
        int offset=1;
        List<Recipe> recipes = repo.findAll();
        when(repo.findAll()).thenReturn(recipes);
        assertEquals(recipes, service.getAllRecipes());
    }


    @Test
    public void saveTest() {
        Recipe recipe = createRecipe();
        when(repo.save(any())).thenReturn(recipe);
        assertEquals(recipe, service.save(recipe));
    }

    @Test
    public void updateTest() {
        Long id = 1L;
        Recipe recipe = createRecipe();
        when(repo.findById(id)).thenReturn(Optional.of(recipe));
        when(repo.save(recipe)).thenReturn(recipe);
        assertEquals(recipe, service.updateRecipe(recipe,id));
    }

    @Test
    public void deleteTest() {
        Long id = 1L;
        Recipe recipe = createRecipe();
        when(repo.findById(id)).thenReturn(Optional.of(recipe));
        service.deleteRecipe(id);
        verify(repo,times(1)).deleteById(id);
    }

    private static Recipe createRecipe(){
        Recipe recipe = new Recipe();
        Ingredient ing1 = new Ingredient();
        Ingredient ing2 = new Ingredient();
        List<Ingredient> ingList = new ArrayList<>();
        ing1.setId(1L);
        ing1.setName("Chicken");
        ing2.setId(2L);
        ing2.setName("Potato");
        ingList.add(ing1);
        ingList.add(ing2);
        recipe.setId(1L);
        recipe.setLastUpdated(LocalDateTime.now());
        recipe.setServes(4);
        recipe.setCategory(Category.Nonveg);
        recipe.setIngredients(ingList);
        recipe.setInstruction("Test Recipe");
        return recipe;
    }
}
