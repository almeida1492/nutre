package com.example.henriqueribeirodealmeida.nutre;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.henriqueribeirodealmeida.nutre.Adapters.SearchAdapter;
import com.example.henriqueribeirodealmeida.nutre.Entities.Meal;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private SearchAdapter adapter;
    private EditText searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ListView foodListView = findViewById(R.id.foods);
        searchView = findViewById(R.id.search);

        MealViewModel mealViewModel = ViewModelProviders.of(this).get(MealViewModel.class);
        final ArrayList<Meal> foods = new ArrayList<>();

        adapter = new SearchAdapter(this, foods);
        foodListView.setAdapter(adapter);

        mealViewModel.getmAllMeals().observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(@Nullable final List<Meal> liveMeals) {
                adapter.clear();
                foods.clear();
                for (Meal meal : liveMeals) {
                    if (meal != null && meal.getName() != null) {
                        foods.add(meal);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, FoodDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("food", adapter.getItem(position));
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (SearchActivity.this).adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
