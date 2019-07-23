package com.example.henriqueribeirodealmeida.nutre;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.henriqueribeirodealmeida.nutre.Data.UserInfoContainer;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        final EditText nameView = findViewById(R.id.name);
        final EditText ageView = findViewById(R.id.age);
        final EditText heightView = findViewById(R.id.height);
        final EditText weightView = findViewById(R.id.weight);
        final Spinner physicalActivityView = findViewById(R.id.physical_activity_spinner);
        final Spinner genderView = findViewById(R.id.gender_spinner);

        Button confirmView = findViewById(R.id.confirm);

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

        confirmView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfoContainer.setName(getApplicationContext(), nameView.getText().toString());
                if (!ageView.getText().toString().equals("")){
                    UserInfoContainer.setAge(getApplicationContext(), Integer.valueOf(ageView.getText().toString()));
                }
                if (!heightView.getText().toString().equals("")){
                    UserInfoContainer.setHeight(getApplicationContext(), Integer.valueOf(heightView.getText().toString()));
                }
                if (!weightView.getText().toString().equals("")){
                    UserInfoContainer.setWeight(getApplicationContext(), Integer.valueOf(weightView.getText().toString()));
                }
                UserInfoContainer.setPhysicalActivityIntensity(getApplicationContext(), physicalActivityView.getSelectedItemPosition());
                UserInfoContainer.setGender(getApplicationContext(), genderView.getSelectedItemPosition());

                Intent intent = new Intent(UserActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
