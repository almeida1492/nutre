package com.example.henriqueribeirodealmeida.nutre;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.henriqueribeirodealmeida.nutre.Entities.Meal;
import com.example.henriqueribeirodealmeida.nutre.Repositories.MealRepository;

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

    public void insert(Meal meal) { mRepository.insert(meal); }
}