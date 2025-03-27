package com.thomas.pizzaparadise.Controller;

import com.thomas.pizzaparadise.Usecases.BestillingService;
import org.springframework.stereotype.Controller;

@Controller
public class BestillingController {

    private final BestillingService service;

    public BestillingController(BestillingService service) {
        this.service = service;
    }


}
