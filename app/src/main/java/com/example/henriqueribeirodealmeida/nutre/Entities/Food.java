package com.example.henriqueribeirodealmeida.nutre.Entities;

public class Food {

    private String name;
    private double quantity;
    private String measure;

    public Food(String name, double quantity, String measure){
        this.name = name;
        this.quantity = quantity;
        this.measure = measure;
    }

    public String getName(){
        return name;
    }

    public double getQuantity(){
        return quantity;
    }

    public String getMeasure(){
        return measure;
    }
}
