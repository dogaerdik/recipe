package com.abnamro.recipe.service;

import com.abnamro.recipe.config.MessageProvider;
import com.abnamro.recipe.dto.RecipeDTO;
import com.abnamro.recipe.exception.NotFoundException;
import com.abnamro.recipe.repository.IngredientRepository;
import com.abnamro.recipe.repository.RecipeRepository;
import com.abnamro.recipe.service.impl.RecipeCommandServiceImpl;
import com.abnamro.recipe.service.impl.RecipeQueryServiceImpl;
import com.abnamro.recipe.utils.builder.RecipeTestDataBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceTest {

    @Mock
    private ModelMapper mapper;
    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private IngredientRepository ingredientRepository;
    @InjectMocks
    private RecipeCommandServiceImpl recipeCommandService;
    @Mock
    private  MessageProvider messageProvider;

    @Before
    public void setup() {
        recipeRepository = mock(RecipeRepository.class);
        ingredientRepository = mock(IngredientRepository.class);
        mapper = mock(ModelMapper.class);
        recipeCommandService = new RecipeCommandServiceImpl(mapper, recipeRepository,messageProvider);
        mapper = mock(ModelMapper.class);
    }
    @Test(expected = NotFoundException.class)
    public void test_updateRecipe_notFound() {
        RecipeDTO recipeDTO= RecipeTestDataBuilder.createRecipeDTO(5L);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.empty());

        recipeCommandService.updateRecipe(recipeDTO);
    }

}
