package app.example.henriqueribeirodealmeida.nutre.Repositories;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import app.example.henriqueribeirodealmeida.nutre.DAO.DailyMealDAO;
import app.example.henriqueribeirodealmeida.nutre.Database.MealRoomDatabase;
import app.example.henriqueribeirodealmeida.nutre.Entities.DailyMeal;
import app.example.henriqueribeirodealmeida.nutre.Entities.Food;
import app.example.henriqueribeirodealmeida.nutre.FoodViewModel;

import java.util.ArrayList;
import java.util.List;

public class DailyMealRepository {

    private DailyMealDAO mDailyMealDao;
    private LiveData<List<DailyMeal>> mAllDailyMeals;
    private String mDailyMealDate;


    public DailyMealRepository(Application application) {
        MealRoomDatabase db = MealRoomDatabase.getDatabase(application);
        mDailyMealDao = db.dailyMealDao();
    }

    public LiveData<List<DailyMeal>> getAllDailyMeals(String date) {
        return mDailyMealDao.getAllDailyMeals(date);
    }

    //insert
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
        protected   void onPostExecute(Long dailyMealId) {
            for (Food food : mFoods) {
                food.setDailyMealId(dailyMealId.intValue());
                mFoodViewModel.insert(food);
            }
            mActivity.finish();
        }
    }

    public void delete (DailyMeal dailyMeal) {
        new deleteAsyncTask(mDailyMealDao).execute(dailyMeal);
    }

    private static class deleteAsyncTask extends AsyncTask<DailyMeal, Void, Void> {

        private DailyMealDAO mAsyncTaskDao;

        deleteAsyncTask(DailyMealDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final DailyMeal... params) {
            mAsyncTaskDao.deleteMeal(params[0]);
            return null;
        }
    }

        //update
    public void update(DailyMeal dailyMeal, ArrayList newFoods, ArrayList foodsToBeDeleted, FoodViewModel foodViewModel, Activity activity) {
        new updateAsyncTask(mDailyMealDao, newFoods, foodsToBeDeleted, foodViewModel, activity).execute(dailyMeal);
    }

    private static class updateAsyncTask extends AsyncTask<DailyMeal, Void, Void> {

        private DailyMealDAO mAsyncTaskDao;
        private ArrayList<Food> mFoods;
        private ArrayList<Food> mFoodsToBeDeleted;
        private FoodViewModel mFoodViewModel;
        private Activity mActivity;

        updateAsyncTask(DailyMealDAO dao, ArrayList<Food> foods, ArrayList<Food> foodsToBeDeleted, FoodViewModel foodViewModel, Activity activity) {
            mAsyncTaskDao = dao;
            mFoods = foods;
            mFoodsToBeDeleted = foodsToBeDeleted;
            mFoodViewModel = foodViewModel;
            mActivity = activity;
        }

        @Override
        protected Void doInBackground(final DailyMeal ...params) {
            mAsyncTaskDao.update(params[0].getId(), params[0].getName());
            for (Food food : mFoods) {
                food.setDailyMealId(params[0].getId());
                mFoodViewModel.insert(food);
            }
            for (Food food : mFoodsToBeDeleted) {
                mFoodViewModel.delete(food);
            }
            mActivity.finish();
            return null;
        }
    }
}