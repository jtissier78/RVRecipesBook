package com.jtissdev.api.controller;

import com.jtissdev.api.dao.RecipeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

	@Autowired
	private RecipeDAO recipeDAO;

	// Add your controller methods here
}