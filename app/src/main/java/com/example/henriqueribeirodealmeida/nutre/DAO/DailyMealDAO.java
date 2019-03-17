package com.example.henriqueribeirodealmeida.nutre.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.henriqueribeirodealmeida.nutre.Entities.DailyMeal;
import com.example.henriqueribeirodealmeida.nutre.Entities.Food;
import com.example.henriqueribeirodealmeida.nutre.Entities.Meal;

import java.util.List;

@Dao
public interface DailyMealDAO {

    @Insert
    long insert(DailyMeal dailyMeal);

    @Query("DELETE FROM daily_meal")
    void deleteAll();

    @Query("SELECT * from daily_meal ORDER BY name ASC")
    LiveData<List<DailyMeal>> getAllDailyMeals();
}