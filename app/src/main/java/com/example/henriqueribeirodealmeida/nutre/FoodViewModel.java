package com.example.henriqueribeirodealmeida.nutre;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.henriqueribeirodealmeida.nutre.Entities.Food;
import com.example.henriqueribeirodealmeida.nutre.Entities.Meal;
import com.example.henriqueribeirodealmeida.nutre.Repositories.FoodRepository;
import com.example.henriqueribeirodealmeida.nutre.Repositories.MealRepository;

import java.util.List;

public class FoodViewModel extends AndroidViewModel {

    private FoodRepository mRepository;

    private LiveData<List<Food>> mAllFood;

    public FoodViewModel (Application application) {
        super(application);
        mRepository = new FoodRepository(application);
    }

    LiveData<List<Food>> getAllFood() { return mRepository.getAllFood(); }

    LiveData<List<Food>> findDailyMealFoods(int dailyMealId) { return mRepository.getAllDailyMealFood(dailyMealId); }

    public void insert(Food food) { mRepository.insert(food); }
}