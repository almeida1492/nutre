package com.example.henriqueribeirodealmeida.nutre.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.henriqueribeirodealmeida.nutre.Entities.Food;
import com.example.henriqueribeirodealmeida.nutre.Entities.Meal;
import com.example.henriqueribeirodealmeida.nutre.R;

import java.util.ArrayList;

public class SearchAdapter extends ArrayAdapter<Meal> {

    public SearchAdapter(@NonNull Context context, ArrayList<Meal> foods) {
        super(context, 0, foods);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = convertView;

        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_search, parent, false);
        }

        final Meal food = getItem(position);

        TextView nameView = itemView.findViewById(R.id.name);
        nameView.setText(food.getName());

        return itemView;
    }
}
