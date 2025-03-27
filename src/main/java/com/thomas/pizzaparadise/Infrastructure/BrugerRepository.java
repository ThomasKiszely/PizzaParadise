package com.thomas.pizzaparadise.Infrastructure;
import com.thomas.pizzaparadise.Model.Bruger;

import com.thomas.pizzaparadise.Model.Bestilling;
import com.thomas.pizzaparadise.Model.Pizza;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BrugerRepository {

    private JdbcTemplate jdbcTemplate;

    public BrugerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createBruger(Bruger bruger) {
        String sql = "INSERT INTO bruger (bruger_email, bruger_password, bruger_bonuspoint) VALUES (?, ?, ?)";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, bruger.getEmail());
            preparedStatement.setString(2, bruger.getPassword());
            preparedStatement.setInt(3, bruger.getBonuspoint());
            if (preparedStatement.executeUpdate() > 0) {
                System.out.println("Bruger oprettet.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Bruger> readBrugere() {
        String sql = "select * from bruger";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Bruger.class));
    }

    public void updateBruger(Bruger bruger) {
        String sql = "UPDATE bruger SET bruger_email, bruger_password, bruger_bonuspoint WHERE id = ?";
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, bruger.getEmail());
            preparedStatement.setString(2, bruger.getPassword());
            preparedStatement.setInt(3, bruger.getBonuspoint());
            if (preparedStatement.executeUpdate() > 0) {
                System.out.println("Bruger opdateret.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteBruger(Bruger bruger) {
        String sql = "DELETE FROM bruger WHERE id = ?";
        jdbcTemplate.update(sql, bruger.getId());
    }
}