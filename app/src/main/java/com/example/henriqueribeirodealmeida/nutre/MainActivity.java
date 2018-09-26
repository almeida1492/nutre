package com.example.henriqueribeirodealmeida.nutre;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.henriqueribeirodealmeida.nutre.Adapters.MealHistoryAdapter;
import com.example.henriqueribeirodealmeida.nutre.Adapters.SummaryAdapter;
import com.example.henriqueribeirodealmeida.nutre.Entities.Meal;
import com.example.henriqueribeirodealmeida.nutre.Entities.Nutrient;
import com.example.henriqueribeirodealmeida.nutre.Entities.SummaryValues;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int NUTRIENTS_COUNT = 18;

    private SummaryValues summaryValues;
    private ArrayList<Nutrient> summaryItems;
    private SummaryAdapter summaryAdapter;

    private ArrayList<Meal> meals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView titleView = findViewById(R.id.title);
        RelativeLayout panelView = findViewById(R.id.summary_panel);
        final ListView summaryView = findViewById(R.id.summary);
        final View panelMask = findViewById(R.id.panel_mask);
        final ListView mealHistoryView = findViewById(R.id.meal_history);

        //Set activity title text font
        Typeface balooChettanType = Typeface.createFromAsset(getAssets(), "fonts/BalooChettan-Regular.ttf");
        titleView.setTypeface(balooChettanType);

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
        meals.add(new Meal());
        meals.add(new Meal());
        meals.add(new Meal());
        meals.add(new Meal());
        meals.add(new Meal());
        meals.add(new Meal());
        meals.add(new Meal());
        MealHistoryAdapter mealHistoryAdapter = new MealHistoryAdapter(this, meals);
        mealHistoryView.setAdapter(mealHistoryAdapter);
        setListViewHeight(mealHistoryView);
    }

    private void setSummaryItems(){
        summaryItems.get(0).setName("Energia");
        summaryItems.get(0).setValue(summaryValues.getEnergy());
        summaryItems.get(0).setMeasure("kcal");

        summaryItems.get(1).setName("Carboidratos");
        summaryItems.get(1).setValue(summaryValues.getCarbohydrate());
        summaryItems.get(1).setMeasure("g");

        summaryItems.get(2).setName("Proteína");
        summaryItems.get(2).setValue(summaryValues.getProtein());
        summaryItems.get(2).setMeasure("g");

        summaryItems.get(3).setName("Gorduras Totais");
        summaryItems.get(3).setValue(summaryValues.getTotalFat());
        summaryItems.get(3).setMeasure("g");

        summaryItems.get(4).setName("Gordura Trans");
        summaryItems.get(4).setValue(summaryValues.getTransFat());
        summaryItems.get(4).setMeasure("g");

        summaryItems.get(5).setName("Gordura Saturada");
        summaryItems.get(5).setValue(summaryValues.getSaturatedFat());
        summaryItems.get(5).setMeasure("g");

        summaryItems.get(6).setName("Água");
        summaryItems.get(6).setValue(summaryValues.getWater());
        summaryItems.get(6).setMeasure("g");

        summaryItems.get(7).setName("Vitamina A");
        summaryItems.get(7).setValue(summaryValues.getVitaminA());
        summaryItems.get(7).setMeasure("g");

        summaryItems.get(8).setName("Vitamina C");
        summaryItems.get(8).setValue(summaryValues.getVitaminC());
        summaryItems.get(8).setMeasure("g");

        summaryItems.get(9).setName("Vitamina E");
        summaryItems.get(9).setValue(summaryValues.getVitaminE());
        summaryItems.get(9).setMeasure("g");

        summaryItems.get(10).setName("Fibras");
        summaryItems.get(10).setValue(summaryValues.getFibers());
        summaryItems.get(10).setMeasure("g");

        summaryItems.get(11).setName("Sódio");
        summaryItems.get(11).setValue(summaryValues.getSodium());
        summaryItems.get(11).setMeasure("g");

        summaryItems.get(12).setName("Ferro");
        summaryItems.get(12).setValue(summaryValues.getIron());
        summaryItems.get(12).setMeasure("g");

        summaryItems.get(13).setName("Ômega 3");
        summaryItems.get(13).setValue(summaryValues.getOmega3());
        summaryItems.get(13).setMeasure("g");

        summaryItems.get(14).setName("Ômega 6");
        summaryItems.get(14).setValue(summaryValues.getOmega6());
        summaryItems.get(14).setMeasure("g");

        summaryItems.get(15).setName("Ômega 9");
        summaryItems.get(15).setValue(summaryValues.getOmega9());
        summaryItems.get(15).setMeasure("g");

        summaryItems.get(16).setName("Cálcio");
        summaryItems.get(16).setValue(summaryValues.getCalcium());
        summaryItems.get(16).setMeasure("g");

        summaryItems.get(17).setName("Zinco");
        summaryItems.get(17).setValue(summaryValues.getZinc());
        summaryItems.get(17).setMeasure("g");
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
