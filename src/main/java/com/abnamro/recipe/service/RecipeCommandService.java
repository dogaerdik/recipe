package com.abnamro.recipe.service;

import com.abnamro.recipe.dto.RecipeDTO;

import java.util.List;

public interface RecipeCommandService {
    RecipeDTO createRecipe(RecipeDTO recipeDTO);
    void deleteRecipe(long id);
   List<RecipeDTO> getAllRecipe();

    RecipeDTO updateRecipe(RecipeDTO recipeDTO);
}
