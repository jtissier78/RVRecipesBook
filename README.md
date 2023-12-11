# RV Recipes Book API

## Overview

RV Recipes Book API is the backend service for managing recipes. It provides endpoints to create, retrieve, update, and delete recipes, along with their ingredients and steps.

## Table of Contents

- [Features](#features)
- [Dependencies](#dependencies)
- [Profiles](#profiles)
- [Configuration](#configuration)
- [Build and Run](#build-and-run)
- [License](#license)
- [Contributing](#contributing)
- [Contact](#contact)

## Features

- CRUD operations for recipes
- Management of ingredients and steps for each recipe
- Profile-based configuration for development and production environments
- Integration with external libraries for database access and application management

## Dependencies

- [JUnit](https://junit.org/junit5/) - Testing framework
- [ConfigDataBaseAccess](https://example.com/configdatabaseaccess) - Library for database configuration
- [AppsManager](https://example.com/appsmanager) - Library for application management
- [Spring Boot Starter Web](https://spring.io/projects/spring-boot) - Starter for building RESTful web services

## Profiles

- **dev:** Default profile for development environment
- **prod:** Profile for production environment

## Configuration

Environment-specific configuration files are located in `src/main/resources/env/`. Modify the appropriate configuration file based on the active profile.

Example:

- `application-dev.properties` for development
- `application-prod.properties` for production

## Build and Run

To build and run the project, use the following commands:

```bash
mvn clean install
java -jar target/RV-Recipes-Book-Api.jar
