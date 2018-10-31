package com.example.henriqueribeirodealmeida.nutre.Database;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.ConditionVariable;
import android.support.annotation.NonNull;
import android.content.res.AssetManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.lang.String;
import java.util.List;



import com.example.henriqueribeirodealmeida.nutre.DAO.MealDAO;
import com.example.henriqueribeirodealmeida.nutre.Entities.Meal;

import static android.widget.Toast.*;

@Database(entities = {Meal.class}, version = 1)
public abstract class MealRoomDatabase extends RoomDatabase {

    public abstract MealDAO mealDao();

    private static volatile MealRoomDatabase INSTANCE;

    public static MealRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MealRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MealRoomDatabase.class, "meal_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
        new RoomDatabase.Callback(){

            @Override
            public void onOpen (@NonNull SupportSQLiteDatabase db){
                super.onOpen(db);
                new PopulateDbAsync(INSTANCE).execute();
            }
        };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final MealDAO mDao;

        PopulateDbAsync(MealRoomDatabase db) {
            mDao = db.mealDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            String mCSVfile = "food.csv";
            AssetManager manager;
            manager = Context.getAssets();
            InputStream inStream = null;
            try {
                inStream = manager.open(mCSVfile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
            
            try {
                InputStream is;
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String line = "";
                while ((line = br.readLine()) != null) {
                    String[] str = line.split(",(?=([^\"]\"[^\"]\")[^\"]$)");

                    Meal meal = new Meal(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8], str[9], str[10], str[11], str[12], str[13], str[14], str[15], str[16], str[17]);
                    mDao.insert(meal);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            }

        private ActionBar makeText(PopulateDbAsync populateDbAsync, String s, int lengthShort) {
            return null;
        }

        public ConditionVariable getAssets() {
            ConditionVariable assets;
            return assets;
        }

        private class CSVReader {
            public List readAll() {
            }
        }
    }
}