package com.example.henriqueribeirodealmeida.nutre.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.example.henriqueribeirodealmeida.nutre.Adapters.AddedFoodAdapter;
import com.example.henriqueribeirodealmeida.nutre.Entities.Food;
import com.example.henriqueribeirodealmeida.nutre.Entities.Meal;
import com.example.henriqueribeirodealmeida.nutre.R;

import java.util.ArrayList;

public class MealDetailsFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_meal_details, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        Bundle bundle = getArguments();

        ArrayList<Food> foods = new ArrayList<>();
        foods.add(new Food("arroz", 1, "Colher de sopa"));
        foods.add(new Food("arroz", 1, "Colher de sopa"));
        foods.add(new Food("arroz", 1, "Colher de sopa"));
        foods.add(new Food("arroz", 1, "Colher de sopa"));
        foods.add(new Food("arroz", 1, "Colher de sopa"));
        foods.add(new Food("arroz", 1, "Colher de sopa"));
        foods.add(new Food("arroz", 1, "Colher de sopa"));
        foods.add(new Food("arroz", 1, "Colher de sopa"));
        foods.add(new Food("arroz", 1, "Colher de sopa"));
        ArrayList<Meal> meals = new ArrayList<>();

        AddedFoodAdapter adapter = new AddedFoodAdapter(getContext(), foods);

        ListView foodsView = rootView.findViewById(R.id.added_food_list);
        foodsView.setAdapter(adapter);

        return rootView;
    }
}
