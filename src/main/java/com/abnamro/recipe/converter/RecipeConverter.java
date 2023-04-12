package com.abnamro.recipe.converter;

import com.abnamro.recipe.domain.Ingredient;
import com.abnamro.recipe.domain.Recipe;
import com.abnamro.recipe.dto.RecipeDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeConverter extends AbstractConverter<Recipe, RecipeDTO> {
    private final ModelMapper mapper;
    @PostConstruct
    public void register() {
        this.mapper.addConverter(this);
    }
    @Override
    protected RecipeDTO convert(Recipe source)
    {
        return convert(source, new RecipeDTO());
    }
    public RecipeDTO convert(Recipe source, RecipeDTO destination) {
        if (Objects.nonNull(source)) {
            destination.setId(source.getId());
            destination.setName(source.getName());
            destination.setVegetarian(source.isVegetarian());
            destination.setInstructions(source.getInstructions());
            destination.setNumberOfServings(source.getNumberOfServings());
            List<Ingredient> ingredient  = source.getIngredients();
            if (!CollectionUtils.isEmpty(ingredient)) {
                destination.setIngredients(ingredient);
            }
      }
        return destination;
    }
}