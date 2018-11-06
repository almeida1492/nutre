package com.example.henriqueribeirodealmeida.nutre.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.henriqueribeirodealmeida.nutre.DAO.MealDAO;
import com.example.henriqueribeirodealmeida.nutre.Entities.Meal;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

@Database(entities = {Meal.class}, version = 2, exportSchema = false)
public abstract class MealRoomDatabase extends RoomDatabase {

    public abstract MealDAO mealDao();

    private static volatile MealRoomDatabase INSTANCE;
    private static volatile Context CONTEXT;

    public static MealRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MealRoomDatabase.class) {
                if (INSTANCE == null) {
                    CONTEXT = context;
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
                    new PopulateDbAsync(INSTANCE, CONTEXT).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final MealDAO mDao;
        private final Context mContext;

        PopulateDbAsync(MealRoomDatabase db, Context context) {
            mDao = db.mealDao();
            mContext = context;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            try {
                String file = "data/food.csv";
                InputStream in = mContext.getAssets().open(file);
                InputStreamReader inputStreamReader = new InputStreamReader(in);
                BufferedReader buffer = new BufferedReader(inputStreamReader);

                String line;
                while ((line = buffer.readLine()) != null) {
                    String[] str = parseCSVLine(line);
                    if (str != null && str.length > 17) {
                        Meal meal = new Meal(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8], str[9], str[10], str[11], str[12], str[13], str[14], str[15], str[16], str[17]);
                        mDao.insert(meal);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        public static String[] parseCSVLine(String line) {
            // Create a pattern to match breaks
            Pattern p =
                    Pattern.compile(",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))");
            // Split input with the pattern
            String[] fields = p.split(line);
            for (int i = 0; i < fields.length; i++) {
                // Get rid of residual double quotes
                fields[i] = fields[i].replace("\"", "");
            }
            return fields;
        }
    }
}