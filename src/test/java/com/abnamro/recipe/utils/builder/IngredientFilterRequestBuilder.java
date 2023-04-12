package com.abnamro.recipe.utils.builder;

import com.abnamro.recipe.domain.IngredientType;
import com.abnamro.recipe.request.IngredientFilterRequest;

public class IngredientFilterRequestBuilder {

    private String name;

    private IngredientType ingredientType;

    public IngredientFilterRequest build() {
        return new IngredientFilterRequest(name,ingredientType);
    }

    public IngredientFilterRequestBuilder withName(String name,IngredientType ingredientType) {
        this.name = name;
        this.ingredientType = ingredientType;
        return this;
    }
}