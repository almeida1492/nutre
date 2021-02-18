package app.example.henriqueribeirodealmeida.nutre;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import app.example.henriqueribeirodealmeida.nutre.Entities.DailyMeal;
import app.example.henriqueribeirodealmeida.nutre.Entities.Meal;
import app.example.henriqueribeirodealmeida.nutre.Repositories.MealRepository;

import java.util.List;

public class MealViewModel extends AndroidViewModel {

    private MealRepository mRepository;

    private LiveData<List<Meal>> mAllMeals;

    public MealViewModel (Application application) {
        super(application);
        mRepository = new MealRepository(application);
        mAllMeals = mRepository.getAllMeals();
    }

    LiveData<List<Meal>> getmAllMeals() { return mAllMeals; }

    List<Meal> getMeals() { return mRepository.getMeals(); }

    LiveData<List<Meal>> findByIds(int[] ids) { return mRepository.findByIds(ids); }

    public void delete(Meal meal) {
        mRepository.delete(meal);
    }

    public void insert(Meal meal) { mRepository.insert(meal); }
/*
    public void update (Meal meal) {mRepository.update(
            meal.getId(),
            meal.getName(),
            meal.getMeasureLabel(),
            meal.getUnityMultiplier(),
            meal.getUnity() ,
            meal.getEnergy(),
            meal.getWater(),
            meal.getCarbohydrate(),
            meal.getProtein()  ,
            meal.getTotalFat() ,
            meal.getSaturatedFat(),
            meal.getFibers(),
            meal.getSodium(),
            meal.getVitaminC(),
            meal.getCalcium(),
            meal.getIron(),
            meal.getVitaminA(),
            meal.getPotassium(),
            meal.getMagnesium(),
            meal.getThiamine(),
            meal.getRiboflavin(),
            meal.getNiacin());}

 */

    public void update (Meal meal) {mRepository.update(meal);}
}