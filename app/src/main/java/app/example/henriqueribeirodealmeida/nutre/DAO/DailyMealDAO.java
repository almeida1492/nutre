package app.example.henriqueribeirodealmeida.nutre.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import app.example.henriqueribeirodealmeida.nutre.Entities.DailyMeal;

import java.util.List;

@Dao
public interface DailyMealDAO {

    @Insert
    long insert(DailyMeal dailyMeal);

    @Query("DELETE FROM daily_meal")
    void deleteAll();

    @Query("SELECT * from daily_meal WHERE daily_meal.date LIKE (:date) ORDER BY date DESC LIMIT 15 ")
    LiveData<List<DailyMeal>> getAllDailyMeals(String date);

    @Delete
    void deleteMeal(DailyMeal dailyMeal);

    @Query("UPDATE daily_meal SET name = :name WHERE id = :id")
    void update(int id, String name);
}