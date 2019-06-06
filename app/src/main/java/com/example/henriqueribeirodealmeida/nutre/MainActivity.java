package com.example.henriqueribeirodealmeida.nutre;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.henriqueribeirodealmeida.nutre.Adapters.MealHistoryAdapter;
import com.example.henriqueribeirodealmeida.nutre.Adapters.SummaryAdapter;
import com.example.henriqueribeirodealmeida.nutre.Data.UserInfoContainer;
import com.example.henriqueribeirodealmeida.nutre.Entities.DailyMeal;
import com.example.henriqueribeirodealmeida.nutre.Entities.Food;
import com.example.henriqueribeirodealmeida.nutre.Entities.Meal;
import com.example.henriqueribeirodealmeida.nutre.Entities.Nutrient;
import com.example.henriqueribeirodealmeida.nutre.Entities.SummaryValues;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int NUTRIENTS_COUNT = 17;

    private SummaryValues summaryValues;
    private ArrayList<Nutrient> summaryItems;
    private SummaryAdapter summaryAdapter;

    private ArrayList<DailyMeal> dailyMeals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView titleView = findViewById(R.id.title);
        RelativeLayout panelView = findViewById(R.id.summary_panel);
        final ListView summaryView = findViewById(R.id.summary);
        final View panelMask = findViewById(R.id.panel_mask);
        final ListView mealHistoryView = findViewById(R.id.meal_history);
        ImageView searchView = findViewById(R.id.search_action);
        ImageView userView = findViewById(R.id.user_action);
        TextView summaryTitle = findViewById(R.id.summary_panel_title);
        TextView summaryHeader = findViewById(R.id.summary_panel_header);
        TextView mealHistoryTitle = findViewById(R.id.meal_history_title);
        ImageView newMealButton = findViewById(R.id.new_meal_action);

        //Set fonts
        Typeface balooChettanType = Typeface.createFromAsset(getAssets(), "fonts/BalooChettan-Regular.ttf");
        Typeface notoSansType = Typeface.createFromAsset(getAssets(), "fonts/NotoSans-Regular.ttf");
        titleView.setTypeface(balooChettanType);
        summaryTitle.setTypeface(notoSansType);
        summaryHeader.setTypeface(notoSansType);
        mealHistoryTitle.setTypeface(notoSansType);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String date = df.format(Calendar.getInstance().getTime());
        String formattedDate = Helpers.formatDate(date, false);
        summaryHeader.setText(formattedDate);

        /*ListView summaryListView = findViewById(R.id.summary);*/
        summaryValues = new SummaryValues(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        summaryItems = new ArrayList<>();

        //Create a new Nutrient object for each slot in summaryItems list in order to make possible
        //the setting in setSummaryItems()
        for (int i = 0; i < NUTRIENTS_COUNT; i++){
            summaryItems.add(new Nutrient());
        }
        setSummaryItems();

        summaryAdapter = new SummaryAdapter(this, summaryItems);
        summaryView.setAdapter(summaryAdapter);
        summaryView.setDivider(null);
        setListViewHeight(summaryView);

        panelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This method call returns the panel status towards view expansion from the adapter
                boolean isExpanded = summaryAdapter.notifyClickToExpand();

                if (isExpanded){
                    panelMask.setVisibility(View.INVISIBLE);
                } else {
                    panelMask.setVisibility(View.VISIBLE);
                }

                setListViewHeight(summaryView);
            }
        });

        dailyMeals = new ArrayList<>();

        final MealHistoryAdapter mealHistoryAdapter = new MealHistoryAdapter(this, dailyMeals);
        mealHistoryView.setAdapter(mealHistoryAdapter);
        setListViewHeight(mealHistoryView);

        DailyMealViewModel dailyMealViewModel = ViewModelProviders.of(this).get(DailyMealViewModel.class);
        dailyMealViewModel.getAllDailyMeals().observe(this, new Observer<List<DailyMeal>>() {
            @Override
            public void onChanged(@Nullable final List<DailyMeal> liveMeals) {
                dailyMeals.clear();
                for (DailyMeal meal : liveMeals) {
                    if (meal != null && meal.getName() != null) {
                        dailyMeals.add(meal);
                    }
                }
                mealHistoryAdapter.notifyDataSetChanged();
                setListViewHeight(mealHistoryView);
            }
        });
        final LifecycleOwner lifecycleOwner = this;
        final MealViewModel mealViewModel = ViewModelProviders.of(this).get(MealViewModel.class);
        FoodViewModel foodViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        foodViewModel.getAllFood().observe(this, new Observer<List<Food>>() {
            @Override
            public void onChanged(@Nullable final List<Food> foods) {
                int [] mealIds = new int[foods.size()];
                final HashMap<Integer, Integer> mealQuantity = new HashMap<>();
                for (int i=0; i < foods.size() ; i++) {
                    Food food = foods.get(i);
                    mealIds[i] = food.getMealId();
                    if (mealQuantity.get(food.getMealId()) == null) {
                        mealQuantity.put(food.getMealId(), (int) food.getQuantityPerUnit());
                    } else {
                        int oldQuantity = mealQuantity.get(food.getMealId());
                        mealQuantity.put(food.getDailyMealId(), oldQuantity + (int) food.getQuantityPerUnit());
                    }
                }

                mealViewModel.findByIds(mealIds).observe(lifecycleOwner, new Observer<List<Meal>>() {
                    @Override
                    public void onChanged(@Nullable final List<Meal> liveMeals) {
                        Float energy = 0.0f;
                        Float carbohydrate = 0.0f;
                        Float protein = 0.0f;
                        Float totalFat = 0.0f;
                        Float saturatedFat = 0.0f;
                        Float transFat = 0.0f;
                        Float fibers = 0.0f;
                        Float sodium = 0.0f;
                        Float vitaminC = 0.0f;
                        Float calcium = 0.0f;
                        Float iron = 0.0f;
                        Float vitaminA = 0.0f;
                        Float selenium = 0.0f;
                        Float potassium = 0.0f;
                        Float magnesium = 0.0f;
                        Float vitaminE = 0.0f;
                        Float thiamine = 0.0f;


                        for (Meal meal : liveMeals) {
                            int quantity = mealQuantity.get(meal.getId());
                            energy += meal.getEnergy() * meal.getUnityMultiplier() * quantity / 100;
                            carbohydrate += meal.getCarbohydrate() * meal.getUnityMultiplier() * quantity / 100;
                            protein += meal.getProtein() * meal.getUnityMultiplier() * quantity / 100;
                            totalFat += meal.getTotalFat() * meal.getUnityMultiplier() * quantity / 100;
                            saturatedFat += meal.getSaturatedFat() * meal.getUnityMultiplier() * quantity / 100;
                            transFat += meal.getTransFat() * meal.getUnityMultiplier() * quantity / 100;
                            fibers += meal.getFibers() * meal.getUnityMultiplier() * quantity / 100;
                            sodium += meal.getSodium() * meal.getUnityMultiplier() * quantity / 100;
                            vitaminC += meal.getVitaminC() * meal.getUnityMultiplier() * quantity / 100;
                            calcium += meal.getCalcium() * meal.getUnityMultiplier() * quantity / 100;
                            iron += meal.getIron() * meal.getUnityMultiplier() * quantity / 100;
                            vitaminA += meal.getVitaminA() * meal.getUnityMultiplier() * quantity / 100;
                            selenium += meal.getSelenium() * meal.getUnityMultiplier() * quantity / 100;
                            potassium += meal.getPotassium() * meal.getUnityMultiplier() * quantity / 100;
                            magnesium += meal.getMagnesium() * meal.getUnityMultiplier() * quantity / 100;
                            vitaminE +=  meal.getVitaminE() * meal.getUnityMultiplier() * quantity / 100;
                            thiamine += meal.getThiamine() * meal.getUnityMultiplier() * quantity / 100;
                        }

                        summaryItems.get(0).setValue(Math.round(energy));
                        summaryItems.get(1).setValue(Math.round(carbohydrate));
                        summaryItems.get(2).setValue(Math.round(protein));
                        summaryItems.get(3).setValue(Math.round(totalFat));
                        summaryItems.get(4).setValue(Math.round(saturatedFat));
                        summaryItems.get(5).setValue(Math.round(transFat));
                        summaryItems.get(6).setValue(Math.round(fibers));
                        summaryItems.get(7).setValue(Math.round(sodium));
                        summaryItems.get(8).setValue(Math.round(vitaminC));
                        summaryItems.get(9).setValue(Math.round(calcium));
                        summaryItems.get(10).setValue(Math.round(iron));
                        summaryItems.get(11).setValue(Math.round(vitaminA));
                        summaryItems.get(12).setValue(Math.round(selenium));
                        summaryItems.get(13).setValue(Math.round(potassium));
                        summaryItems.get(14).setValue(Math.round(magnesium));
                        summaryItems.get(15).setValue(Math.round(vitaminE));
                        summaryItems.get(16).setValue(Math.round(thiamine));

                        summaryAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        userView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });

        newMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewMealActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        setSummaryItems();
        summaryAdapter.notifyDataSetChanged();
        super.onResume();
    }

    private void setSummaryItems(){
        summaryItems.get(0).setName("Energia");
        summaryItems.get(0).setValue((int) summaryValues.getEnergy());
        summaryItems.get(0).setSuggestedValue((int) Helpers.calculateRequiredEnergy(this));
        summaryItems.get(0).setMeasure(" kcal");

        summaryItems.get(1).setName("Carboidratos");
        summaryItems.get(1).setValue((int) summaryValues.getCarbohydrate());
        summaryItems.get(1).setSuggestedValue((int) ((Helpers.calculateRequiredEnergy(this) * 0.65)/4));
        summaryItems.get(1).setMeasure(" g");

        summaryItems.get(2).setName("Proteína");
        summaryItems.get(2).setValue((int) summaryValues.getProtein());
        summaryItems.get(2).setSuggestedValue(UserInfoContainer.getWeight(this));
        summaryItems.get(2).setMeasure(" g");

        summaryItems.get(3).setName("Gorduras Totais");
        summaryItems.get(3).setValue((int) summaryValues.getTotalFat());
        summaryItems.get(3).setSuggestedValue((int) ((Helpers.calculateRequiredEnergy(this) * 0.225)/9));
        summaryItems.get(3).setMeasure(" g");

        summaryItems.get(4).setName("Gordura Saturada");
        summaryItems.get(4).setValue((int) summaryValues.getSaturatedFat());
        summaryItems.get(4).setSuggestedValue((int) ((Helpers.calculateRequiredEnergy(this) * 0.07)/9));
        summaryItems.get(4).setMeasure(" g");

        summaryItems.get(5).setName("Gordura Trans");
        summaryItems.get(5).setValue((int) summaryValues.getTransFat());
        summaryItems.get(5).setMeasure(" g");

        summaryItems.get(6).setName("Fibras");
        summaryItems.get(6).setValue((int) summaryValues.getFibers());
        summaryItems.get(6).setSuggestedValue(25);
        summaryItems.get(6).setMeasure(" g");

        summaryItems.get(7).setName("Sódio");
        summaryItems.get(7).setValue((int) summaryValues.getSodium());
        summaryItems.get(7).setSuggestedValue(2400);
        summaryItems.get(7).setMeasure(" mg");

        summaryItems.get(8).setName("Vitamina C");
        summaryItems.get(8).setValue((int) summaryValues.getVitaminC());
        summaryItems.get(8).setSuggestedValue(45);
        summaryItems.get(8).setMeasure(" mg");

        summaryItems.get(9).setName("Cálcio");
        summaryItems.get(9).setValue((int) summaryValues.getCalcium());
        summaryItems.get(9).setSuggestedValue(600);
        summaryItems.get(9).setMeasure(" mg");

        summaryItems.get(10).setName("Ferro");
        summaryItems.get(10).setValue((int) summaryValues.getIron());
        summaryItems.get(10).setSuggestedValue(14);
        summaryItems.get(10).setMeasure(" mg");

        summaryItems.get(11).setName("Vitamina A");
        summaryItems.get(11).setValue((int) summaryValues.getVitaminA());
        summaryItems.get(11).setSuggestedValue(600);
        summaryItems.get(11).setMeasure(" µg");

        summaryItems.get(12).setName("Selênio");
        summaryItems.get(12).setValue((int) summaryValues.getSelenium());
        summaryItems.get(12).setSuggestedValue(34);
        summaryItems.get(12).setMeasure(" µg");

        summaryItems.get(13).setName("Potássio");
        summaryItems.get(13).setValue((int) summaryValues.getPotassium());
        summaryItems.get(13).setSuggestedValue(2400);
        summaryItems.get(13).setMeasure(" g");

        summaryItems.get(14).setName("Magnésio");
        summaryItems.get(14).setValue((int) summaryValues.getMagnesium());
        summaryItems.get(14).setSuggestedValue(260);
        summaryItems.get(14).setMeasure(" g");

        summaryItems.get(15).setName("Vitamina E");
        summaryItems.get(15).setValue((int) summaryValues.getVitaminE());
        summaryItems.get(15).setSuggestedValue(10);
        summaryItems.get(15).setMeasure(" mg");

        summaryItems.get(16).setName("Tiamina");
        summaryItems.get(16).setValue((int) summaryValues.getThiamine());
        summaryItems.get(16).setSuggestedValue(1.2);
        summaryItems.get(16).setMeasure(" mg");

    }

    private void setListViewHeight(ListView listView) {

        ListAdapter adapter = listView.getAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.EXACTLY);

        for (int i = 0; i < adapter.getCount(); i++) {

            View groupItem = adapter.getView(i, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));

        if (height < 10){
            height = 200;
        }

        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
