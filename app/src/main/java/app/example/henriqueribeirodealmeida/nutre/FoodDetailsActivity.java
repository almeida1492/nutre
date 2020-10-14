package app.example.henriqueribeirodealmeida.nutre;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import app.example.henriqueribeirodealmeida.nutre.Adapters.FoodDetailsAdapter;
import app.example.henriqueribeirodealmeida.nutre.Entities.Meal;
import app.example.henriqueribeirodealmeida.nutre.Entities.Nutrient;
import app.example.henriqueribeirodealmeida.nutre.Entities.SummaryValues;

import com.example.henriqueribeirodealmeida.nutre.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FoodDetailsActivity extends AppCompatActivity{

    private static final int NUTRIENTS_COUNT = 16;
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
        nameView.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/BalooChettan-Regular.ttf"));

        factor = 1;

        summaryItems = new ArrayList<>();
        summaryValues = new SummaryValues(
                meal.getEnergy(),
                meal.getWater(),
                meal.getCarbohydrate(),
                meal.getProtein(),
                meal.getTotalFat(),
                meal.getSaturatedFat(),
                meal.getFibers(),
                meal.getSodium(),
                meal.getVitaminC(),
                meal.getCalcium(),
                meal.getIron(),
                meal.getVitaminA(),
                meal.getPotassium(),
                meal.getMagnesium(),
                meal.getThiamine(),
                meal.getRiboflavin(),
                meal.getNiacin());

        for (int i = 0; i < NUTRIENTS_COUNT; i++){
            summaryItems.add(new Nutrient());
        }
        if (bundle.getString("caller").equals("AddedFoodAdapter")){
            homeView.setVisibility(View.INVISIBLE);
            double totalQuantity;
            double quantity = bundle.getDouble("quantity");

            totalQuantity = quantity * meal.getUnityMultiplier();
            factor = totalQuantity / 100;

        }
        quantityView.setText("Composição em  100g" );

        setSummaryItems();

        adapter = new FoodDetailsAdapter(this, summaryItems);
        nutrientsView.setAdapter(adapter);
        nutrientsView.setDivider(null);
        setListViewHeight(nutrientsView);

        homeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new TrocaDeTela(FoodDetailsActivity.this, MainActivity.class,R.anim.mover_esquerda,R.anim.fade_in);

            }
        });
    }

    private void setSummaryItems(){
        summaryItems.get(0).setName("Energia");
        summaryItems.get(0).setValue((int) (summaryValues.getEnergy() * factor));
        summaryItems.get(0).setMeasure(" kcal");

        summaryItems.get(1).setName("Água");
        summaryItems.get(1).setValue((int) (summaryValues.getWater() * factor));
        summaryItems.get(1).setMeasure(" ml");

        summaryItems.get(2).setName("Carboidrato");
        summaryItems.get(2).setValue((int) (summaryValues.getCarbohydrate() * factor));
        summaryItems.get(2).setMeasure(" g");

        summaryItems.get(3).setName("Proteína");
        summaryItems.get(3).setValue((int) (summaryValues.getProtein() * factor));
        summaryItems.get(3).setMeasure(" g");

        summaryItems.get(4).setName("Gorduras totais");
        summaryItems.get(4).setValue((int) (summaryValues.getTotalFat() * factor));
        summaryItems.get(4).setMeasure(" g");

        summaryItems.get(5).setName("Gordura saturada");
        summaryItems.get(5).setValue((int) (summaryValues.getSaturatedFat() * factor));
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

        summaryItems.get(12).setName("Potássio");
        summaryItems.get(12).setValue((int) (summaryValues.getPotassium() * factor));
        summaryItems.get(12).setMeasure(" mg");

        summaryItems.get(13).setName("Magnésio");
        summaryItems.get(13).setValue((int) (summaryValues.getMagnesium() * factor));
        summaryItems.get(13).setMeasure(" mg");

        summaryItems.get(14).setName("Tiamina");
        summaryItems.get(14).setValue((int) (summaryValues.getThiamine() * factor));
        summaryItems.get(14).setMeasure(" mg");

        summaryItems.get(15).setName("Riboflavina");
        summaryItems.get(15).setValue((int) (summaryValues.getRiboflavin() * factor));
        summaryItems.get(15).setMeasure(" mg");

        summaryItems.get(15).setName("Niacina");
        summaryItems.get(15).setValue((int) (summaryValues.getNiacin() * factor));
        summaryItems.get(15).setMeasure(" mg");

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
