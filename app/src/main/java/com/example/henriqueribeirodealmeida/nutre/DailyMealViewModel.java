package com.example.henriqueribeirodealmeida.nutre;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.henriqueribeirodealmeida.nutre.Entities.DailyMeal;
import com.example.henriqueribeirodealmeida.nutre.Entities.Food;
import com.example.henriqueribeirodealmeida.nutre.Entities.Meal;
import com.example.henriqueribeirodealmeida.nutre.Repositories.DailyMealRepository;
import com.example.henriqueribeirodealmeida.nutre.Repositories.FoodRepository;
import com.example.henriqueribeirodealmeida.nutre.Repositories.MealRepository;

import java.util.ArrayList;
import java.util.List;

public class DailyMealViewModel extends AndroidViewModel {

    private DailyMealRepository mRepository;

    private LiveData<List<DailyMeal>> mAllDailyMeals;

    public DailyMealViewModel (Application application) {
        super(application);
        mRepository = new DailyMealRepository(application);
        mAllDailyMeals = mRepository.getAllDailyMeals();
    }

    LiveData<List<DailyMeal>> getAllDailyMeals() { return mAllDailyMeals; }

    public void insert(DailyMeal dailyMeal, ArrayList foods, FoodViewModel foodViewModel, Activity activity) {
        mRepository.insert(dailyMeal, foods, foodViewModel, activity);
    }

    public void delete(DailyMeal dailyMeal) {
        mRepository.delete(dailyMeal);
    }

    public void update(DailyMeal dailyMeal, ArrayList newFoods, ArrayList foodsToBeDeleted, FoodViewModel foodViewModel, Activity activity) {
        mRepository.update(dailyMeal, newFoods, foodsToBeDeleted, foodViewModel, activity);
    }
}