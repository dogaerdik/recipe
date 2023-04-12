package com.abnamro.recipe.repository;

import com.abnamro.recipe.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> , JpaSpecificationExecutor<Recipe> {
    List<Recipe> findByIdNotIn(Set<Long> recipeIds);
}