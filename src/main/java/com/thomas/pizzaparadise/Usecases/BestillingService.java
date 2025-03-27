package com.thomas.pizzaparadise.Usecases;

import com.thomas.pizzaparadise.Infrastructure.BestillingRepository;
import com.thomas.pizzaparadise.Infrastructure.PizzaRepository;
import com.thomas.pizzaparadise.Model.Bestilling;
import com.thomas.pizzaparadise.Model.Pizza;
import com.thomas.pizzaparadise.Model.Topping;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BestillingService {

    private final BestillingRepository bestillingRepository;

    public BestillingService(BestillingRepository bestillingRepository) {
        this.bestillingRepository = bestillingRepository;
    }

    public void createBestillingsInfo(Bestilling bestilling) {
        bestillingRepository.createBestillingsInfo(bestilling);
    }

    public List<Bestilling> readBestillingsInfo() {
        return bestillingRepository.readBestillingsInfo();
    }

    public void updateBestillingsInfo(Bestilling bestilling) {
        bestillingRepository.updateBestillingsInfo(bestilling);
    }

    public void deleteBestillingsInfo(Bestilling bestilling) {
        bestillingRepository.deleteBestillingsInfo(bestilling);
    }

    public void createFuldBestilling(Pizza pizza, Bestilling bestilling, List<Topping> toppings) {
        bestillingRepository.createFuldBestilling(pizza, bestilling, toppings);
    }

}
