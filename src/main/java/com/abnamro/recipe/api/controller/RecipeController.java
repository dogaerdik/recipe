package com.abnamro.recipe.api.controller;


import com.abnamro.recipe.api.constants.ApiEndpoints;
import com.abnamro.recipe.dto.RecipeDTO;
import com.abnamro.recipe.request.SearchFilterRequest;
import com.abnamro.recipe.service.impl.RecipeCommandServiceImpl;
import com.abnamro.recipe.service.impl.RecipeQueryServiceImpl;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequestMapping(value = ApiEndpoints.RECIPE_URL_CONVERTER, produces = ApiEndpoints.RESPONSE_CONTENTTYPE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeController {
    private final RecipeCommandServiceImpl recipeCommandService;
    private final RecipeQueryServiceImpl recipeQueryService;

    @ApiOperation(value = "Create a recipe")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad input")
    })
    @PostMapping()
    public ResponseEntity<RecipeDTO> createRecipe(@RequestBody RecipeDTO recipeDTO) {
        log.info("Creating the recipe with properties");
        return new ResponseEntity<>(recipeCommandService.createRecipe(recipeDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete the recipe")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 404, message = "Recipe not found by the given ID")
    })
    @DeleteMapping(value = "/{id}")
    public void deleteRecipe(@ApiParam(value = "Recipe ID", required = true) @NotNull @PathVariable("id") long id) {
        log.info("Deleting the recipe by its id. Id: {}", id);
        recipeCommandService.deleteRecipe(id);
    }

    @ApiOperation(value = "Search recipes by given parameters")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 404, message = "Different error messages related to criteria and recipe")

    })
    @PostMapping(value = "/search")
    public ResponseEntity<List<RecipeDTO>> searchRecipe(@RequestBody SearchFilterRequest searchFilterRequest) {
        return new ResponseEntity<>(recipeQueryService.searchRecipe(searchFilterRequest), HttpStatus.OK);
    }

    @ApiOperation(value = "List all recipes")
    @PostMapping(value = "/findAll")
    public ResponseEntity<List<RecipeDTO>> getAllRecipe() {
        log.info("Getting the all recipes");
        return new ResponseEntity<>(recipeCommandService.getAllRecipe(), HttpStatus.OK);
    }

    @ApiOperation(value = "Update a recipe")
    @PutMapping()
    public ResponseEntity<RecipeDTO> updateRecipe(@RequestBody RecipeDTO recipeDTO) {
        log.info("Updating the recipe");
        return new ResponseEntity<>(recipeCommandService.updateRecipe(recipeDTO), HttpStatus.OK);
    }
}
