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
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        List<Ingredient> ingredientList = new ArrayList<>();
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
        if(optionalRecipe.isPresent())
        {
        assertEquals(optionalRecipe.get().getName(), request.getName());
        assertEquals(optionalRecipe.get().getInstructions(), request.getInstructions());
        assertEquals(optionalRecipe.get().getNumberOfServings(), request.getNumberOfServings());
        assertNotNull(optionalRecipe);
        }
    }

    @Test
    public void test_listRecipe_successfully() throws Exception {
        Recipe recipe1 = new Recipe();
        recipe1.setId(5L);
        recipe1.setName("name1");
        recipe1.setInstructions("Ins1");

        Recipe recipe2 = new Recipe();
        recipe2.setId(6L);
        recipe2.setName("name2");
        recipe2.setInstructions("Ins2");

        List<Recipe> storedRecipeList = new ArrayList<>();
        storedRecipeList.add(recipe1);
        storedRecipeList.add(recipe2);

        recipeRepository.saveAll(storedRecipeList);

        MvcResult result = performPost("/api/v1/recipe/findAll",null)
                .andExpect(status().isOk())
                .andReturn();

        List<RecipeDTO> RecipeList = getListFromMvcResult(result, RecipeDTO.class);

        assertEquals(storedRecipeList.size(), RecipeList.size());
        assertEquals(storedRecipeList.get(0).getName(), RecipeList.get(0).getName());
        assertEquals(storedRecipeList.get(1).getName(), RecipeList.get(1).getName());
    }

    @Test
    public void test_updateRecipe_successfully() throws Exception {
        Recipe testRecipe = new Recipe();
        testRecipe.setName("lasagna");
        testRecipe.setInstructions("chop the onion, potato");
        testRecipe.setNumberOfServings(2);

        Recipe savedRecipe = recipeRepository.save(testRecipe);

        savedRecipe.setName("meat-lasagna");
        savedRecipe.setInstructions("add meat add pasta");

        performPatch("/api/v1/recipe", savedRecipe)
                .andExpect(status().isOk());

        Optional<Recipe> updatedRecipe = recipeRepository.findById(savedRecipe.getId());

        assertTrue(updatedRecipe.isPresent());
        assertEquals(savedRecipe.getName(), updatedRecipe.get().getName());
        assertEquals(savedRecipe.getNumberOfServings(), updatedRecipe.get().getNumberOfServings());
        assertEquals(savedRecipe.getInstructions(), updatedRecipe.get().getInstructions());
    }

    @Test
    public void test_SearchRecipeByCriteria_fails() throws Exception {
        performPost("/api/v1/recipe/search", null)
                .andExpect(status().is4xxClientError())
                .andReturn();
    }
}
