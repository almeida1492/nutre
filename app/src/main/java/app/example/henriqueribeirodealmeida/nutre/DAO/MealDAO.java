package app.example.henriqueribeirodealmeida.nutre.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import app.example.henriqueribeirodealmeida.nutre.Entities.DailyMeal;
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

    @Delete
    void deleteMeal(Meal meal);

    @Update
    void updateMeal(Meal meal);

    //teste
    /*
    @Query("UPDATE meals SET name = :name , " +
            "measureLabel = :measureLabel," +
            "unityMultiplier = :unityMultiplier," +
            "unity =:unity, " +
            "energy = :energy, " +
            "water =:water, " +
            "carbohydrate =:carbohydrate," +
            "protein =:protein," +
            "totalFat=:totalFat, " +
            "saturatedFat=:saturatedFat, " +
            "fibers =:fibers,"+
            "sodium=:sodium,"+
            "vitaminC=:vitaminC,"+
            "calcium=:calcium,"+
            "iron=:iron,"+
            "vitaminA=:vitaminA,"+
            "potassium=:potassium,"+
            "magnesium=:magnesium,"+
            "thiamine=:thiamine,"+
            "riboflavin=:riboflavin,"+
            "niacin=:niacin" +
            " WHERE id = :id")
    void updateMeal(int id,
                    String name,
                    String measureLabel,
                    float unityMultiplier,
                    String unity ,
                    float energy,
                    float water,
                    float carbohydrate,
                    float protein,
                    float totalFat,
                    float saturatedFat,
                    float fibers,
                    float sodium,
                    float vitaminC,
                    float calcium,
                    float iron,
                    float vitaminA,
                    float potassium,
                    float magnesium,
                    float thiamine,
                    float riboflavin,
                    float niacin);

     */




}