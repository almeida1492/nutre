package com.example.henriqueribeirodealmeida.nutre;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.henriqueribeirodealmeida.nutre.Data.UserInfoContainer;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Typeface balooChettanType = Typeface.createFromAsset(getAssets(), "fonts/BalooChettan-Regular.ttf");
        Typeface notoSansType = Typeface.createFromAsset(getAssets(), "fonts/NotoSans-Regular.ttf");
        TextView headerView = findViewById(R.id.header);
        headerView.setTypeface(balooChettanType);
        TextView titleView = findViewById(R.id.title);
        titleView.setTypeface(notoSansType);

        Spinner physicalActivityView = findViewById(R.id.physical_activity_spinner);
        ArrayList<String> physicalActivityOptions = new ArrayList<>();
        physicalActivityOptions.add("Baixa");
        physicalActivityOptions.add("Moderada");
        physicalActivityOptions.add("Intensa");
        ArrayAdapter<String> physicalActivityAdapter = new ArrayAdapter<String>(this, R.layout.spinner_default, physicalActivityOptions);
        physicalActivityAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
        physicalActivityView.setAdapter(physicalActivityAdapter);

    }
}
