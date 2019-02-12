package com.example.henriqueribeirodealmeida.nutre.Entities;

public class Food {

    private String name;
    private double quantityPerUnit;
    private String measure;

    public Food(String name, double quantity, String measure){
        this.name = name;
        this.quantityPerUnit = quantity;
        this.measure = measure;
    }

    public String getName(){
        return name;
    }

    public double getQuantityPerUnit(){
        return quantityPerUnit;
    }

    public String getMeasure(){
        return measure;
    }
}
