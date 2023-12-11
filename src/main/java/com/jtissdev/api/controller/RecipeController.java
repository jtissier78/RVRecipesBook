package com.jtissdev.api.controller;

import com.jtissdev.api.dao.RecipeDAO;
import com.jtissdev.api.model.Recipe;
import com.jtissdev.api.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recipes")
public class RecipeController {


	private final RecipeService recipeService;

	@Autowired
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@GetMapping
	public List<Recipe> getAllRecipes() {
		return recipeService.getAllRecipes();
	}

	@GetMapping("/{id}")
	public Optional<Recipe> getRecipeById(@PathVariable Long id) {
		return recipeService.getRecipeById(id);
	}

	@PostMapping
	public Recipe addRecipe(@RequestBody Recipe recipe) {
		return recipeService.addRecipe(recipe);
	}

	@PutMapping
	public Recipe updateRecipe(@RequestBody Recipe recipe) {
		return recipeService.updateRecipe(recipe);
	}

	@DeleteMapping("/{id}")
	public void deleteRecipe(@PathVariable Long id) {
		recipeService.deleteRecipe(id);
	}
}