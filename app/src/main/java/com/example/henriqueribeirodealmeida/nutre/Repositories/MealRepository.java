package com.example.henriqueribeirodealmeida.nutre.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.henriqueribeirodealmeida.nutre.DAO.MealDAO;
import com.example.henriqueribeirodealmeida.nutre.Database.MealRoomDatabase;
import com.example.henriqueribeirodealmeida.nutre.Entities.Meal;

import java.util.List;

public class MealRepository {

    private MealDAO mMealDao;
    private LiveData<List<Meal>> mAllMeals;

    public MealRepository(Application application) {
        MealRoomDatabase db = MealRoomDatabase.getDatabase(application);
        mMealDao = db.mealDao();
        mAllMeals = mMealDao.getAllMeals();
    }

    public LiveData<List<Meal>> getAllMeals() {
        return mAllMeals;
    }

    public List<Meal> getMeals() { return mMealDao.getMeals(); }

    public LiveData<List<Meal>> findByIds(int[] ids) { return mMealDao.findByIds(ids); }


    public void insert (Meal meal) {
        new insertAsyncTask(mMealDao).execute(meal);
    }

    private static class insertAsyncTask extends AsyncTask<Meal, Void, Void> {

        private MealDAO mAsyncTaskDao;

        insertAsyncTask(MealDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Meal... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}