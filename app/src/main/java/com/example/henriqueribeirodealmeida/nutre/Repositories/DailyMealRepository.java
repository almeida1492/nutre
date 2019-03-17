package com.example.henriqueribeirodealmeida.nutre.Repositories;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.henriqueribeirodealmeida.nutre.DAO.DailyMealDAO;
import com.example.henriqueribeirodealmeida.nutre.Database.MealRoomDatabase;
import com.example.henriqueribeirodealmeida.nutre.Entities.DailyMeal;
import com.example.henriqueribeirodealmeida.nutre.Entities.Food;
import com.example.henriqueribeirodealmeida.nutre.FoodViewModel;

import java.util.ArrayList;
import java.util.List;

public class DailyMealRepository {

    private DailyMealDAO mDailyMealDao;
    private LiveData<List<DailyMeal>> mAllDailyMeals;

    public DailyMealRepository(Application application) {
        MealRoomDatabase db = MealRoomDatabase.getDatabase(application);
        mDailyMealDao = db.dailyMealDao();
        mAllDailyMeals = mDailyMealDao.getAllDailyMeals();
    }

    public LiveData<List<DailyMeal>> getAllDailyMeals() {
        return mAllDailyMeals;
    }


    public void insert (DailyMeal dailyMeal, ArrayList foods, FoodViewModel foodViewModel, Activity activity) {
        new insertAsyncTask(mDailyMealDao, foods, foodViewModel, activity).execute(dailyMeal);
    }

    private static class insertAsyncTask extends AsyncTask<DailyMeal, Void, Long> {

        private DailyMealDAO mAsyncTaskDao;
        private ArrayList<Food> mFoods;
        private FoodViewModel mFoodViewModel;
        private Activity mActivity;

        insertAsyncTask(DailyMealDAO dao, ArrayList<Food> foods, FoodViewModel foodViewModel, Activity activity) {
            mAsyncTaskDao = dao;
            mFoods = foods;
            mFoodViewModel = foodViewModel;
            mActivity = activity;
        }

        @Override
        protected Long doInBackground(final DailyMeal... params) {
            return mAsyncTaskDao.insert(params[0]);
        }

        @Override
        protected void onPostExecute(Long dailyMealId) {
            for (Food food : mFoods) {
                food.setDailyMealId(dailyMealId.intValue());
                mFoodViewModel.insert(food);
            }
            mActivity.finish();
        }
    }
}