package com.thomas.pizzaparadise.Model;

public class Bruger {

    private int id;
    private String email;
    private String password;
    private int bonuspoint;

    public Bruger(int id, String email, String password, int bonuspoint) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.bonuspoint = bonuspoint;
    }
    public Bruger() {}

    public int getId() {
        return id;
    }
    public int getBonuspoint() {
        return bonuspoint;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
