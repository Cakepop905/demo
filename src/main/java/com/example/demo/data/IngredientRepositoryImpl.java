package com.example.demo.data;

import com.example.demo.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class IngredientRepositoryImpl implements IngredientRepository{
    private JdbcTemplate jdbc;


    @Autowired
    public IngredientRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

//    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("select id, name, type from Ingredient", this::mapRowToIngredient);
    }

//    @Override
    public Ingredient findOne(String id) {
        return jdbc.queryForObject("select id, name, type from Ingredient where id=?", this::mapRowToIngredient, id);
    }

//    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbc.update("insert into Ingredient(id,name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getType(),
        ingredient.getName());
        return ingredient;
    }
private Ingredient mapRowToIngredient(ResultSet resultSet, int rowNum) throws SQLException{
        return new Ingredient(
                resultSet.getString("id"),
                resultSet.getString("name"),
                Ingredient.Type.valueOf(resultSet.getString("type"))
        );
    }
}