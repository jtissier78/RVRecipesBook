package com.jtissdev.api.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  id;
	private String name;
	private String difficulty;
	private String prepTime;
	private String cookTime;
	private List<Ingredient> ingredients;
	private List<Step> steps;
	public Long getId() {
		return null;
	}
}
