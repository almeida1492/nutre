package com.example.henriqueribeirodealmeida.nutre.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.henriqueribeirodealmeida.nutre.Entities.Nutrient;
import com.example.henriqueribeirodealmeida.nutre.R;

import java.util.ArrayList;

import static com.example.henriqueribeirodealmeida.nutre.R.id;

public class FoodDetailsAdapter extends ArrayAdapter<Nutrient> {

    public FoodDetailsAdapter(@NonNull Context context, @NonNull ArrayList<Nutrient> summaryItems) {
        super(context, 0, summaryItems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = convertView;

        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_summary_panel, parent, false);
        }


        Nutrient nutrient = getItem(position);

        TextView nameView = itemView.findViewById(id.name);
        nameView.setText(nutrient.getName());


        TextView valueView = itemView.findViewById(id.value);


        int value = (int) nutrient.getValue();


        String valueOutput = value + nutrient.getMeasure();
        valueView.setText(valueOutput);

        TextView suggestedValueView = itemView.findViewById(id.suggested_value);
        suggestedValueView.setVisibility(View.GONE);

        return itemView;
    }

}
