package com.example.delivery_app_dath.Modal;

public class Vehicle {
    private String name;
    private String description;
    private String box;
    private String weight;
    private int amountperkm;

    public Vehicle() {
    }

    public Vehicle(String name, String description, String box, String weight, int amountperkm) {
        this.name = name;
        this.description = description;
        this.box = box;
        this.weight = weight;
        this.amountperkm = amountperkm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getAmountperkm() {
        return amountperkm;
    }

    public void setAmountperkm(int amountperkm) {
        this.amountperkm = amountperkm;
    }
}
