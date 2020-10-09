package app.example.henriqueribeirodealmeida.nutre;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

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


    public void insert(Meal meal) { mRepository.insert(meal); }
}