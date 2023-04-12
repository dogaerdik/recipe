package com.abnamro.recipe.request;

import com.abnamro.recipe.domain.IngredientType;
import com.abnamro.recipe.validator.EnumValidator;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class IngredientFilterRequest {
    @NotBlank(message = "Please provide the ingredient name.")
    @Size(max = 100, message = "The description can be {max} characters long at maximum.")
    @ApiModelProperty(notes = "The name of the ingredient", example = "Onion")
    private String name;
    @ApiModelProperty(notes = "The information of the ingredients are include or exclude the filtering", example = "EXCLUDE")
    @EnumValidator(enumClass = IngredientType.class, message = "The recipe type is invalid it can be EXCLUDE or INCLUDE")
    private IngredientType ingredientType;
}

