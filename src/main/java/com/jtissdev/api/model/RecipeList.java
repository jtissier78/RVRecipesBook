package com.jtissdev.api.model;

import java.util.ArrayList;
import java.util.List;

public class RecipeList {
	private List<Recipe> recipes;

	public RecipeList() {
		this.recipes = new ArrayList<>();
	}

	public void addRecipe(Recipe recipe) {
		recipes.add(recipe);
	}

	public List<Recipe> getAllRecipes() {
		return new ArrayList<>(recipes);
	}

	public Recipe getRecipeById(String id) {
		// Implement logic to find the recipe by id
		return recipes.stream()
				       .filter(recipe -> recipe.getId().equals(id))
				       .findFirst()
				       .orElse(null);
	}

	// Add other methods if needed
}
