package com.example.demo.data;

import com.example.demo.domain.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import static java.sql.JDBCType.VARCHAR;

@Repository
public class TacoRepositoryImpl implements TacoRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public TacoRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    @Override
    public Taco save(Taco taco) {
     // insert the taco into the table, capture the vvid from the database
        // make the insert a seperate method
        long tacoId = saveTacoInfo(taco);
        // pass the vvid into the taco object with the taco.setId
        taco.setId(tacoId);
        // iterate through each ingredient on the list
        for(String ingredientId: taco.getIngredients()) {
            // save each ingredient & taco.id to the Taco_Table table
            // return taco object
        }
        return taco;
    }
    private long saveTacoInfo(Taco taco){
        taco.setCreateAt(new Date());

        PreparedStatementCreatorFactory pcsf = new PreparedStatementCreatorFactory("Insert into Taco(name, createAt) values(?, ?)", Types.VARCHAR, Types.TIMESTAMP)
pcsf.setReturnGenerateKeys(Boolean.TRUE);

PreparedStatementCreator psc = pcsf.newPreparedStatementCreator(Arrays.asList(taco.getName(), new Timestamp(taco.getcreateAt().getTime)))); {

KeyHolder keyHolder = new GenerateKeyHolder();
jdbc.update(psc, keyHolder);
return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }
    private void saveIngredientTaco(String ingredientId, long tacoId) {
        jdbc.update("insert into Taco_ingredients(taco, ingredient)values(?, ?)"), tacoId, ingredientId;
    }
}
