package com.example.henriqueribeirodealmeida.nutre;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.henriqueribeirodealmeida.nutre.Entities.Meal;
import com.example.henriqueribeirodealmeida.nutre.Entities.Nutrient;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FoodDetailsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        Meal meal = bundle.getParcelable("food");
        Log.e("maroto", meal.getName());

        TextView nameView = findViewById(R.id.name);
        ListView nutrientsView = findViewById(R.id.nutrients);

        nameView.setText(meal.getName());

    }
}
