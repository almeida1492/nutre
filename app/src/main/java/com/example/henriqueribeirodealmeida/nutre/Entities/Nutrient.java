package com.example.henriqueribeirodealmeida.nutre.Entities;

import android.support.v7.app.AppCompatActivity;

public class Nutrient extends AppCompatActivity {


    private String name;
    private double value;
    private double suggestedValue;
    private String measure;
    private int human;


    public Nutrient() {
    }

    public Nutrient(String name, int value, String measure) {
        this.name = name;
        this.value = value;
        this.measure = measure;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;

    }

    public void setValue(int value) {
        this.value = value;

    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public double getSuggestedValue() {
        return suggestedValue;
    }

    public void setSuggestedValue(double suggestedValue) {
        this.suggestedValue = suggestedValue;

    }






}


