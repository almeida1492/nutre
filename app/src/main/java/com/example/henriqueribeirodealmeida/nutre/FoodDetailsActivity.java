package com.example.henriqueribeirodealmeida.nutre;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.henriqueribeirodealmeida.nutre.Adapters.FoodDetailsAdapter;
import com.example.henriqueribeirodealmeida.nutre.Adapters.SummaryAdapter;
import com.example.henriqueribeirodealmeida.nutre.Entities.Food;
import com.example.henriqueribeirodealmeida.nutre.Entities.Meal;
import com.example.henriqueribeirodealmeida.nutre.Entities.Nutrient;
import com.example.henriqueribeirodealmeida.nutre.Entities.SummaryValues;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FoodDetailsActivity extends AppCompatActivity{

    private static final int NUTRIENTS_COUNT = 17;

    private SummaryValues summaryValues;
    private ArrayList<Nutrient> summaryItems;
    private FoodDetailsAdapter adapter;
    private ScrollView scrollContainer;
    private double factor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        Meal meal = bundle.getParcelable("food");

        TextView nameView = findViewById(R.id.name);
        ListView nutrientsView = findViewById(R.id.nutrients);
        ImageView homeView = findViewById(R.id.home);
        scrollContainer = findViewById(R.id.scroll_container);
        TextView quantityView = findViewById(R.id.quantity);
        nameView.setText(meal.getName());

        factor = 1;

        summaryItems = new ArrayList<>();
        summaryValues = new SummaryValues(
                meal.getEnergy(),
                meal.getCarbohydrate(),
                meal.getProtein(),
                meal.getTotalFat(),
                meal.getSaturatedFat(),
                meal.getTransFat(),
                meal.getFibers(),
                meal.getSodium(),
                meal.getVitaminC(),
                meal.getCalcium(),
                meal.getIron(),
                meal.getVitaminA(),
                meal.getSelenium(),
                meal.getPotassium(),
                meal.getMagnesium(),
                meal.getVitaminE(),
                meal.getThiamine());

        for (int i = 0; i < NUTRIENTS_COUNT; i++){
            summaryItems.add(new Nutrient());
        }
        if (bundle.getString("caller").equals("AddedFoodAdapter")){
            homeView.setVisibility(View.INVISIBLE);
            double totalQuantity;
            double quantity = bundle.getDouble("quantity");

            totalQuantity = quantity * meal.getUnityMultiplier();
            factor = totalQuantity / 100;
            quantityView.setText("Composição em " + (int) totalQuantity + "g");
        }
        setSummaryItems();

        adapter = new FoodDetailsAdapter(this, summaryItems);
        nutrientsView.setAdapter(adapter);
        nutrientsView.setDivider(null);
        setListViewHeight(nutrientsView);

        homeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodDetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setSummaryItems(){
        summaryItems.get(0).setName("Energia");
        summaryItems.get(0).setValue((int) (summaryValues.getEnergy() * factor));
        summaryItems.get(0).setMeasure(" kcal");

        summaryItems.get(1).setName("Carboidratos");
        summaryItems.get(1).setValue((int) (summaryValues.getCarbohydrate() * factor));
        summaryItems.get(1).setMeasure(" g");

        summaryItems.get(2).setName("Proteína");
        summaryItems.get(2).setValue((int) (summaryValues.getProtein() * factor));
        summaryItems.get(2).setMeasure(" g");

        summaryItems.get(3).setName("Gorduras Totais");
        summaryItems.get(3).setValue((int) (summaryValues.getTotalFat() * factor));
        summaryItems.get(3).setMeasure(" g");

        summaryItems.get(4).setName("Gordura Saturada");
        summaryItems.get(4).setValue((int) (summaryValues.getSaturatedFat() * factor));
        summaryItems.get(4).setMeasure(" g");

        summaryItems.get(5).setName("Gordura Trans");
        summaryItems.get(5).setValue((int) (summaryValues.getTransFat() * factor));
        summaryItems.get(5).setMeasure(" g");

        summaryItems.get(6).setName("Fibras");
        summaryItems.get(6).setValue((int) (summaryValues.getFibers() * factor));
        summaryItems.get(6).setMeasure(" g");

        summaryItems.get(7).setName("Sódio");
        summaryItems.get(7).setValue((int) (summaryValues.getSodium() * factor));
        summaryItems.get(7).setMeasure(" mg");

        summaryItems.get(8).setName("Vitamina C");
        summaryItems.get(8).setValue((int) (summaryValues.getVitaminC() * factor));
        summaryItems.get(8).setMeasure(" mg");

        summaryItems.get(9).setName("Cálcio");
        summaryItems.get(9).setValue((int) (summaryValues.getCalcium() * factor));
        summaryItems.get(9).setMeasure(" mg");

        summaryItems.get(10).setName("Ferro");
        summaryItems.get(10).setValue((int) (summaryValues.getIron() * factor));
        summaryItems.get(10).setMeasure(" mg");

        summaryItems.get(11).setName("Vitamina A");
        summaryItems.get(11).setValue((int) (summaryValues.getVitaminA() * factor));
        summaryItems.get(11).setMeasure(" µg");

        summaryItems.get(12).setName("Selenio");
        summaryItems.get(12).setValue((int) (summaryValues.getSelenium() * factor));
        summaryItems.get(12).setMeasure(" µg");

        summaryItems.get(13).setName("Potássio");
        summaryItems.get(13).setValue((int) (summaryValues.getPotassium() * factor));
        summaryItems.get(13).setMeasure(" g");

        summaryItems.get(14).setName("Magnésio");
        summaryItems.get(14).setValue((int) (summaryValues.getMagnesium() * factor));
        summaryItems.get(14).setMeasure(" g");

        summaryItems.get(15).setName("Vitamina E");
        summaryItems.get(15).setValue((int) (summaryValues.getVitaminE() * factor));
        summaryItems.get(15).setMeasure(" mg");

        summaryItems.get(16).setName("Tiamina");
        summaryItems.get(16).setValue((int) (summaryValues.getThiamine() * factor));
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

        scrollContainer.fullScroll(View.FOCUS_UP);
    }
}
