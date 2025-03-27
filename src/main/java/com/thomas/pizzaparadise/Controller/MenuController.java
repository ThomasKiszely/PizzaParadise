package com.thomas.pizzaparadise.Controller;


import com.thomas.pizzaparadise.Model.Bestilling;
import com.thomas.pizzaparadise.Model.Pizza;
import com.thomas.pizzaparadise.Model.Topping;
import com.thomas.pizzaparadise.Usecases.BestillingService;
import com.thomas.pizzaparadise.Usecases.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MenuController {


    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private BestillingService bestillingService;

    @GetMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("topping", new Topping());
        model.addAttribute("bestilling", new Bestilling());
        model.addAttribute("pizzas", pizzaService.readPizzas());
        model.addAttribute("toppings", pizzaService.readToppings());
        return "menu";
    }


    @PostMapping("/menu")
    public String menuSumbit(@ModelAttribute Pizza pizza, @ModelAttribute Topping topping,  @ModelAttribute Bestilling bestilling , Model model) {
        model.addAttribute("pizza", pizza);
        model.addAttribute("topping", topping);
        model.addAttribute("bestilling", bestilling);
        bestillingService.createBestillingsInfo(bestilling);
        return "redirect:/menu";
    }

}
