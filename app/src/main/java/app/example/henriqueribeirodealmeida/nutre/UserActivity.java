package app.example.henriqueribeirodealmeida.nutre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import app.example.henriqueribeirodealmeida.nutre.Data.UserInfoContainer;
import app.example.henriqueribeirodealmeida.nutre.Entities.Nutrient;
import app.example.henriqueribeirodealmeida.nutre.Fragments.InfoIMCFragment;
import app.example.henriqueribeirodealmeida.nutre.Fragments.PhysicalActivityInfoFragment;

import com.example.henriqueribeirodealmeida.nutre.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class UserActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        final EditText nameView = findViewById(R.id.name);
        final EditText ageView = findViewById(R.id.age);
        final EditText heightView = findViewById(R.id.height);
        final EditText weightView = findViewById(R.id.weight);
        final Spinner physicalActivityView = findViewById(R.id.physical_activity_spinner);
        final Spinner genderView = findViewById(R.id.gender_spinner);
        final Spinner Energeticvalue = findViewById(R.id.EnergeticValue);
        ImageView helpView = findViewById(R.id.help);
        Button confirmView = findViewById(R.id.confirm);

        //Second linear
        final LinearLayout linearIMC = findViewById(R.id.layoutIMC);
        final TextView imc = findViewById(R.id.imc);
        final TextView valorEnergetico = findViewById(R.id.valor_energetico);
        final EditText valorEscolhido = findViewById(R.id.valorescolhido);

       // final TextView textoIMC = findViewById(R.id.textoIMC);
        ImageView infoIMC = findViewById(R.id.info_IMC);

        linearIMC.setVisibility(View.GONE);

        Typeface balooChettanType = Typeface.createFromAsset(getAssets(), "fonts/BalooChettan-Regular.ttf");
        Typeface notoSansType = Typeface.createFromAsset(getAssets(), "fonts/NotoSans-Regular.ttf");
        TextView headerView = findViewById(R.id.header);
        headerView.setTypeface(balooChettanType);
        TextView titleView = findViewById(R.id.title);
        titleView.setTypeface(notoSansType);

        ArrayList<String> physicalActivityOptions = new ArrayList<>();
        physicalActivityOptions.add("Baixa");
        physicalActivityOptions.add("Moderada");
        physicalActivityOptions.add("Intensa");
        ArrayAdapter<String> physicalActivityAdapter = new ArrayAdapter<String>(this, R.layout.spinner_default, physicalActivityOptions);
        physicalActivityAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
        physicalActivityView.setAdapter(physicalActivityAdapter);

        ArrayList<String> genderOptions = new ArrayList<>();
        genderOptions.add("Masculino");
        genderOptions.add("Feminino");
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, R.layout.spinner_default, genderOptions);
        genderAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
        genderView.setAdapter(genderAdapter);

        final ArrayList<String> EnergeticOption = new ArrayList<>();
        EnergeticOption.add("Continuar com o calculado\n pelo aplicativo");
        EnergeticOption.add("Valor padrão de 2000 Kcal");
        EnergeticOption.add("Digite seu próprio valor energético");
        ArrayAdapter<String> EnergyAdaper = new ArrayAdapter<>(this,R.layout.spinner_default,EnergeticOption);
        EnergyAdaper.setDropDownViewResource(R.layout.item_spinner_dropdown);
        Energeticvalue.setAdapter(EnergyAdaper);

        if (!UserInfoContainer.getName(this).equals("")){
            nameView.setText(UserInfoContainer.getName(this));
        }
        if (UserInfoContainer.getAge(this) != 0){
            ageView.setText(String.valueOf(UserInfoContainer.getAge(this)));
        }
        if (UserInfoContainer.getHeight(this) != 0) {
            heightView.setText(String.valueOf(UserInfoContainer.getHeight(this)));
        }
        if (UserInfoContainer.getWeight(this) != 0) {
            weightView.setText(String.valueOf(UserInfoContainer.getWeight(this)));
        }
        physicalActivityView.setSelection(UserInfoContainer.getPhysicalActivityIntensity(this));
        genderView.setSelection(UserInfoContainer.getGender(this));


        helpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhysicalActivityInfoFragment dialog = new PhysicalActivityInfoFragment();
                dialog.show(getSupportFragmentManager(), "PhysicalActivityInfoFragment");
            }
        });

        infoIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 InfoIMCFragment dialog = new InfoIMCFragment();
                dialog.show(getSupportFragmentManager(), "InfoIMCFragment");
            }
        });


        confirmView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name   = nameView.getText().toString();
                String age    = ageView.getText().toString();
                String height = heightView.getText().toString();
                String weight = weightView.getText().toString();
                UserInfoContainer.setPhysicalActivityIntensity(getApplicationContext(), physicalActivityView.getSelectedItemPosition());
                UserInfoContainer.setGender(getApplicationContext(), genderView.getSelectedItemPosition());


                if (name.isEmpty() || age.isEmpty() || height.isEmpty() || weight.isEmpty()){
                    Toast.makeText(UserActivity.this, "Preencha corretamente ", Toast.LENGTH_SHORT).show();

                    linearIMC.setVisibility(View.GONE);

                }else{

                    linearIMC.setVisibility(View.VISIBLE);
                    DecimalFormat formatador = new DecimalFormat("0.00");
                    DecimalFormat formatador1 = new DecimalFormat("0");

                    UserInfoContainer.setName(getApplicationContext(),   name);
                    UserInfoContainer.setAge(getApplicationContext(),    Integer.valueOf(age));
                    UserInfoContainer.setHeight(getApplicationContext(), Integer.valueOf(height));
                    UserInfoContainer.setWeight(getApplicationContext(), Integer.valueOf(weight));
                    UserInfoContainer.setEnergy(getApplicationContext(), (int) Helpers.calculateRequiredEnergy(UserActivity.this));

                    imc.setText("Seu IMC atual é:\n "+ formatador.format( Helpers.calculateIMC(UserActivity.this)));
                    valorEnergetico.setText("Valor energético para\n manter seu peso atual:\n"+formatador1.format(UserInfoContainer.getEnergy(getApplicationContext()))+"\tKcal");


                    findViewById(R.id.ok2).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch(Energeticvalue.getSelectedItemPosition()){
                                case(0):
                                    UserInfoContainer.setEnergy(getApplicationContext(), (int) Helpers.calculateRequiredEnergy(UserActivity.this));
                                    valorEscolhido.setVisibility(View.GONE);
                                    new TrocaDeTela(UserActivity.this, MainActivity.class, R.anim.mover_esquerda, R.anim.fade_in);
                                    break;
                                case(1):
                                    UserInfoContainer.setEnergy(getApplicationContext(),2000);
                                    valorEscolhido.setVisibility(View.GONE);
                                    new TrocaDeTela(UserActivity.this, MainActivity.class, R.anim.mover_esquerda, R.anim.fade_in);
                                    break;
                                case(2):
                                    valorEscolhido.setVisibility(View.VISIBLE);
                                    if (!valorEscolhido.getText().toString().isEmpty()) {
                                        UserInfoContainer.setEnergy(getApplicationContext(), Integer.valueOf(valorEscolhido.getText().toString()));

                                        new TrocaDeTela(UserActivity.this, MainActivity.class, R.anim.mover_esquerda, R.anim.fade_in);
                                    }else{
                                        Toast.makeText(UserActivity.this, "Preencha o campo corretamente", Toast.LENGTH_SHORT).show();
                                    }
                                    break;

                            }

                        }

                    });


                }
            }});

         SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
         SharedPreferences.Editor edt = pref.edit();
         edt.putBoolean("activity_executed", true);
         edt.commit();
    }
}

