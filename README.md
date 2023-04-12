## Objective

Create a standalone java application which allows users to manage their favourite recipes. It should
allow adding, updating, removing and fetching recipes. Additionally users should be able to filter
available recipes based on one or more of the following criteria:
1. Whether or not the dish is vegetarian
2. The number of servings
3. Specific ingredients (either include or exclude)
4. Text search within the instructions.


For example, the API should be able to handle the following search requests:
1. All vegetarian recipes
2. Recipes that can serve 4 persons and have “potatoes” as an ingredient
3. Recipes without “salmon” as an ingredient that has “oven” in the instructions.

## Requirements
Please ensure that we have some documentation about the architectural choices and also how to
run the application. The project is expected to be delivered as a GitHub (or any other public git
hosting) repository URL.

All these requirements needs to be satisfied:

1. It must be a REST application implemented using Java (use a framework of your choice)
2. Your code should be production-ready.
3. REST API must be documented
4. Data must be persisted in a database
5. Unit tests must be present
6. Integration tests must be present

-----------------------------------------

## Setup guide

#### Minimum Requirements

- Java 11
- Maven 3.x

#### Install the application

1. Make sure you have [Java](https://www.oracle.com/technetwork/java/javase/downloads/jdk13-downloads-5672538.html) and [Maven](https://maven.apache.org) installed

2.Clone the repository
  ```
$ git clone https://github.com/dogaerdik/recipe.git
  ```
3.Open the command line in the source code folder

4.Build project

  ```
  $ mvn package
  ```

Run the tests
  ```
  $ mvn test
  ```


Run the project

  ```
  $cd target/
  
  $ java -jar recipe-0.0.1-SNAPSHOT.jar
  ```

5. Open the swagger-ui with the link below

```text
http://localhost:8085/swagger-ui/#
```

6. Open the h2-database with the link below with credentials

```text
http://localhost:8085/h2-console/

JDBC URL:jdbc:h2:mem:recipe
User Name:	sa
Password:	sa
```


-----------------------------------------
## Architecture
Repository pattern has been used. Hibernate, JPA Data, Junit, Spring Boot, h2-database , Swagger-ui and lombok used for the project.

Ingredient and Recipe entities are separate entities. They have relation with One to Many Unidirectional relationship and with Cascade on delete.

For filtering Predicates and Spring Data JPA was used and parameters can set null values except for boolean values.
Some validations are added to DTO 's and request objects. ModelMapper was added to the configuration and used. For production ready approach build maven added azure-pipeline.yml was created.