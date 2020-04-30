package com.example.henriqueribeirodealmeida.nutre;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Toast;

import com.example.henriqueribeirodealmeida.nutre.Adapters.AddedFoodAdapter;
import com.example.henriqueribeirodealmeida.nutre.Entities.DailyMeal;
import com.example.henriqueribeirodealmeida.nutre.Entities.Food;
import com.example.henriqueribeirodealmeida.nutre.Entities.Meal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NewMealActivity extends AppCompatActivity {

    private Meal mCurrentSelectedMeal;
    private ListView addedFoodList;
    private ArrayList<Food> foods;
    private TextView emptyView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meal);

        ImageView upButton = findViewById(R.id.up_button);
        ImageView addMeal = findViewById(R.id.add_meal);
        Button addItemButton = findViewById(R.id.add_item);
        addedFoodList = findViewById(R.id.added_food_list);
        ScrollView mainScrollView = findViewById(R.id.main_scroll_view);
        final Spinner mealTypeView = findViewById(R.id.meal_type_picker);
        final ImageView iconView = findViewById(R.id.meal_icon);
        emptyView = findViewById(R.id.empty);

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final DailyMealViewModel dailyMealViewModel = ViewModelProviders.of(this).get(DailyMealViewModel.class);
        final FoodViewModel foodViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        foods = new ArrayList<>();
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

        final AddedFoodAdapter adapter = new AddedFoodAdapter(this, foods, meals);
        addedFoodList.setAdapter(adapter);
        addedFoodList.setFocusable(false);
        setListViewHeight();

        final EditText measureValueView = findViewById(R.id.measure_value);
        final TextView measureLabelView = findViewById(R.id.measure_label);

        measureValueView.setText("");
        measureLabelView.setText("");

        foodPickerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentSelectedMeal = (Meal) parent.getItemAtPosition(position);
                measureLabelView.setText("(" + mCurrentSelectedMeal.getMeasureLabel() + ")");
                measureValueView.setText("1");

                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(foodPickerView.getWindowToken(), 0);
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

        mealTypeView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (mealTypeView.getSelectedItem().toString()){
                    case "Café da manhã":
                        iconView.setImageResource(R.drawable.breakfast);
                        break;
                    case "Almoço":
                        //almoco e janta tem a mesma imagem
                        iconView.setImageResource(R.drawable.lunch);
                        break;
                    case "Janta":
                        iconView.setImageResource(R.drawable.lunch);
                        break;
                    case "Ceia":
                        iconView.setImageResource(R.drawable.supper);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!measureValueView.getText().toString().equals("")){
                    emptyView.setVisibility(View.GONE);
                    double quantity = Double.parseDouble(measureValueView.getText().toString().replaceAll("\\D+",""));
                    if (mCurrentSelectedMeal != null) {
                        foods.add(new Food(mCurrentSelectedMeal.getName(), quantity, mCurrentSelectedMeal.getMeasureLabel(), mCurrentSelectedMeal.getId()));
                    } else {
                        foods.add(new Food(foodPickerView.getText().toString(), quantity, "unidade"));
                    }
                    adapter.notifyDataSetChanged();
                    setListViewHeight();
                    foodPickerView.setText("");
                    foodPickerView.requestFocus();
                    measureValueView.setText("");
                    measureLabelView.setText("");
                } else {
                    Toast.makeText(getApplication(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (foods.size() != 0){
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                    String date = df.format(Calendar.getInstance().getTime());

                    String mealType = mealTypeView.getSelectedItem().toString();
                    DailyMeal dailyMeal = new DailyMeal(mealType, date);
                    dailyMealViewModel.insert(dailyMeal, foods, foodViewModel, activity);
                    Intent intent = new Intent(NewMealActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Adicione itens nesta refeição", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setListViewHeight() {

        ListAdapter adapter = addedFoodList.getAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(addedFoodList.getWidth(), View.MeasureSpec.EXACTLY);

        for (int i = 0; i < adapter.getCount(); i++) {

            View groupItem = adapter.getView(i, null, addedFoodList);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = addedFoodList.getLayoutParams();
        int height = totalHeight + (addedFoodList.getDividerHeight() * (adapter.getCount() - 1));

        if (height < 10){
            height = 200;
        }

        params.height = height;
        addedFoodList.setLayoutParams(params);
        addedFoodList.requestLayout();

        if (foods.isEmpty()){
            emptyView.setVisibility(View.VISIBLE);
        }
    }
}
