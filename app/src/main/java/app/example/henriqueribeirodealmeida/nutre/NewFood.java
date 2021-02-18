package app.example.henriqueribeirodealmeida.nutre;
import android.arch.persistence.room.Query;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import app.example.henriqueribeirodealmeida.nutre.Adapters.NewFoodAdapter;
import app.example.henriqueribeirodealmeida.nutre.Entities.Meal;
import app.example.henriqueribeirodealmeida.nutre.Entities.Nutrient;

import com.example.henriqueribeirodealmeida.nutre.R;

import java.util.ArrayList;

public class NewFood extends AppCompatActivity {

    private ArrayList<Nutrient> summaryItems = new ArrayList<>();
    private MealViewModel mealViewModel = new MealViewModel(getApplication());
    private NewFoodAdapter summaryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_food);

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
        physicalActivityOptions.add("-");
        physicalActivityOptions.add("mL");
        physicalActivityOptions.add("gramas");
        ArrayAdapter<String> physicalActivityAdapter = new ArrayAdapter<String>(this, R.layout.spinner_default_2, physicalActivityOptions);
        physicalActivityAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
        unidade.setAdapter(physicalActivityAdapter);


        ArrayList<String> physicalActivityOptions1 = new ArrayList<>();
        physicalActivityOptions1.add("-");
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

       setvalores();


        summaryAdapter = new NewFoodAdapter(this,summaryItems);
        summaryView.setAdapter(summaryAdapter);
        summaryView.setHorizontalSpacing(3);
        summaryView.setVerticalSpacing(21);

        findViewById(R.id.ok_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Unidade  = String.valueOf(unidade.getSelectedItem());
                String Nome     = nome.getText().toString();
                String MedidaCasual    = String.valueOf(medida_casual.getSelectedItem());
                String UnityMultiplier = quant_unidade.getText().toString();
                String Multiplicador   =  multiplicador.getText().toString();



                   if (!Nome.isEmpty() && !MedidaCasual.isEmpty() && !Unidade.isEmpty() && !Multiplicador.isEmpty() &&  Integer.valueOf(Multiplicador) != 0 && Unidade!= "-" && MedidaCasual != "-" ) {

                       int lastValue = Integer.valueOf(UnityMultiplier) /  Integer.valueOf(Multiplicador);


                        mealViewModel.insert(new Meal(
                                Nome,
                                MedidaCasual,
                                lastValue,
                                Unidade,
                                (summaryItems.get(0).getAddValue()),
                                (summaryItems.get(1).getAddValue()),
                                (summaryItems.get(2).getAddValue()),
                                (summaryItems.get(3).getAddValue()),
                                (summaryItems.get(4).getAddValue()),
                                (summaryItems.get(5).getAddValue()),
                                (summaryItems.get(6).getAddValue()),
                                (summaryItems.get(7).getAddValue()),
                                (summaryItems.get(8).getAddValue()),
                                (summaryItems.get(9).getAddValue()),
                                (summaryItems.get(10).getAddValue()),
                                (summaryItems.get(11).getAddValue()),
                                (summaryItems.get(12).getAddValue()),
                                (summaryItems.get(13).getAddValue()),
                                (summaryItems.get(14).getAddValue()),
                                (summaryItems.get(15).getAddValue()),
                                (summaryItems.get(16).getAddValue())));


                     Toast.makeText(NewFood.this, "Alimento Adicionado", Toast.LENGTH_SHORT).show();

                     new TrocaDeTela(NewFood.this,MainActivity.class,R.anim.mover_esquerda,R.anim.fade_in);

                   }else{
                  Toast.makeText(NewFood.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                  }

            }
        });

    }

    public void setvalores(){

        for (int i = 0; i < 17; i++) {

            summaryItems.add(new Nutrient());
            summaryItems.get(i).Values(i);
            summaryItems.get(i).Measure(i);

        }

    }
}
