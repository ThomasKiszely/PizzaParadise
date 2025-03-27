package com.thomas.pizzaparadise.Controller;

import com.thomas.pizzaparadise.Usecases.PizzaService;
import org.springframework.stereotype.Controller;

@Controller
public class PizzaController {

    private final PizzaService service;

    public PizzaController(PizzaService service) {
        this.service = service;
    }




}
