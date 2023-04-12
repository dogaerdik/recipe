package com.abnamro.recipe.dto;

import com.abnamro.recipe.domain.Ingredient;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class RecipeDTO {
    private Long id;
    @NotBlank(message = "Please provide the ingredient name")
    @Size(max = 100, message = "The description can be {max} characters long at maximum")
    @ApiModelProperty(notes = "The name of the recipe", example = "Pasta")
    private String name;
    @ApiModelProperty(notes = "The information of the vegetarian", example = "True")
    private boolean isVegetarian;

    @Positive(message = "Please provide the valid number of servings")
    @ApiModelProperty(notes = "The number of servings per recipe", example = "2")
    private Integer numberOfServings;
    @ApiModelProperty(notes = "The ids of the ingredients needed to make the recipe")
    private List<Ingredient> ingredients;
    @Size(max = 100, message = "The description can be {max} characters long at maximum.")
    @ApiModelProperty(notes = "The instructions to create a new recipe", example = "Chop the onion and the pepper , stir and fry and serve.")
    private String instructions;
}