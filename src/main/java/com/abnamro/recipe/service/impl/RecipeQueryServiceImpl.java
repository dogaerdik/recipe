package com.abnamro.recipe.service.impl;

import com.abnamro.recipe.domain.Ingredient;
import com.abnamro.recipe.domain.IngredientType;
import com.abnamro.recipe.dto.RecipeDTO;
import com.abnamro.recipe.repository.IngredientRepository;
import com.abnamro.recipe.repository.RecipeRepository;
import com.abnamro.recipe.request.SearchFilterRequest;
import com.abnamro.recipe.service.RecipeQueryService;
import com.abnamro.recipe.service.spec.SearchFilterSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class RecipeQueryServiceImpl implements RecipeQueryService {
    private final ModelMapper mapper;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    @Override
    public List<RecipeDTO> searchRecipe(SearchFilterRequest searchFilterRequest) {

        List<Ingredient> ingredientListInclude = ingredientRepository.findByName(searchFilterRequest.getIngredientFilterRequest().getName());

        List<RecipeDTO> recipeDTOList;
        recipeDTOList = recipeRepository
                .findAll(SearchFilterSpec.searchRecipe(searchFilterRequest))
                .stream()
                .map(recipe -> mapper.map(recipe, RecipeDTO.class))
                .filter(recipeDTO -> ingredientListInclude.stream()
                        .allMatch(ingredient -> ingredient.getName().equals(ingredientListInclude.stream().findAny().get().getName()))).collect(Collectors.toList());

        if (searchFilterRequest.getIngredientFilterRequest().getIngredientType().equals(IngredientType.EXCLUDE))
        {
            Set<Long> recipeIds = recipeDTOList.stream()
                .map(RecipeDTO::getId)
                .collect(toSet());
            recipeDTOList = recipeRepository.findByIdNotIn(recipeIds).stream().map(recipe -> mapper.map(recipe, RecipeDTO.class)).collect(Collectors.toList());
        }
        return recipeDTOList;
    }
}
