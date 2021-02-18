package app.example.henriqueribeirodealmeida.nutre;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.henriqueribeirodealmeida.nutre.R;

import java.util.ArrayList;

import app.example.henriqueribeirodealmeida.nutre.Adapters.EditFoodAdapter;
import app.example.henriqueribeirodealmeida.nutre.Adapters.NewFoodAdapter;
import app.example.henriqueribeirodealmeida.nutre.Entities.Meal;
import app.example.henriqueribeirodealmeida.nutre.Entities.Nutrient;
import app.example.henriqueribeirodealmeida.nutre.Entities.SummaryValues;

public class UpdateFood extends AppCompatActivity {

    private ArrayList<Nutrient> summaryItems = new ArrayList<>();
    private MealViewModel mealViewModel = new MealViewModel(getApplication());
    private EditFoodAdapter summaryAdapter;
    private SummaryValues summaryValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_food);

        final Intent[] intent = {getIntent()};
        final Bundle bundle = intent[0].getBundleExtra("bundle");
        final Meal meal = bundle.getParcelable("food");


        //listview
        final GridView summaryView = findViewById(R.id.foods);

        //texts
        final EditText nome = findViewById(R.id.nome);
        final EditText quant_unidade = findViewById(R.id.quantidade_unidade);
        final Spinner unidade = findViewById(R.id.unidade);
        final Spinner medida_casual = findViewById(R.id.medida_casual);
        TextView add = findViewById(R.id.Add);
        TextView passo_um = findViewById(R.id.textView2);
        TextView passo_dois = findViewById(R.id.textView3);
        TextView passo_ter = findViewById(R.id.textView5);
        final EditText multiplicador = findViewById(R.id.Multiplicador);


        ArrayList<String> physicalActivityOptions = new ArrayList<>();
        physicalActivityOptions.add("gramas");
        physicalActivityOptions.add("mL");
        ArrayAdapter<String> physicalActivityAdapter = new ArrayAdapter<String>(this, R.layout.spinner_default_2, physicalActivityOptions);
        physicalActivityAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
        unidade.setAdapter(physicalActivityAdapter);

        //System.out.println(meal.getUnity());

        ArrayList<String> physicalActivityOptions1 = new ArrayList<>();
        physicalActivityOptions1.add(meal.getMeasureLabel());
        physicalActivityOptions1.add("Copo");
        physicalActivityOptions1.add("Xícara");
        physicalActivityOptions1.add("Colher de sopa");
        physicalActivityOptions1.add("Pitada");
        physicalActivityOptions1.add("Unidade");
        ArrayAdapter<String> physicalActivityAdapter1 = new ArrayAdapter<String>(this, R.layout.spinner_default_2, physicalActivityOptions1);
        physicalActivityAdapter1.setDropDownViewResource(R.layout.item_spinner_dropdown);
        medida_casual.setAdapter(physicalActivityAdapter1);

        //Set fonts
        add.setTypeface( Typeface.createFromAsset(getAssets(), "fonts/BalooChettan-Regular.ttf"));
        passo_dois.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/BalooChettan-Regular.ttf"));
        passo_um.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/BalooChettan-Regular.ttf"));
        passo_ter.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/BalooChettan-Regular.ttf"));


        for (int i = 0; i < 17; i++) {

            summaryItems.add(new Nutrient());


        }

        summaryAdapter = new EditFoodAdapter(this,summaryItems);
        summaryView.setAdapter(summaryAdapter);
        summaryView.setHorizontalSpacing(3);
        summaryView.setVerticalSpacing(21);

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

        nome.setText(meal.getName());
        quant_unidade.setText(String.valueOf(meal.getUnityMultiplier()));
        multiplicador.setText("1");

        setSummaryItems();


        findViewById(R.id.ok_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Unidade  = String.valueOf(unidade.getSelectedItem());
                String Nome     = nome.getText().toString();
                String MedidaCasual    = String.valueOf(medida_casual.getSelectedItem());
                String UnityMultiplier = quant_unidade.getText().toString();
                String Multiplicador   =  multiplicador.getText().toString();


                if (!Nome.isEmpty() && !MedidaCasual.isEmpty() && !Unidade.isEmpty() && !Multiplicador.isEmpty() &&  Integer.valueOf(Multiplicador) != 0 && Unidade!= "-" && MedidaCasual != "-" ) {

                       float lastValue = Float.valueOf(UnityMultiplier) /  Float.valueOf(Multiplicador);

                   Meal mealupdate = new Meal(
                                Nome,
                                MedidaCasual,
                                lastValue,
                                Unidade,
                                (summaryItems.get(0).getValue()),
                                (summaryItems.get(1).getValue()),
                                (summaryItems.get(2).getValue()),
                                (summaryItems.get(3).getValue()),
                                (summaryItems.get(4).getValue()),
                                (summaryItems.get(5).getValue()),
                                (summaryItems.get(6).getValue()),
                                (summaryItems.get(7).getValue()),
                                (summaryItems.get(8).getValue()),
                                (summaryItems.get(9).getValue()),
                                (summaryItems.get(10).getValue()),
                                (summaryItems.get(11).getValue()),
                                (summaryItems.get(12).getValue()),
                                (summaryItems.get(13).getValue()),
                                (summaryItems.get(14).getValue()),
                                (summaryItems.get(15).getValue()),
                                (summaryItems.get(16).getValue()));


                   System.out.println(mealupdate.getName());

                    mealViewModel.insert(mealupdate);
                    mealViewModel.delete(meal);

                     Toast.makeText(UpdateFood.this, "Alimento Atualizado", Toast.LENGTH_SHORT).show();

                    new TrocaDeTela(UpdateFood.this,SearchActivity.class,R.anim.mover_esquerda,R.anim.fade_in);

                   }else{
                   Toast.makeText(UpdateFood.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                  }

            }
        });

    }


    private void setSummaryItems(){
        summaryItems.get(0).setName("Energia");
        summaryItems.get(0).setValue((int) (summaryValues.getEnergy() ));
        summaryItems.get(0).setMeasure(" kcal");

        summaryItems.get(1).setName("Água");
        summaryItems.get(1).setValue((int) (summaryValues.getWater() ));
        summaryItems.get(1).setMeasure(" ml");

        summaryItems.get(2).setName("Carboidrato");
        summaryItems.get(2).setValue((int) (summaryValues.getCarbohydrate() ));
        summaryItems.get(2).setMeasure(" g");

        summaryItems.get(3).setName("Proteína");
        summaryItems.get(3).setValue((int) (summaryValues.getProtein() ));
        summaryItems.get(3).setMeasure(" g");

        summaryItems.get(4).setName("Gorduras totais");
        summaryItems.get(4).setValue((int) (summaryValues.getTotalFat() ));
        summaryItems.get(4).setMeasure(" g");

        summaryItems.get(5).setName("Gordura saturada");
        summaryItems.get(5).setValue((int) (summaryValues.getSaturatedFat() ));
        summaryItems.get(5).setMeasure(" g");

        summaryItems.get(6).setName("Fibras");
        summaryItems.get(6).setValue((int) (summaryValues.getFibers() ));
        summaryItems.get(6).setMeasure(" g");

        summaryItems.get(7).setName("Sódio");
        summaryItems.get(7).setValue((int) (summaryValues.getSodium() ));
        summaryItems.get(7).setMeasure(" mg");

        summaryItems.get(8).setName("Vitamina C");
        summaryItems.get(8).setValue((int) (summaryValues.getVitaminC() ));
        summaryItems.get(8).setMeasure(" mg");

        summaryItems.get(9).setName("Cálcio");
        summaryItems.get(9).setValue((int) (summaryValues.getCalcium() ));
        summaryItems.get(9).setMeasure(" mg");

        summaryItems.get(10).setName("Ferro");
        summaryItems.get(10).setValue((int) (summaryValues.getIron() ));
        summaryItems.get(10).setMeasure(" mg");

        summaryItems.get(11).setName("Vitamina A");
        summaryItems.get(11).setValue((int) (summaryValues.getVitaminA() ));
        summaryItems.get(11).setMeasure(" µg");

        summaryItems.get(12).setName("Potássio");
        summaryItems.get(12).setValue((int) (summaryValues.getPotassium() ));
        summaryItems.get(12).setMeasure(" mg");

        summaryItems.get(13).setName("Magnésio");
        summaryItems.get(13).setValue((int) (summaryValues.getMagnesium() ));
        summaryItems.get(13).setMeasure(" mg");

        summaryItems.get(14).setName("Tiamina");
        summaryItems.get(14).setValue((int) (summaryValues.getThiamine() ));
        summaryItems.get(14).setMeasure(" mg");

        summaryItems.get(15).setName("Riboflavina");
        summaryItems.get(15).setValue((int) (summaryValues.getRiboflavin() ));
        summaryItems.get(15).setMeasure(" mg");

        summaryItems.get(16).setName("Niacina");
        summaryItems.get(16).setValue((int) (summaryValues.getNiacin() ));
        summaryItems.get(16).setMeasure(" mg");

    }
}
