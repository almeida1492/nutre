package app.example.henriqueribeirodealmeida.nutre;

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

import app.example.henriqueribeirodealmeida.nutre.Adapters.AddedFoodAdapter;
import app.example.henriqueribeirodealmeida.nutre.Adapters.FoodImageAdapter;
import app.example.henriqueribeirodealmeida.nutre.Entities.DailyMeal;
import app.example.henriqueribeirodealmeida.nutre.Entities.Food;
import app.example.henriqueribeirodealmeida.nutre.Entities.Meal;

import com.example.henriqueribeirodealmeida.nutre.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class NewMealActivity extends AppCompatActivity {

    private Meal mCurrentSelectedMeal;
    private ListView addedFoodList;
    private ArrayList<Food> foods = new ArrayList<>();
    private ArrayList<Food>  oldFoods = new ArrayList<>();
    private TextView emptyView;
    private AutoCompleteTextView foodPickerView;
    private TextView measureLabelView;
    private EditText measureValueView;
    private ArrayList<Meal> meals;
    private ArrayList<FoodImage> mFoodList;
    private FoodImageAdapter mAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meal);
        boolean updateFlag = false;
        String name = "";
        int id = 0,id2=0;

        Intent intent = getIntent();
        final Bundle bundle = intent.getBundleExtra("bundle");

        ImageView upButton = findViewById(R.id.up_button);
        TextView addMeal = findViewById(R.id.add_meal);
        Button addItemButton = findViewById(R.id.add_item);
        addedFoodList = findViewById(R.id.added_food_list);
        ScrollView mainScrollView = findViewById(R.id.main_scroll_view);
        final EditText mealTypeView = findViewById(R.id.meal_type_picker);
        emptyView = findViewById(R.id.empty);
        final Button pathNewMeal = findViewById(R.id.newmeal_path);

        initList();

        final Spinner spinnerFoodImages = findViewById(R.id.spinner_food_image);
        mAdapter = new FoodImageAdapter(this, mFoodList);
        spinnerFoodImages.setAdapter(mAdapter);


        if (bundle != null){
            name = bundle.getString("name");
            id = bundle.getInt("dailyMealId");
            foods = bundle.getParcelableArrayList("foods");
            oldFoods = new ArrayList<>(foods);
            spinnerFoodImages.setSelection(mAdapter.getPosition(new FoodImage(getIntent().getIntExtra("foodicon", R.drawable.breakfast))));
            mAdapter.notifyDataSetChanged();
            mealTypeView.setText(bundle.getString("name"));
            updateFlag = true;
            emptyView.setVisibility(View.GONE);
            pathNewMeal.setVisibility(View.GONE);//incomleto
        }



        spinnerFoodImages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FoodImage clickedItem = (FoodImage) adapterView.getItemAtPosition(i);

                }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final int dailyMealId = id;

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        final DailyMealViewModel dailyMealViewModel = ViewModelProviders.of(this).get(DailyMealViewModel.class);
        final FoodViewModel foodViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        final Activity activity = this;

        MealViewModel mealViewModel = ViewModelProviders.of(this).get(MealViewModel.class);

        meals = new ArrayList<>();
        foodPickerView   = findViewById(R.id.food_picker_text_view);
        measureValueView = findViewById(R.id.measure_value);
        measureLabelView = findViewById(R.id.measure_label);

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
/*
if (adapter.isEmpty() ) {
            pathNewMeal.setVisibility(View.VISIBLE);
        } else {
            pathNewMeal.setVisibility(View.GONE);
        }
*/
        final AddedFoodAdapter adapter = new AddedFoodAdapter(this, foods, meals);
        addedFoodList.setAdapter(adapter);
        addedFoodList.setFocusable(false);
        setListViewHeight();





        measureValueView.setText("");
        measureLabelView.setText("");

        foodPickerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentSelectedMeal = (Meal) parent.getItemAtPosition(position);
                measureLabelView.setText("(" + mCurrentSelectedMeal.getMeasureLabel()  + " = " + mCurrentSelectedMeal.getUnityMultiplier() + " " +   mCurrentSelectedMeal.getUnity()+ ")");
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

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!measureValueView.getText().toString().equals("") &  !mealTypeView.getText().toString().equals("")){

                    emptyView.setVisibility(View.GONE);
                    double quantity = Double.parseDouble(measureValueView.getText().toString());//.replaceAll("\\D+","")

                        if (mCurrentSelectedMeal != null) {

                                foods.add(new Food(mCurrentSelectedMeal.getName(),quantity, mCurrentSelectedMeal.getMeasureLabel(), mCurrentSelectedMeal.getId()));


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

        final boolean flag = updateFlag;

        addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (foods.size() != 0){

                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                    String date = df.format(Calendar.getInstance().getTime());

                    if (flag){
                        String mealType = mealTypeView.getText().toString();
                        FoodImage foodicon = (FoodImage) spinnerFoodImages.getSelectedItem();
                        DailyMeal dailyMeal = new DailyMeal(dailyMealId, mealType, foodicon.getFoodImage());

                        ArrayList<Food> newFoods = new ArrayList<>();
                        ArrayList<Food> foodsToKeep = new ArrayList<>();
                        ArrayList<Food> foodsToBeDeleted = new ArrayList<>();

                        for (Food food : foods) {
                            if (food.getDailyMealId() == 0) {
                                newFoods.add(food);

                                } else {

                                foodsToKeep.add(food);
                            }
                        }

                        for (Food food : oldFoods) {
                            if (!foodsToKeep.contains(food)){
                                foodsToBeDeleted.add(food);
                            }
                        }


                        dailyMealViewModel.update(dailyMeal, newFoods, foodsToBeDeleted, foodViewModel, activity);
                        Intent intent = new Intent(NewMealActivity.this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("foodicon", foodicon.getFoodImage());
                        intent.putExtra("bundle", bundle);
                        startActivity(intent);
                    } else {
                        String mealType = mealTypeView.getText().toString();
                        FoodImage foodicon = (FoodImage) spinnerFoodImages.getSelectedItem();
                        DailyMeal dailyMeal = new DailyMeal(mealType, date, foodicon.getFoodImage());
                        dailyMealViewModel.insert(dailyMeal, foods, foodViewModel, activity);
                        Intent intent = new Intent(NewMealActivity.this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("foodicon", foodicon.getFoodImage());
                        intent.putExtra("bundle", bundle);
                        startActivity(intent);
                    }

                } else {
                    Toast.makeText(getApplication(), "Adicione itens nesta refeição", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void initList(){
        mFoodList = new ArrayList<>();
        mFoodList.add(new FoodImage(R.drawable.meal_placeholder));
        mFoodList.add(new FoodImage(R.drawable.supper));
        mFoodList.add(new FoodImage(R.drawable.lunch));
        mFoodList.add(new FoodImage(R.drawable.breakfast));
    }

    public void setInputFields(Context context, Food item){
        mCurrentSelectedMeal = getFoodDetails(item.getName());
        measureLabelView.setText("(" + mCurrentSelectedMeal.getMeasureLabel() + ")");
        measureValueView.setText(String.valueOf((int) item.getQuantityPerUnit()));
        foodPickerView.setText(item.getName());
        measureValueView.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
    }

    private Meal getFoodDetails(String name){
        for (int i = 0; i < meals.size(); i++){
            if (meals.get(i).getName().equals(name)){
                return meals.get(i);
            }
        }
        return null;
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
