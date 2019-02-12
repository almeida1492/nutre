package com.example.henriqueribeirodealmeida.nutre;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Typeface;
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
import com.example.henriqueribeirodealmeida.nutre.Entities.Meal;
import com.example.henriqueribeirodealmeida.nutre.Entities.Nutrient;
import com.example.henriqueribeirodealmeida.nutre.Entities.SummaryValues;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int NUTRIENTS_COUNT = 17;

    private SummaryValues summaryValues;
    private ArrayList<Nutrient> summaryItems;
    private SummaryAdapter summaryAdapter;

    private ArrayList<Meal> meals;

<<<<<<< HEAD
=======
    private MealViewModel mMealViewModel;

>>>>>>> de8181cf61e848cf1477daea46c8741613d2111e

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMealViewModel = ViewModelProviders.of(this).get(MealViewModel.class);
        mMealViewModel.getmAllMeals().observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(@Nullable final List<Meal> meals) {
                for (Meal meal : meals) {
                    Log.d("DEBUG_DB", "Meal name: " + meal.getName());                }
            }
        });

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

        meals = new ArrayList<>();
        String[] str = new String[18];

        MealHistoryAdapter mealHistoryAdapter = new MealHistoryAdapter(this, meals);
        mealHistoryView.setAdapter(mealHistoryAdapter);
        setListViewHeight(mealHistoryView);

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Ainda não implementado.", Toast.LENGTH_SHORT).show();
            }
        });

        userView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Ainda não implementado.", Toast.LENGTH_SHORT).show();
            }
        });

        newMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, newMealActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setSummaryItems(){
        summaryItems.get(0).setName("Energia");
        summaryItems.get(0).setValue((int) summaryValues.getEnergy());
        summaryItems.get(0).setMeasure("kcal");

        summaryItems.get(1).setName("Carboidratos");
        summaryItems.get(1).setValue((int) summaryValues.getCarbohydrate());
        summaryItems.get(1).setMeasure("g");

        summaryItems.get(2).setName("Proteína");
        summaryItems.get(2).setValue((int) summaryValues.getProtein());
        summaryItems.get(2).setMeasure("g");

        summaryItems.get(3).setName("Gorduras Totais");
        summaryItems.get(3).setValue((int) summaryValues.getTotalFat());
        summaryItems.get(3).setMeasure("g");

        summaryItems.get(4).setName("Gordura Saturada");
        summaryItems.get(4).setValue((int) summaryValues.getSaturatedFat());
        summaryItems.get(4).setMeasure("g");

        summaryItems.get(5).setName("Gordura Trans");
        summaryItems.get(5).setValue((int) summaryValues.getTransFat());
        summaryItems.get(5).setMeasure("g");

        summaryItems.get(6).setName("Fibras");
        summaryItems.get(6).setValue((int) summaryValues.getFibers());
        summaryItems.get(6).setMeasure("g");

        summaryItems.get(7).setName("Sódio");
        summaryItems.get(7).setValue((int) summaryValues.getSodium());
        summaryItems.get(7).setMeasure("g");

        summaryItems.get(8).setName("Vitamina C");
        summaryItems.get(8).setValue((int) summaryValues.getVitaminC());
        summaryItems.get(8).setMeasure("g");

        summaryItems.get(9).setName("Cálcio");
        summaryItems.get(9).setValue((int) summaryValues.getCalcium());
        summaryItems.get(9).setMeasure("g");

        summaryItems.get(10).setName("Ferro");
        summaryItems.get(10).setValue((int) summaryValues.getIron());
        summaryItems.get(10).setMeasure("g");

        summaryItems.get(11).setName("Vitamina A");
        summaryItems.get(11).setValue((int) summaryValues.getVitaminA());
        summaryItems.get(11).setMeasure("g");

        summaryItems.get(12).setName("Celenio");
        summaryItems.get(12).setValue((int) summaryValues.getSelenium());
        summaryItems.get(12).setMeasure("g");

        summaryItems.get(13).setName("Potássio");
        summaryItems.get(13).setValue((int) summaryValues.getPotassium());
        summaryItems.get(13).setMeasure("g");

        summaryItems.get(14).setName("Magnésio");
        summaryItems.get(14).setValue((int) summaryValues.getMagnesium());
        summaryItems.get(14).setMeasure("g");

        summaryItems.get(15).setName("Vitamina E");
        summaryItems.get(15).setValue((int) summaryValues.getVitaminE());
        summaryItems.get(15).setMeasure("g");

        summaryItems.get(16).setName("Tiamina");
        summaryItems.get(16).setValue((int) summaryValues.getThiamine());
        summaryItems.get(16).setMeasure("g");

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
