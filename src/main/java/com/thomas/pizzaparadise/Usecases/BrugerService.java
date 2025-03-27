package com.thomas.pizzaparadise.Usecases;

import com.thomas.pizzaparadise.Infrastructure.BrugerRepository;
import com.thomas.pizzaparadise.Model.Bruger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrugerService {

    private BrugerRepository brugerRepository;
    private Bruger bruger = new Bruger();

    public BrugerService(BrugerRepository brugerRepository) {
        this.brugerRepository = brugerRepository;
    }

    public void registerBruger(Bruger bruger) {
        this.bruger = bruger;
        brugerRepository.createBruger(bruger);
    }

    public void setCurrentBruger(Bruger currentBruger) {
        this.bruger = currentBruger;
    }

    public Bruger getCurrentBruger() {
        return bruger;
    }

    public Bruger loginBruger(String email, String password) {
        List<Bruger> brugere = brugerRepository.readBrugere();
            for (Bruger bruger : brugere) {
                System.out.println("Checking bruger " + bruger.getEmail() + " " + bruger.getPassword());
                if (email.equals(bruger.getEmail()) && password.equals(bruger.getPassword())) {
                    setCurrentBruger(bruger);
                    System.out.println("Current Bruger: " + getCurrentBruger());
                    return bruger;
                }
            }
            return null;
        }
}
