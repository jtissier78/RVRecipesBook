-- Création de la base de données
CREATE DATABASE IF NOT EXISTS testUMBD;

-- Utilisation de la base de données
USE testUMBD;

-- Enumérations en français
CREATE TYPE mealTypeEnum AS ENUM ('Petit-déjeuner', 'Déjeuner', 'Dîner', 'Collation');
CREATE TYPE difficultyEnum AS ENUM ('Facile', 'Moyen', 'Difficile');
CREATE TYPE seasonEnum AS ENUM ('Printemps_Debut', 'Printemps_Coeur', 'Printemps_Fin','Été_Debut', 'Été_Coeur', 'Été_Fin', 'Automne_Debut', 'Automne_Coeur', 'Automne_Fin','Hiver_Debut', 'Hiver_Coeur', 'Hiver_Fin');
CREATE TYPE ingredientUnits AS ENUM ('pièce', 'gramme', 'millilitre', 'cuillère à café', 'cuillère à soupe');

-- Création de la table des recettes
CREATE TABLE IF NOT EXISTS recipes (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    season seasonEnum,
    mealType mealTypeEnum,
    difficulty difficultyEnum NOT NULL,
    prepTime TIME DEFAULT '00:00:00' NOT NULL,
    cookTime TIME DEFAULT '00:00:00' NOT NULL
);

-- Création de la table d'informations sur les produits
CREATE TABLE IF NOT EXISTS products (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Création de la table des étapes de la recette
CREATE TABLE IF NOT EXISTS steps (
    id INT PRIMARY KEY,
    text TEXT NOT NULL
);

-- Création de la table de liaison entre recettes et étapes
CREATE TABLE IF NOT EXISTS recipeSteps (
    id INT PRIMARY KEY,
    recipeId INT,
    stepId INT,
    orderNum INT NOT NULL,
    FOREIGN KEY (recipeId) REFERENCES recipes(id),
    FOREIGN KEY (stepId) REFERENCES steps(id)
);

-- Création de la table des ingrédients
CREATE TABLE IF NOT EXISTS ingredients (
    id INT PRIMARY KEY,
    recipeId INT,
    mainIngredient BOOLEAN DEFAULT false,
    quantity DOUBLE,
    unit ingredientUnits DEFAULT 'pièce',
    product INT, -- Changer le nom de la colonne
    FOREIGN KEY (recipeId) REFERENCES recipes(id),
    FOREIGN KEY (product) REFERENCES products(id)
);
