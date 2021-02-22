package app.example.henriqueribeirodealmeida.nutre.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import app.example.henriqueribeirodealmeida.nutre.DAO.DailyMealDAO;
import app.example.henriqueribeirodealmeida.nutre.DAO.MealDAO;
import app.example.henriqueribeirodealmeida.nutre.Database.MealRoomDatabase;
import app.example.henriqueribeirodealmeida.nutre.Entities.DailyMeal;
import app.example.henriqueribeirodealmeida.nutre.Entities.Meal;

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

    public List<Meal> getMeals() { return mMealDao.getMeals(); }//2

    public LiveData<List<Meal>> findByIds(int[] ids) { return mMealDao.findByIds(ids); }

    public void insert (Meal meal) {
        new insertAsyncTask(mMealDao).execute(meal);
    }

    public void delete (Meal meal) {
        new MealRepository.deleteAsyncTask(mMealDao).execute(meal);
    }

    public void update (Meal meal){ new MealRepository.updateAsyncTask(mMealDao).execute(meal);}


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

    private static class deleteAsyncTask extends AsyncTask<Meal, Void, Void> {

        private MealDAO mAsyncTaskDao;

        deleteAsyncTask(MealDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Meal... params) {
            mAsyncTaskDao.deleteMeal(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Meal, Void, Void> {

        private MealDAO mAsyncTaskDao;

        updateAsyncTask(MealDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Meal... params) {
            mAsyncTaskDao.updateMeal(params[0]);
            return null;
        }
    }
}