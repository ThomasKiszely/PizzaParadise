package com.thomas.pizzaparadise.Model;
import java.util.ArrayList;

public class Pizza {
    private int id;
    private String name;
    private String description;
    private double price;
    private Topping topping;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Topping getTopping() {
        return topping;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    private ArrayList<Topping> toppings;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pizza(){}

    public Pizza(int id, String name, String description, double price, ArrayList<Topping> toppings) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.toppings = toppings;
    }
    @Override
    public String toString() {
        return name;
    }






}
