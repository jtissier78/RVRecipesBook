package com.jtissdev.api.dao;

import com.jtissdev.api.model.Product;
import com.jtissdev.api.model.Recipe;
import com.jtissdev.api.model.RecipeList;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class RecipeDAOImpl implements RecipeDAO {
	private RecipeList recipeList;  // Utilise maintenant RecipeList
	@PersistenceContext
	private EntityManager entityManager;

	public RecipeDAOImpl() {
		this.recipeList = new RecipeList();
	}

	@Override
	public void addRecipe(Recipe recipe) {
		recipeList.addRecipe(recipe);
	}

	@Override
	public RecipeList getAllRecipes() {
		return recipeList;
	}

	@Override
	public Recipe getRecipeById(String id) {
		return recipeList.getRecipeById(id);
	}

	/**
	 * @param fullName
	 * @return
	 */
	@Override
	public List<Recipe> getByFullName(String fullName) {
		return null;
	}

	/**
	 * @param partialName
	 * @return
	 */
	@Override
	public List<Recipe> getByPartialName(String partialName) {
		return null;
	}

	/**
	 * @param id
	 * @param fullName
	 * @param partialName
	 * @return
	 */
	@Override
	public List<Recipe> getByMainIngredient(String id, String fullName, String partialName) {
		return null;
	}

	/**
	 * @param difficulty
	 * @return
	 */
	@Override
	public List<Recipe> getByDifficultyLessThan(String difficulty) {
		return null;
	}

	/**
	 * @param totalTime
	 * @return
	 */
	@Override
	public List<Recipe> getByTotalTimeLessThan(String totalTime) {
		return null;
	}

	@Override
	public List<Recipe> getRecipesByMainIngredient(String mainIngredient) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Recipe> cq = cb.createQuery(Recipe.class);
		Root<Recipe> root = cq.from(Recipe.class);

		cq.select(root)
				.where(cb.equal(root.join("ingredients").get("mainIngredient"), mainIngredient));

		TypedQuery<Recipe> query = entityManager.createQuery(cq);
		return query.getResultList();
	}

	/**
	 * @param id
	 * @param recipe
	 */
	@Override
	public void updateRecipe(String id, Recipe recipe) {

	}

	/**
	 * @param id
	 */
	@Override
	public void deleteRecipe(String id) {

	}

	/**
	 * @param product
	 */
	@Override
	public void addProduct(Product product) {

	}

	// Implémentation des autres méthodes en utilisant RecipeList
}
