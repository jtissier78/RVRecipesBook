-- Création de la base de données
CREATE DATABASE IF NOT EXISTS testUMBD;

-- Utilisation de la base de données
USE testUMBD;

-- Création de la table des recettes
CREATE TABLE IF NOT EXISTS recipes (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    season VARCHAR(50),
    mealType VARCHAR(50),
    difficulty VARCHAR(50) NOT NULL,
    prepTime INT NOT NULL,
    cookTime INT NOT NULL
);

-- Création de la table d'informations sur les produits
CREATE TABLE IF NOT EXISTS products (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Création de la table des étapes de la recette
CREATE TABLE IF NOT EXISTS steps (
    id INT PRIMARY KEY,
    recipeId INT,
    orderNum INT NOT NULL,
    text TEXT NOT NULL,
    FOREIGN KEY (recipeId) REFERENCES recipes(id)
);

-- Création de la table des ingrédients
CREATE TABLE IF NOT EXISTS ingredients (
    id INT PRIMARY KEY,
    recipeId INT,
    mainIngredient BOOLEAN NOT NULL,
    quantity DOUBLE,
    unit VARCHAR(50),
    productInfoId INT,
    FOREIGN KEY (recipeId) REFERENCES recipes(id),
    FOREIGN KEY (productInfoId) REFERENCES products(id)
);

-- Création de la table de liens entre ingrédients et recettes
CREATE TABLE IF NOT EXISTS recipeIngredients (
    id INT PRIMARY KEY,
    recipeId INT,
    ingredientId INT,
    FOREIGN KEY (recipeId) REFERENCES recipes(id),
    FOREIGN KEY (ingredientId) REFERENCES ingredients(id)
);
