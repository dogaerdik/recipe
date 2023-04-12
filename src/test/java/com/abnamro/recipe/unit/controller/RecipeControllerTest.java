package com.abnamro.recipe.unit.controller;


import com.abnamro.recipe.api.controller.RecipeController;
import com.abnamro.recipe.domain.Recipe;
import com.abnamro.recipe.dto.RecipeDTO;
import com.abnamro.recipe.request.SearchFilterRequest;
import com.abnamro.recipe.service.impl.RecipeCommandServiceImpl;
import com.abnamro.recipe.service.impl.RecipeQueryServiceImpl;
import com.abnamro.recipe.utils.builder.IngredientTestDataBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecipeControllerTest {
    @Mock
    private RecipeCommandServiceImpl recipeService;
    @Mock
    private  RecipeQueryServiceImpl recipeQueryService;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private RecipeController recipeController;
    @Test
    public void test_createRecipe_successfully() {
        RecipeDTO request = new RecipeDTO();
        request.setInstructions("Chop the onion and the pepper , stir and fry and serve.");
        request.setNumberOfServings(2);

        when(recipeService.createRecipe(any(RecipeDTO.class))).thenReturn(request);
        ResponseEntity<RecipeDTO> response = recipeController.createRecipe(request);
        assertThat(response).isNotNull();
    }

    @Test
    public void test_getAllRecipe_successfully() {
        List<RecipeDTO> recipeDTOList = new ArrayList<>();
        RecipeDTO Recipe = new RecipeDTO();
        Recipe.setId(5L);
        Recipe.setName("name");
        recipeDTOList.add(Recipe);
        when(recipeService.getAllRecipe()).thenReturn(recipeDTOList);
        ResponseEntity<List<RecipeDTO>> response = recipeController.getAllRecipe();
        assertThat(response.getBody().get(0).getId()).isSameAs(Recipe.getId());
        assertThat(response.getBody().get(0).getName()).isSameAs(Recipe.getName());
    }

    @Test
    public void test_listRecipes_successfully() {
        RecipeDTO recipe = new RecipeDTO();
        recipe.setId(5L);
        recipe.setInstructions("Instruction1");
        recipe.setVegetarian(Boolean.TRUE);

        RecipeDTO recipe1 = new RecipeDTO();
        recipe1.setId(6L);
        recipe1.setInstructions("Instruction2");
        recipe1.setVegetarian(Boolean.FALSE);

        List<RecipeDTO> storedRecipeList = new ArrayList<>();
        storedRecipeList.add(recipe);
        storedRecipeList.add(recipe1);
        List<RecipeDTO> recipeResult = new ArrayList<>();
        recipeResult.add(recipe);

        SearchFilterRequest searchFilterRequest = new SearchFilterRequest();
        searchFilterRequest.setVegetarian(Boolean.TRUE);
        searchFilterRequest.setInstructions("Instruction1");
        IngredientTestDataBuilder.ingredientFilterRequest();

        when(recipeQueryService.searchRecipe(searchFilterRequest)).thenReturn(recipeResult);
        ResponseEntity<List<RecipeDTO>> recipeList = recipeController.searchRecipe(searchFilterRequest);

        assertThat(storedRecipeList.get(0).getInstructions()).isSameAs(recipeList.getBody().get(0).getInstructions());
    }
    @Test
    public void test_updateRecipe_successfully() {
        Recipe recipe = new Recipe();
        recipe.setName("name1");
        recipe.setNumberOfServings(8);
        recipe.setInstructions("ins");

        RecipeDTO Recipe = new RecipeDTO();
        Recipe.setId(5L);
        Recipe.setName("name4");
        Recipe.setInstructions("ins4");
        Recipe.setNumberOfServings(6);

        when(recipeService.updateRecipe(any())).thenReturn(Recipe);
        recipe.setName("name2");
        recipeController.updateRecipe(Recipe);
    }
}