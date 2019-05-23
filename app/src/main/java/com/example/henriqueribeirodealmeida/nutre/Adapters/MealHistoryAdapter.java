package com.example.henriqueribeirodealmeida.nutre.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.henriqueribeirodealmeida.nutre.Entities.DailyMeal;
import com.example.henriqueribeirodealmeida.nutre.Helpers;
import com.example.henriqueribeirodealmeida.nutre.R;

import java.util.ArrayList;

public class MealHistoryAdapter extends ArrayAdapter<DailyMeal> {

    public MealHistoryAdapter(@NonNull Context context, ArrayList<DailyMeal> dailyMeals) {
        super(context, 0, dailyMeals);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = convertView;

        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_meal, parent, false);
        }

        DailyMeal currentItem = getItem(position);

        TextView nameView = itemView.findViewById(R.id.name);
        nameView.setText(currentItem.getName());

        TextView quantityView = itemView.findViewById(R.id.item_info);
        String formattedDate = Helpers.formatDate(currentItem.getDate(), true);
        quantityView.setText(formattedDate);

        return itemView;
        //TODO update contents inside items
    }
}
