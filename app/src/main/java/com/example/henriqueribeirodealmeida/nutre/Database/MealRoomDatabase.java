package com.example.henriqueribeirodealmeida.nutre.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.henriqueribeirodealmeida.nutre.DAO.DailyMealDAO;
import com.example.henriqueribeirodealmeida.nutre.DAO.FoodDAO;
import com.example.henriqueribeirodealmeida.nutre.DAO.MealDAO;
import com.example.henriqueribeirodealmeida.nutre.Entities.DailyMeal;
import com.example.henriqueribeirodealmeida.nutre.Entities.Food;
import com.example.henriqueribeirodealmeida.nutre.Entities.Meal;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Pattern;

@Database(entities = {Meal.class, Food.class, DailyMeal.class}, version = 6, exportSchema = false)
public abstract class MealRoomDatabase extends RoomDatabase {

    public abstract MealDAO mealDao();
    public abstract FoodDAO foodDao();
    public abstract DailyMealDAO dailyMealDao();

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
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE, CONTEXT).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final MealDAO mealDao;
        private final FoodDAO foodDao;
        private final Context mContext;

        PopulateDbAsync(MealRoomDatabase db, Context context) {
            mealDao = db.mealDao();
            foodDao = db.foodDao();
            mContext = context;
        }


        public Float convertStringToFloat(String str) {
            try {
                return Float.parseFloat(str);
            } catch (Exception e) {
                Log.d("DEBUG_PARSE", "Could not parse string to float: " + str);
            }

            return 0.0f;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            List<Meal> meals = mealDao.getMeals();
            boolean alreadyHasMeals = !meals.isEmpty();
            if (alreadyHasMeals) {
                return null;
            }
            mealDao.deleteAll();
            try {
                String file = "data/food.csv";
                InputStream in = mContext.getAssets().open(file);
                InputStreamReader inputStreamReader = new InputStreamReader(in);
                BufferedReader buffer = new BufferedReader(inputStreamReader);

                String line;
                while ((line = buffer.readLine()) != null) {
                    String[] str = parseCSVLine(line);
                    if (str != null && str.length > 17) {
                        String measureLabel = null;
                        float baseQuantity;
                        try {
                            measureLabel = str[18];
                        } catch (Exception e) {
                            Log.e("MEASURE_LABEL", e.toString());
                        }

                        try {
                            baseQuantity = Float.parseFloat(str[19]);
                        } catch (Exception e) {
                            Log.e("MEASURE_LABEL", e.toString());
                            baseQuantity = 1.0f;
                        }

                        if (measureLabel == null || measureLabel.trim().equals("")) {
                            measureLabel = "unidade";
                        }
                        Meal meal = new Meal((str[0]), convertStringToFloat(str[1]), convertStringToFloat(str[2]), convertStringToFloat(str[3]), convertStringToFloat(str[4]), convertStringToFloat(str[5]), convertStringToFloat(str[6]), convertStringToFloat(str[7]), convertStringToFloat(str[8]), convertStringToFloat(str[9]), convertStringToFloat(str[10]), convertStringToFloat(str[11]), convertStringToFloat(str[12]), convertStringToFloat(str[13]), convertStringToFloat(str[14]), convertStringToFloat(str[15]), convertStringToFloat(str[16]), convertStringToFloat(str[17]), measureLabel, baseQuantity);
                        mealDao.insert(meal);
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