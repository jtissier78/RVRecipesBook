package com.jtissdev.api.dao;

import com.jtissdev.api.model.Product;
import com.jtissdev.api.model.Recipe;
import com.jtissdev.api.model.RecipeList;

import java.util.List;

public interface  RecipeDAO {
	void addRecipe(Recipe recipe);

	RecipeList getAllRecipes();  // Utilise maintenant RecipeList

	Recipe getRecipeById(String id);  // Utilise maintenant RecipeList

	List<Recipe> getByFullName(String fullName);

	List<Recipe> getByPartialName(String partialName);

	List<Recipe> getByMainIngredient(String id, String fullName, String partialName);

	List<Recipe> getRecipesByMainIngredient(String mainIngredient);

	List<Recipe> getByDifficultyLessThan(String difficulty);

	List<Recipe> getByTotalTimeLessThan(String totalTime);

	void updateRecipe(String id, Recipe recipe);

	void deleteRecipe(String id);

	void addProduct(Product product);

	// Add other methods if needed
}
