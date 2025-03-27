package com.thomas.pizzaparadise.Infrastructure;
import com.thomas.pizzaparadise.Model.Bestilling;
import com.thomas.pizzaparadise.Model.Pizza;
import com.thomas.pizzaparadise.Model.Topping;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PizzaRepository {
    private final JdbcTemplate jdbcTemplate;

    public PizzaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    // PIZZAER
    public void createPizza(Pizza pizza) {
        String sql = "INSERT INTO pizza (pizza_name, pizza_beskrivelse, pizza_pris) VALUES (?, ?, ?)";
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pizza.getName());
            preparedStatement.setString(2, pizza.getDescription());
            preparedStatement.setDouble(3, pizza.getPrice());
            if (preparedStatement.executeUpdate() > 0) {
                System.out.println("Bruger oprettet.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pizza> readPizzas() {
        List<Pizza> pizzas = new ArrayList<>();
        String sql = "select * from pizza";
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("pizza_id");
                    String pizzaName = resultSet.getString("pizza_name");
                    String pizzaDescription = resultSet.getString("pizza_beskrivelse");
                    double price = resultSet.getDouble("pizza_pris");
                    Pizza pizza = new Pizza(id, pizzaName, pizzaDescription, price);
                    pizzas.add(pizza);
                    System.out.println(pizzaName);
                }

            }
        catch (SQLException e){
            e.printStackTrace();
        }
        return pizzas;
    }

    public void updatePizza(Pizza pizza) {
        String sql = "UPDATE pizza SET pizza_name = ?, SET pizza_beskrivelse = ?, SET pizza_pris = ? WHERE id = ?";
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, pizza.getName());
            preparedStatement.setString(2, pizza.getDescription());
            preparedStatement.setDouble(3, pizza.getPrice());
            preparedStatement.setInt(4, pizza.getId());
            if (preparedStatement.executeUpdate() > 0) {
                System.out.println("Bruger opdateret.");
            }
        } catch (SQLException e) {
                 e.printStackTrace();
        }
    }

    public void deletePizza(Pizza pizza) {
        String sql = "DELETE FROM pizza WHERE id = ?";
        jdbcTemplate.update(sql, pizza.getId());
    }


    // TOPPINGS
    public void createTopping(Topping topping) {
        String sql = "INSERT INTO topping (topping_navn, topping_pris) VALUES (?, ?)";
        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Topping.class));
    }

    public List<Topping> readToppings() {
        List<Topping> toppings = new ArrayList<>();
        String sql = "select * from topping";
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("topping_id");
                String toppingName = resultSet.getString("topping_navn");
                double price = resultSet.getDouble("topping_pris");
                Topping topping = new Topping(id, toppingName, price);
                toppings.add(topping);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return toppings;
    }

    public void deleteTopping(Topping topping) {
        String sql = "DELETE FROM topping WHERE id = ?";
        jdbcTemplate.update(sql, topping.getId());
    }


}
