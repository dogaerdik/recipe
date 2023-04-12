package com.abnamro.recipe.utils.builder;


import com.abnamro.recipe.domain.Ingredient;

import java.util.Date;

public class IngredientModelBuilder {
    private Integer id;
    private String ingredientName;
    private Date createdDate;

    public Ingredient build() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(id);
        ingredient.setName(ingredientName);
        ingredient.setCreatedDate(createdDate);
        return ingredient;
    }
    public IngredientModelBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public IngredientModelBuilder withName(String name) {
        this.ingredientName = name;
        return this;
    }
    public IngredientModelBuilder withCreateDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }
}
