package com.abnamro.recipe.unit.validator;

import com.abnamro.recipe.domain.Ingredient;
import com.abnamro.recipe.utils.builder.IngredientTestDataBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class EnumValidatorConstraintTest {
    private static Validator validator;

    @BeforeClass
    public static void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory()
                .getValidator();
    }

    @Test
    public void whenNotBlankName_thenNoConstraintViolations() {
        Ingredient request = IngredientTestDataBuilder.createIngredient();
        Set<ConstraintViolation<Ingredient>> violations = validator.validate(request);
        assertThat(violations.size()).isEqualTo(0);
    }
}
