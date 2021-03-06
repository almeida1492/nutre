package app.example.henriqueribeirodealmeida.nutre.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import app.example.henriqueribeirodealmeida.nutre.DAO.FoodDAO;
import app.example.henriqueribeirodealmeida.nutre.Database.MealRoomDatabase;
import app.example.henriqueribeirodealmeida.nutre.Entities.Food;

import java.util.List;

public class FoodRepository {

    private FoodDAO mFoodDao;

    public FoodRepository(Application application) {
        MealRoomDatabase db = MealRoomDatabase.getDatabase(application);
        mFoodDao = db.foodDao();
    }

    public LiveData<List<Food>> getAllDailyMealFood(int dailyMealId) {
        return mFoodDao.findDailyMealFoods(dailyMealId);
    }

    public LiveData<List<Food>> getAllFood(String date) {
        return mFoodDao.getAllFood(date);
    }


    public void insert (Food food) {
        new insertAsyncTask(mFoodDao).execute(food);
    }

    private static class insertAsyncTask extends AsyncTask<Food, Void, Void> {

        private FoodDAO mAsyncTaskDao;

        insertAsyncTask(FoodDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Food... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void delete(Food food) {
        new deleteAsyncTask(mFoodDao).execute(food);
    }

    private static class deleteAsyncTask extends AsyncTask<Food, Void, Void> {

        private FoodDAO mAsyncTaskDao;

        deleteAsyncTask(FoodDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Food... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}