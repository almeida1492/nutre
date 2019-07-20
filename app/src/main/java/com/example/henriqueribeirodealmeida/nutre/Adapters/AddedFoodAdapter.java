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
import com.example.henriqueribeirodealmeida.nutre.R;

import java.util.ArrayList;

public class AddedFoodAdapter extends ArrayAdapter<Food> {

    public AddedFoodAdapter(@NonNull Context context, ArrayList<Food> foods) {
        super(context, 0, foods);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = convertView;

        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_meal, parent, false);
        }

        Food currentItem = getItem(position);

        TextView nameView = itemView.findViewById(R.id.name);
        nameView.setText(currentItem.getName());

        TextView quantityView = itemView.findViewById(R.id.item_info);
        int quantity = (int) currentItem.getQuantityPerUnit();
        String quantityOutput = quantity + " (" + currentItem.getMeasure() + ")";
        quantityView.setText(quantityOutput);

        return itemView;
    }
}
