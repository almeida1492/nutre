package com.example.henriqueribeirodealmeida.nutre;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.henriqueribeirodealmeida.nutre.Adapters.AddedFoodAdapter;
import com.example.henriqueribeirodealmeida.nutre.Entities.DailyMeal;
import com.example.henriqueribeirodealmeida.nutre.Entities.Food;
import com.example.henriqueribeirodealmeida.nutre.Entities.Meal;

import java.util.ArrayList;
import java.util.List;

public class newMealActivity extends AppCompatActivity {

    Meal mCurrentSelectedMeal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meal);

        ImageView upButton = findViewById(R.id.up_button);
        ImageView addMeal = findViewById(R.id.add_meal);
        Button addItemButton = findViewById(R.id.add_item);
        final ListView addedFoodList = findViewById(R.id.added_food_list);
        ScrollView mainScrollView = findViewById(R.id.main_scroll_view);

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        final DailyMealViewModel dailyMealViewModel = ViewModelProviders.of(this).get(DailyMealViewModel.class);
        final FoodViewModel foodViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        final ArrayList<Food> foods = new ArrayList<>();
        final Activity activity = this;

        addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DailyMeal dailyMeal = new DailyMeal("Almoço");
                dailyMealViewModel.insert(dailyMeal, foods, foodViewModel, activity);
            }
        });

        MealViewModel mealViewModel = ViewModelProviders.of(this).get(MealViewModel.class);

        final ArrayList<Meal> meals = new ArrayList<>();
        final AutoCompleteTextView foodPickerView = findViewById(R.id.food_picker_text_view);

        final ArrayAdapter<Meal> autoCompleteAdapter = new ArrayAdapter<Meal>(this, android.R.layout.simple_dropdown_item_1line, meals);
        foodPickerView.setAdapter(autoCompleteAdapter);

        mealViewModel.getmAllMeals().observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(@Nullable final List<Meal> liveMeals) {
                autoCompleteAdapter.clear();
                meals.clear();
                for (Meal meal : liveMeals) {
                    if (meal != null && meal.getName() != null) {
                        meals.add(meal);
                        autoCompleteAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        foodPickerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentSelectedMeal = (Meal) parent.getItemAtPosition(position);
            }
        });

        final AddedFoodAdapter adapter = new AddedFoodAdapter(this, foods);
        addedFoodList.setAdapter(adapter);
        addedFoodList.setFocusable(false);
        setListViewHeight(addedFoodList);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentSelectedMeal != null) {
                    foods.add(new Food(mCurrentSelectedMeal.getName(), 1, "copo", mCurrentSelectedMeal.getId()));
                } else {
                    foods.add(new Food(foodPickerView.getText().toString(), 2, "colher"));
                }
                adapter.notifyDataSetChanged();
                setListViewHeight(addedFoodList);
                foodPickerView.clearListSelection();
            }
        });

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
