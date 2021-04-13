package app.example.henriqueribeirodealmeida.nutre;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import app.example.henriqueribeirodealmeida.nutre.Adapters.FoodDetailsAdapter;
import app.example.henriqueribeirodealmeida.nutre.Entities.Meal;
import app.example.henriqueribeirodealmeida.nutre.Entities.Nutrient;
import app.example.henriqueribeirodealmeida.nutre.Entities.SummaryValues;

import com.example.henriqueribeirodealmeida.nutre.R;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
    private double totalQuantity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        final MealViewModel mealViewModel = ViewModelProviders.of(this).get(MealViewModel.class);

        Intent intent = getIntent();
        final Bundle bundle = intent.getBundleExtra("bundle");
        final Meal meal = bundle.getParcelable("food");


        ImageView updateMeal = findViewById(R.id.updateMeal);
        TextView textupdatemeal = findViewById(R.id.textupdatemeal);
        TextView textDeleteMeal = findViewById(R.id.qua);
        final ImageView deleteMeal = findViewById(R.id.deleteMeal);
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

            double quantity = bundle.getDouble("quantity");

            totalQuantity = quantity * meal.getUnityMultiplier();
            factor = totalQuantity / 100;
            System.out.println(factor);

        }

        if(factor==1){

            quantityView.setText("Composição em  100g" );

        }else{
            quantityView.setText("Composição em "+ (int)totalQuantity + " g");
            deleteMeal.setVisibility(View.GONE);
            textDeleteMeal.setVisibility(View.GONE);
            updateMeal.setVisibility(View.GONE);
            textupdatemeal.setVisibility(View.GONE);
        }

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

        deleteMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    deleteConfirm(mealViewModel,meal);
            }
        });

        updateMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editConfirm(meal);
            }
        });



    }

    private void setSummaryItems(){
        summaryItems.get(0).setName("Energia");
        summaryItems.get(0).setValue((float) ( summaryValues.getEnergy() * factor));
        summaryItems.get(0).setMeasure(" kcal");

        summaryItems.get(1).setName("Água");
        summaryItems.get(1).setValue((float) (summaryValues.getWater() * factor));
        summaryItems.get(1).setMeasure(" ml");

        summaryItems.get(2).setName("Carboidrato");
        summaryItems.get(2).setValue((float) (summaryValues.getCarbohydrate() * factor));
        summaryItems.get(2).setMeasure(" g");

        summaryItems.get(3).setName("Proteína");
        summaryItems.get(3).setValue((float)(summaryValues.getProtein() * factor));
        summaryItems.get(3).setMeasure(" g");

        summaryItems.get(4).setName("Gorduras totais");
        summaryItems.get(4).setValue((float) (summaryValues.getTotalFat() * factor));
        summaryItems.get(4).setMeasure(" g");

        summaryItems.get(5).setName("Gordura saturada");
        summaryItems.get(5).setValue((float) (summaryValues.getSaturatedFat() * factor));
        summaryItems.get(5).setMeasure(" g");

        summaryItems.get(6).setName("Fibras");
        summaryItems.get(6).setValue((float) (summaryValues.getFibers() * factor));
        summaryItems.get(6).setMeasure(" g");

        summaryItems.get(7).setName("Sódio");
        summaryItems.get(7).setValue((float) (summaryValues.getSodium() * factor));
        summaryItems.get(7).setMeasure(" mg");

        summaryItems.get(8).setName("Vitamina C");
        summaryItems.get(8).setValue((float) (summaryValues.getVitaminC() * factor));
        summaryItems.get(8).setMeasure(" mg");

        summaryItems.get(9).setName("Cálcio");
        summaryItems.get(9).setValue((float) (summaryValues.getCalcium() * factor));
        summaryItems.get(9).setMeasure(" mg");

        summaryItems.get(10).setName("Ferro");
        summaryItems.get(10).setValue((float) (summaryValues.getIron() * factor));
        summaryItems.get(10).setMeasure(" mg");

        summaryItems.get(11).setName("Vitamina A");
        summaryItems.get(11).setValue((float) (summaryValues.getVitaminA() * factor));
        summaryItems.get(11).setMeasure(" µg");

        summaryItems.get(12).setName("Potássio");
        summaryItems.get(12).setValue((float) (summaryValues.getPotassium() * factor));
        summaryItems.get(12).setMeasure(" mg");

        summaryItems.get(13).setName("Magnésio");
        summaryItems.get(13).setValue((float) (summaryValues.getMagnesium() * factor));
        summaryItems.get(13).setMeasure(" mg");

        summaryItems.get(14).setName("Tiamina");
        summaryItems.get(14).setValue((float) (summaryValues.getThiamine() * factor));
        summaryItems.get(14).setMeasure(" mg");

        summaryItems.get(15).setName("Riboflavina");
        summaryItems.get(15).setValue((float) (summaryValues.getRiboflavin() * factor));
        summaryItems.get(15).setMeasure(" mg");

        summaryItems.get(15).setName("Niacina");
        summaryItems.get(15).setValue((float) (summaryValues.getNiacin() * factor));
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

    public void deleteConfirm(final MealViewModel view, final Meal meal){

        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);

        msgBox.setTitle("Excluindo...");
        msgBox.setIcon(R.drawable.ic_baseline_delete_36);
        msgBox.setMessage("Deseja excluir este alimento?");
        msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                view.delete(meal);
                Toast.makeText(FoodDetailsActivity.this, "Alimento removido com sucesso", Toast.LENGTH_SHORT).show();
                new TrocaDeTela(FoodDetailsActivity.this, SearchActivity.class,R.anim.mover_esquerda,R.anim.fade_in);
            }
        });
        msgBox.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        msgBox.show();

    }

    public void editConfirm( final Meal meal){

        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);

        msgBox.setTitle("Editar");
        msgBox.setIcon(R.drawable.ic_baseline_delete_36);
        msgBox.setMessage("Deseja editar este alimento?");
        msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(FoodDetailsActivity.this, UpdateFood.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("food", meal);
                bundle.putString("caller", "FoodDetailsActivity");
                intent.putExtra("bundle", bundle);

                startActivity(intent);
                 }
        });
        msgBox.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        msgBox.show();

    }




}
