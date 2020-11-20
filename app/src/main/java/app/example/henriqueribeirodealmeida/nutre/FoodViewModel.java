package app.example.henriqueribeirodealmeida.nutre;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import app.example.henriqueribeirodealmeida.nutre.Entities.Food;
import app.example.henriqueribeirodealmeida.nutre.Repositories.FoodRepository;

import java.util.List;

public class FoodViewModel extends AndroidViewModel {

    private FoodRepository mRepository;

    private LiveData<List<Food>> mAllFood;

    public FoodViewModel (Application application) {
        super(application);
        mRepository = new FoodRepository(application);
    }

    LiveData<List<Food>> getAllFood(String date) { return mRepository.getAllFood(date); }

    LiveData<List<Food>> findDailyMealFoods(int dailyMealId) {
        return mRepository.getAllDailyMealFood(dailyMealId); }

    public void insert(Food food) { mRepository.insert(food); }

    public void delete(Food food) { mRepository.delete(food); }
}