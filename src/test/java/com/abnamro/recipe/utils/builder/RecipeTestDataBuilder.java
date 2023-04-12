package com.abnamro.recipe.utils.builder;


import com.abnamro.recipe.domain.Ingredient;
import com.abnamro.recipe.domain.Recipe;
import com.abnamro.recipe.dto.RecipeDTO;

import java.util.ArrayList;
import java.util.List;

public class RecipeTestDataBuilder {
    public static RecipeDTO createRecipDTO() {
        return createRecipeDTO(null);
    }

    public static RecipeDTO createRecipeDTO(Long id) {
        RecipeDTO recipe = new RecipeDTO();

        Ingredient i2 = new IngredientModelBuilder()
                .withName("cabbage")
                .build();

        recipe.setId(id);
        recipe.setName("Pasta");
        recipe.setNumberOfServings(5);
        recipe.setVegetarian(Boolean.TRUE);
        recipe.setInstructions("someInstruction");
        return recipe;
    }

    public static Recipe createRecipe() {
        return createRecipe(null);
    }

    public static Recipe createRecipe(Long id) {
        Recipe recipe = new Recipe();
        recipe.setId(id);
        recipe.setName("Pasta");
        recipe.setNumberOfServings(5);
        recipe.setVegetarian(Boolean.TRUE);
        recipe.setInstructions("someInstruction");
        return recipe;
    }
}
