package com.example.demo.data;
import com.example.demo.domain.Ingredient;

public interface IngredientRepository extends crudRepository<ingredient, String> {
    Iterable<Ingredient> findAll();
    Ingredient findOne(String id);
    Ingredient save(Ingredient ingredient);
}
