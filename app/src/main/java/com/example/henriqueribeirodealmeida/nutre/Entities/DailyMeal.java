package com.example.henriqueribeirodealmeida.nutre.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "daily_meal")
public class DailyMeal {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String name;
    private String date;

    public DailyMeal() {
    }

    public DailyMeal(String name, String date) {
        this.name = name;
        this.date = date;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return date;
    }

}