package com.thomas.pizzaparadise.Controller;

import com.thomas.pizzaparadise.Model.Bruger;
import com.thomas.pizzaparadise.Usecases.BrugerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BrugerController {

    @Autowired
    BrugerService brugerService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("bruger", new Bruger());
        return "index";
    }

    @PostMapping("/index")
    public String loginBruger(@ModelAttribute Bruger bruger, Model model) {
       String email = bruger.getEmail();
       String password = bruger.getPassword();
        System.out.println("email: " + email + ", password: " + password);
        model.addAttribute("email", email);
        model.addAttribute("password", password);
        if (brugerService.loginBruger(email, password) != null) {
            return "redirect:/menu";
        } else {
            model.addAttribute("error", "Forkert email eller password");
            return "index";
        }
    }

    @GetMapping("/register")
    public String registerBruger(Model model) {
        model.addAttribute("bruger", new Bruger());
        return "register";
    }

    @PostMapping("/register")
    public String registerBruger(@ModelAttribute("bruger") Bruger bruger, Model model) {
        model.addAttribute("bruger", bruger);
        brugerService.registerBruger(bruger);
        return "redirect:/";
    }

}
