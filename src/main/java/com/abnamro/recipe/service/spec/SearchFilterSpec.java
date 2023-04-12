package com.abnamro.recipe.service.spec;

import com.abnamro.recipe.domain.Ingredient;
import com.abnamro.recipe.domain.Recipe;
import com.abnamro.recipe.request.SearchFilterRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class SearchFilterSpec {
    public static Specification<Recipe> searchRecipeInfo(SearchFilterRequest request) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("isVegetarian"), request.isVegetarian()));

            if (request.getInstructions() != null) {
                predicates.add(cb.like(cb.lower(root.get("instructions")), "%"+request.getInstructions().toLowerCase()+"%"));
            }
            if (request.getNumberOfServings() != null) {
                predicates.add(cb.equal(root.get("numberOfServings"), request.getNumberOfServings()));
            }
            if (request.getIngredientFilterRequest().getName() != null) {
                Join<Ingredient, Recipe> ingredientRecipeJoin = root.join("ingredients");
                predicates.add(cb.equal(ingredientRecipeJoin.get("name"), request.getIngredientFilterRequest().getName()));
            }
            query.orderBy(cb.desc(root.get("id")));
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
    public static Specification<Recipe> searchRecipe(SearchFilterRequest request) {
        return Specification.where(searchRecipeInfo(request));
    }
}
