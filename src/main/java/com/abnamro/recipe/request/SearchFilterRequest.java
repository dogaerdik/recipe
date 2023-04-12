package com.abnamro.recipe.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SearchFilterRequest {
    @ApiModelProperty(notes = "The information of the vegetarian", example = "True")
    private boolean isVegetarian;
    @Positive(message = "Please provide the valid number of servings.")
    @ApiModelProperty(notes = "The number of servings per recipe", example = "2")
    private Integer numberOfServings;
    private IngredientFilterRequest ingredientFilterRequest;
    @Size(max = 100, message = "The description can be {max} characters long at maximum.")
    @ApiModelProperty(notes = "The instructions to create a new recipe", example = "Chop the onion and the pepper , stir and fry and serve.")
    private String instructions;
}

