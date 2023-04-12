package com.abnamro.recipe.utils.builder;


import com.abnamro.recipe.domain.Ingredient;
import com.abnamro.recipe.domain.IngredientType;
import com.abnamro.recipe.request.IngredientFilterRequest;

import java.util.Date;
import java.util.List;

public class IngredientTestDataBuilder {
    public static Ingredient createIngredient() {
        return new IngredientModelBuilder()
                .withName("tomato")
                .build();
    }

    public static Ingredient createIngredientWithNameParam(String name) {
        return new IngredientModelBuilder()
                .withName(name)
                .build();
    }

    public static List<Ingredient> createIngredientList() {
        return createIngredientList(false);
    }

    Date date = new Date();
    public static List<Ingredient> createIngredientList(boolean withId) {
        Ingredient i1 = new IngredientModelBuilder()
                .withId(withId ? 10 : null)
                .withName("tomato")
                .build();

        Ingredient i2 = new IngredientModelBuilder()
                .withId(withId ? 11 : null)
                .withName("cabbage")
                .build();

        return List.of(i1, i2);
    }

    public static IngredientFilterRequest ingredientFilterRequest() {
        return new IngredientFilterRequestBuilder()
                .withName("tomato", IngredientType.INCLUDE)
                .build();
    }
}
