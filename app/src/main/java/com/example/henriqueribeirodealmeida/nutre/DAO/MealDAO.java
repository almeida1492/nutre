package com.example.henriqueribeirodealmeida.nutre.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.henriqueribeirodealmeida.nutre.Entities.Meal;

import java.util.List;

@Dao
public interface MealDAO {

    @Insert
    void insert(Meal meal);

    @Query("DELETE FROM meals")
    void deleteAll();

    @Query("SELECT * from meals ORDER BY name ASC")
    LiveData<List<Meal>> getAllMeals();
}