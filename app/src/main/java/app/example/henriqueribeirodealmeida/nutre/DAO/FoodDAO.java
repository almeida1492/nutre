package app.example.henriqueribeirodealmeida.nutre.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import app.example.henriqueribeirodealmeida.nutre.Entities.Food;

import java.util.List;

@Dao
public interface FoodDAO {

    @Insert
    long insert(Food food);

    @Query("DELETE FROM food")
    void deleteAll();

    @Query("SELECT * FROM food WHERE dailyMealId=:dailyMealId")
    LiveData<List<Food>> findDailyMealFoods(final int dailyMealId);

    @Query("SELECT * FROM food f INNER JOIN meals m ON m.id = f.mealId INNER JOIN daily_meal d ON d.id = f.dailyMealId WHERE d.date LIKE (:date)")
    LiveData<List<Food>> getAllFood(String date);

    @Delete
    void delete(Food food);
}