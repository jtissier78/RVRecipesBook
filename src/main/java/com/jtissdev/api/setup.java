package com.jtissdev.api;

import com.jtissdev.databaseconnect.DbGestion;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Setup {

	private final DbGestion dbGestion;

	public Setup(DbGestion dbGestion) {
		this.dbGestion = dbGestion;
	}

	public void createDatabaseAndTables() {
		try (Connection connection = dbGestion.getConnexion("root")) {
			connection.setAutoCommit(false);

			// Création de la base de données
			createDatabase(connection);

			// Création des tables
			createTable(connection, "steps", "id INT AUTO_INCREMENT PRIMARY KEY, text TEXT");
			createTable(connection, "products", "id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255)");
			createTable(connection, "ingredients", "id INT AUTO_INCREMENT PRIMARY KEY, " +
					                                       "recipe_id VARCHAR(255), product_id INT, " +
					                                       "quantity VARCHAR(255), unit VARCHAR(255), " +
					                                       "FOREIGN KEY (recipe_id) REFERENCES recipes(id), " +
					                                       "FOREIGN KEY (product_id) REFERENCES products(id)");

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createDatabase(Connection connection) throws SQLException {
		String query = "CREATE DATABASE IF NOT EXISTS testUMBD";
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(query);
		}
	}

	private void createTable(Connection connection, String tableName, String tableDefinition) throws SQLException {
		String query = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + tableDefinition + ")";
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(query);
		}
	}

	public void insertData(JSONObject recipe) {
		try (Connection connection = dbGestion.getConnexion("root")) {
			connection.setAutoCommit(false);

			// Insertion des étapes
			JSONArray steps = (JSONArray) recipe.get("steps");
			for (Object stepObj : steps) {
				JSONObject step = (JSONObject) stepObj;
				int stepId = insertStep(connection, step);
				// Vous pouvez stocker stepId pour référence future si nécessaire
			}

			// Insertion des ingrédients
			JSONArray ingredients = (JSONArray) recipe.get("ingredients");
			for (Object ingredientObj : ingredients) {
				JSONObject ingredient = (JSONObject) ingredientObj;
				int productId = insertProduct(connection, ingredient);
				int ingredientId = insertIngredient(connection, recipe, ingredient, productId);
				// Vous pouvez stocker ingredientId pour référence future si nécessaire
			}

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int insertStep(Connection connection, JSONObject step) throws Exception {
		String query = "INSERT INTO steps (text) VALUES (?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, (String) step.get("text"));
			preparedStatement.executeUpdate();
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);
			}
			throw new Exception("Failed to get step ID");
		}
	}

	private int insertProduct(Connection connection, JSONObject ingredient) throws Exception {
		String productName = (String) ingredient.get("productInfo");
		int productId = getProductId(connection, productName);

		if (productId == -1) {
			String productQuery = "INSERT INTO products (name) VALUES (?)";
			try (PreparedStatement productStatement = connection.prepareStatement(productQuery, Statement.RETURN_GENERATED_KEYS)) {
				productStatement.setString(1, productName);
				productStatement.executeUpdate();
				ResultSet generatedKeys = productStatement.getGeneratedKeys();
				if (generatedKeys.next()) {
					productId = generatedKeys.getInt(1);
				} else {
					throw new Exception("Failed to get product ID");
				}
			}
		}

		return productId;
	}

	private int getProductId(Connection connection, String productName) throws Exception {
		String query = "SELECT id FROM products WHERE name = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, productName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("id");
			}
			return -1;
		}
	}

	private int insertIngredient(Connection connection, JSONObject recipe, JSONObject ingredient, int productId) throws Exception {
		String query = "INSERT INTO ingredients (recipe_id, product_id, quantity, unit) VALUES (?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, (String) recipe.get("id"));
			preparedStatement.setInt(2, productId);
			preparedStatement.setString(3, (String) ingredient.get("quantity"));
			preparedStatement.setString(4, (String) ingredient.get("unit"));
			preparedStatement.executeUpdate();
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);
			}
			throw new Exception("Failed to get ingredient ID");
		}
	}

	public static void main(String[] args) {
		try (InputStream input = new FileInputStream("path/to/application-dev.properties")) {
			Properties properties = new Properties();
			properties.load(input);
			DbGestion dbGestion = new DbGestion(input, yourStructureJSONObject);
			Setup setup = new Setup(dbGestion);

			// Charger votre recette depuis le fichier JSON ou toute autre source
			JSONObject recipe = yourRecipeJSONObject;
			setup.insertData(recipe);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

