package com.thomas.pizzaparadise.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Bestilling {
    int id;
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private int totalPrice;
    private Bruger bruger;
    private List<Pizza> pizzas;

    public Bestilling(int id, Timestamp timestamp, int totalPrice, ArrayList<Pizza> pizzas) {
        this.id = id;
        this.timestamp = timestamp;
        this.totalPrice = totalPrice;
        this.pizzas = pizzas;
    }
    public Bestilling() {}

    public int getId() {
        return id;
    }

    public Bruger getBruger() {
        return bruger;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setPizzas(){
        this.pizzas = pizzas;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }
}
