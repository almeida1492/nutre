package com.example.henriqueribeirodealmeida.nutre;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.henriqueribeirodealmeida.nutre.Adapters.AddedFoodAdapter;
import com.example.henriqueribeirodealmeida.nutre.Entities.DailyMeal;
import com.example.henriqueribeirodealmeida.nutre.Entities.Food;
import com.example.henriqueribeirodealmeida.nutre.Entities.Meal;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewMealActivity extends AppCompatActivity {

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
        final Spinner mealTypeView = findViewById(R.id.meal_type_picker);
        final TextView emptyView = findViewById(R.id.empty);

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

        MealViewModel mealViewModel = ViewModelProviders.of(this).get(MealViewModel.class);

        final ArrayList<Meal> meals = new ArrayList<>();
        final AutoCompleteTextView foodPickerView = findViewById(R.id.food_picker_text_view);

        final ArrayAdapter<Meal> autoCompleteAdapter = new ArrayAdapter<Meal>(this, android.R.layout.simple_dropdown_item_1line, meals);
        foodPickerView.setDropDownWidth(-1);
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

        final AddedFoodAdapter adapter = new AddedFoodAdapter(this, foods);
        addedFoodList.setAdapter(adapter);
        addedFoodList.setFocusable(false);
        setListViewHeight(addedFoodList);

        final EditText measureValueView = findViewById(R.id.measure_value);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyView.setVisibility(View.GONE);
                double quantity = Double.parseDouble(measureValueView.getText().toString().replaceAll("\\D+",""));
                if (mCurrentSelectedMeal != null) {
                    foods.add(new Food(mCurrentSelectedMeal.getName(), quantity, mCurrentSelectedMeal.getMeasureLabel(), mCurrentSelectedMeal.getId()));
                } else {
                    foods.add(new Food(foodPickerView.getText().toString(), quantity, "unidade"));
                }
                adapter.notifyDataSetChanged();
                setListViewHeight(addedFoodList);
                foodPickerView.setText("");
            }
        });

        final TextView measureView = findViewById(R.id.measure_view);
        foodPickerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentSelectedMeal = (Meal) parent.getItemAtPosition(position);
                measureView.setText(mCurrentSelectedMeal.getMeasureLabel());
            }
        });

        LinearLayout quantityView = findViewById(R.id.quantity_container);
        quantityView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                measureValueView.requestFocus();
                measureValueView.selectAll();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(measureValueView, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        ArrayList<String> mealTypes = new ArrayList<>();
        mealTypes.add("Café da manhã");
        mealTypes.add("Almoço");
        mealTypes.add("Janta");
        mealTypes.add("Ceia");
        ArrayAdapter<String> mealTypeAdapter = new ArrayAdapter<>(this, R.layout.default_meal_type, mealTypes);
        mealTypeAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
        mealTypeView.setAdapter(mealTypeAdapter);

        addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                String date = df.format(Calendar.getInstance().getTime());

                String mealType = mealTypeView.getSelectedItem().toString();
                DailyMeal dailyMeal = new DailyMeal(mealType, date);
                dailyMealViewModel.insert(dailyMeal, foods, foodViewModel, activity);
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
