package com.thomas.pizzaparadise.Usecases;

import com.thomas.pizzaparadise.Infrastructure.PizzaRepository;
import com.thomas.pizzaparadise.Model.Pizza;
import com.thomas.pizzaparadise.Model.Topping;
import com.thomas.pizzaparadise.PizzaParadiseApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public void createPizza(Pizza pizza) {
        pizzaRepository.createPizza(pizza);
    }

    public List<Pizza> readPizzas() {
        return pizzaRepository.readPizzas();
    }

    public List<Topping> readToppings() {
        return pizzaRepository.readToppings();
    }

    public void updatePizza(Pizza pizza) {
        pizzaRepository.updatePizza(pizza);
    }






}