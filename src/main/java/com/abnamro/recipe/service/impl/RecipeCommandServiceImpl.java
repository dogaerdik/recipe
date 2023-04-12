package com.abnamro.recipe.service.impl;

import com.abnamro.recipe.config.MessageProvider;
import com.abnamro.recipe.domain.Recipe;
import com.abnamro.recipe.dto.RecipeDTO;
import com.abnamro.recipe.exception.NotFoundException;
import com.abnamro.recipe.repository.RecipeRepository;
import com.abnamro.recipe.service.RecipeCommandService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class RecipeCommandServiceImpl implements RecipeCommandService {
    private final ModelMapper mapper;
    private final RecipeRepository recipeRepository;
    private final MessageProvider messageProvider;
    @Override
    public RecipeDTO createRecipe(RecipeDTO recipeDTO) {
        return Optional.of(mapper.map(recipeDTO, Recipe.class))
                .map(recipeRepository::save)
                .map(recipe -> mapper.map(recipe, RecipeDTO.class))
                .orElse(null);
    }
    @Override
    public void deleteRecipe(long id) {
        if (!recipeRepository.existsById(id)) {
            throw new NotFoundException(messageProvider.getMessage("RECIPE NOT FOUND !"));
        }
        recipeRepository.deleteById(id);
    }
    @Override
    public List<RecipeDTO> getAllRecipe() {
        return recipeRepository.findAll()
                .stream().map(recipe -> mapper.map(recipe, RecipeDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public RecipeDTO updateRecipe(RecipeDTO request) {
        Recipe recipe =recipeRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException(messageProvider.getMessage("Recipe not found.")));
        recipe.setName(request.getName());
        recipe.setInstructions(request.getInstructions());
        recipe.setVegetarian(request.isVegetarian());
        recipe.setNumberOfServings(request.getNumberOfServings());

        return Optional.of(mapper.map(request, Recipe.class))
                .map(recipeRepository::save)
                .map(recipeMap -> mapper.map(recipeMap, RecipeDTO.class))
                .orElse(null);
    }
}
