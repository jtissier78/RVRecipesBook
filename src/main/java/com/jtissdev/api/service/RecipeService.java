package com.jtissdev.api.service;

import com.jtissdev.api.dao.RecipeDAO;
import com.jtissdev.api.model.Recipe;
import com.jtissdev.api.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {


	private RecipeDAO recipeDAO;

	@Autowired
	public RecipeService(RecipeDAO recipeDAO) {
		this.recipeDAO = recipeDAO;
	}
	private RecipeRepository recipeRepository;

	@Autowired
	public RecipeService(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	public List<Recipe> getAllRecipes() {
		return recipeRepository.findAll();
	}

	public Optional<Recipe> getRecipeById(Long id) {
		return recipeRepository.findById(id);
	}

	public List<Recipe> getRecipesByMainIngredient(String mainIngredient) {
		return recipeDAO.getRecipesByMainIngredient(mainIngredient);
	}

	public Recipe addRecipe(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	public Recipe updateRecipe(Recipe recipe) {
		if (recipeRepository.existsById(recipe.getId())) {
			return recipeRepository.save(recipe);
		} else {
			return null; // Gérer l'erreur
		}
	}

	public void deleteRecipe(Long id) {
		recipeRepository.deleteById(id);
	}
}
