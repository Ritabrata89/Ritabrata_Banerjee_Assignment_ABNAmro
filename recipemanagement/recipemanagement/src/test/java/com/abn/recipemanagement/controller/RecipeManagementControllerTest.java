package com.abn.recipemanagement.controller;

import com.abn.recipemanagement.config.Category;
import com.abn.recipemanagement.model.Ingredient;
import com.abn.recipemanagement.model.Recipe;
import com.abn.recipemanagement.service.RecipeManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RecipeManagementController.class)
public class RecipeManagementControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    RecipeManagementController controller;

    @MockBean
    private RecipeManagementService service;

    @Test
    public void getAllRecipesTest() throws Exception{
        int offset=1;
        List<Recipe> recipes = service.getAllRecipes();
        when(service.getAllRecipes()).thenReturn(recipes);
        mvc.perform(get("/api/recipes"))
                .andExpect(status().isOk());
    }

    @Test
    public void saveTest_success() throws Exception {
        Recipe recipe = createRecipe();
        when(service.save(recipe)).thenReturn(recipe);
        mvc.perform(post("/api/recipes")
                        .with(csrf().asHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(recipe)))
                        .andExpect(status().isCreated());
    }

    @Test
    public void saveTest_BadRequest() throws Exception {
        Recipe recipe = createRecipe();
        recipe.setInstruction("<script>");
        when(service.save(recipe)).thenReturn(recipe);
        mvc.perform(post("/api/recipes")
                        .with(csrf().asHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(recipe)))
                .andExpect(status().is(400));
    }

    @Test
    public void saveTest_failure() throws Exception {
        this.mvc
                .perform(post("/api/recipes")
                        .with(csrf().asHeader()))
                        .andExpect(status().is(400));

    }

    @Test
    public void updateRecipeByIdTest_success() throws Exception {

        Recipe recipe = createRecipe();
        when(service.save(recipe)).thenReturn(recipe);
        mvc.perform(put("/api/recipes/{id}",1)
                        .with(csrf().asHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(recipe)))
                        .andExpect(status().isOk());
    }

    @Test
    public void updateRecipeByIdTest_failure() throws Exception{
        Recipe recipe = createRecipe();
        when(service.save(recipe)).thenReturn(recipe);
        this.mvc.perform(put("/api/recipes/{id}","")
                        .with(csrf().asHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(recipe)))
                        .andExpect(status().is(405));
    }

    @Test
    public void deleteRecipeById_success() throws Exception {
        mvc.perform(delete("/api/recipes/{id}", 1)
                        .with(csrf().asHeader()))
                        .andExpect(status().isAccepted());
    }

    @Test
    public void deleteRecipeById_failure() throws Exception {
        mvc.perform(delete("/api/recipes/{id}","")
                        .with(csrf().asHeader()))
                        .andExpect(status().is(405));
    }

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = JsonMapper.builder()
                    .addModule(new JavaTimeModule())
                    .build();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
