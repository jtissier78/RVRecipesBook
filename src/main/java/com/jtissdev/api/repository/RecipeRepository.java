package com.jtissdev.api.repository;

import com.jtissdev.api.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository  extends JpaRepository<Recipe, Long> {
}
