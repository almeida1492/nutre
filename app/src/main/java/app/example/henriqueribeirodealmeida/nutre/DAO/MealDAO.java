package app.example.henriqueribeirodealmeida.nutre.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import app.example.henriqueribeirodealmeida.nutre.Entities.Meal;

import java.util.List;

@Dao
public interface MealDAO {

    @Insert
    void insert(Meal meal);

    @Query("DELETE FROM meals")
    void deleteAll();

    @Query("SELECT * from meals ORDER BY name ASC")
    LiveData<List<Meal>> getAllMeals();

    @Query("SELECT * from meals ORDER BY name ASC")
    List<Meal> getMeals();

    @Query("SELECT * FROM meals WHERE id IN(:mealIds)")
    LiveData<List<Meal>> findByIds(int[] mealIds);
    //TESTE
    @Query("DELETE FROM meals WHERE id IN(:mealId)")
    LiveData<List<Meal>> deleteByIds(int[] mealId);



}