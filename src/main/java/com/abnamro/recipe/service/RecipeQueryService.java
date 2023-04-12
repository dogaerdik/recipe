package com.abnamro.recipe.service;

import com.abnamro.recipe.dto.RecipeDTO;
import com.abnamro.recipe.request.SearchFilterRequest;

import java.util.List;

public interface RecipeQueryService {
    List<RecipeDTO> searchRecipe(SearchFilterRequest searchFilterRequest);
}
