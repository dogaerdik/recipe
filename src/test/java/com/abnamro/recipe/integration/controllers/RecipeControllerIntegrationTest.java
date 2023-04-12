package com.abnamro.recipe.integration.controllers;


import com.abnamro.recipe.domain.Ingredient;
import com.abnamro.recipe.domain.Recipe;
import com.abnamro.recipe.dto.RecipeDTO;
import com.abnamro.recipe.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
public class RecipeControllerIntegrationTest extends AbstractControllerIntegrationTest {
    @Autowired
    private RecipeRepository recipeRepository;
    @Before
    public void before() {
        recipeRepository.deleteAll();
    }
    @Test
    public void test_createRecipe_successfully() throws Exception {
        List<Ingredient> ingredientList= new ArrayList<>();
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Potato");
        ingredientList.add(ingredient);
        RecipeDTO request = new RecipeDTO();
        request.setId(1L);
        request.setVegetarian(Boolean.TRUE);
        request.setNumberOfServings(5);
        request.setInstructions("Chop the onion and the pepper , stir and fry and serve.");
        request.setIngredients(ingredientList);
        MvcResult result = performPost("/api/v1/recipe", request)
                .andExpect(status().isCreated())
                .andReturn();
        Optional<Recipe> optionalRecipe = recipeRepository.findById(1L);
        assertTrue(optionalRecipe.isPresent());
        assertEquals(optionalRecipe.get().getName(), request.getName());
        assertEquals(optionalRecipe.get().getInstructions(), request.getInstructions());
        assertEquals(optionalRecipe.get().getNumberOfServings(), request.getNumberOfServings());
    }
}
