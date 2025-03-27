package com.thomas.pizzaparadise.Infrastructure;

import com.thomas.pizzaparadise.Model.Bestilling;
import com.thomas.pizzaparadise.Model.Pizza;
import com.thomas.pizzaparadise.Model.Topping;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

@Repository
public class BestillingRepository {
    private JdbcTemplate jdbcTemplate;

    public BestillingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createBestillingsInfo(Bestilling bestilling) {
        String sql = "INSERT INTO pizza_paradise.bestilling (fk_bruger_id, bestilling_dato_tid, bestilling_pris,) VALUES (?, ?, ?)";
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setInt(1, bestilling.getBruger().getId());
            preparedStatement.setTimestamp(2, bestilling.getTimestamp());
            preparedStatement.setDouble(3, bestilling.getTotalPrice());
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    System.out.println("Key generated");
                }
            }
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    public List<Bestilling> readBestillingsInfo() {
        String sql = "SELECT * FROM pizza_paradise.bestilling";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Bestilling.class));
    }

    public void updateBestillingsInfo(Bestilling bestilling) {
        String sql = "UPDATE bestilling SET fk_bruger_id = ?, bestilling_dato_tid = ?, bestilling_pris = ? WHERE id = ?";
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bestilling.getBruger().getId());
            preparedStatement.setTimestamp(2, bestilling.getTimestamp());
            preparedStatement.setDouble(3, bestilling.getTotalPrice());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBestillingsInfo(Bestilling bestilling) {
        String sql = "DELETE FROM bestilling WHERE id = ?";
        jdbcTemplate.update(sql, bestilling.getId());
    }


    public void createFuldBestilling(Pizza pizza, Bestilling bestilling, List<Topping> toppings) {

        String insertBestillingSql = "INSERT INTO pizza_paradise.bestilling (fk_bruger_id, bestilling_dato_tid, bestilling_pris) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertBestillingSql, bestilling.getBruger().getId(), bestilling.getTimestamp(), bestilling.getTotalPrice());

        // henter ID fra sidste oprettede bestilling
        int bestillingId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        // 2. opret pizza
        String insertPizzaSql = "INSERT INTO pizza_paradise.pizza (pizza_name, pizza_beskrivelse, pizza_pris) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertPizzaSql, pizza.getName(), pizza.getDescription(), pizza.getPrice());

        // få ID fra pizza
        int pizzaId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        // indsætter topping
        String insertToppingSql = "INSERT INTO pizza_paradise.bestilling_har_pizza_og_topping (fk_pizza, fk_bestilling, fk_topping) VALUES (?, ?, ?)";

        for (Topping topping : toppings) {
            jdbcTemplate.update(insertToppingSql, pizzaId, bestillingId, topping.getId());
        }

        System.out.println("Ordre oprettet med ID: " + bestillingId);
    }


}

