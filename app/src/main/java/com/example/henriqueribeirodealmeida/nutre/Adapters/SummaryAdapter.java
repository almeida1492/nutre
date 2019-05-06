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

public class SummaryAdapter extends ArrayAdapter<Nutrient>{

    private boolean isExpanded;

    public SummaryAdapter(@NonNull Context context, @NonNull ArrayList<Nutrient> summaryItems) {
        super(context, 0, summaryItems);
        isExpanded = false;
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

        TextView nameView = itemView.findViewById(R.id.name);
        nameView.setText(nutrient.getName());

        TextView valueView = itemView.findViewById(R.id.value);
        String valueOutput = String.valueOf(nutrient.getValue()) + nutrient.getMeasure();
        valueView.setText(valueOutput);

        TextView suggestedValueView = itemView.findViewById(R.id.suggested_value);
        String suggestedValueOutput = String.valueOf(nutrient.getValue()) + nutrient.getMeasure();
        suggestedValueView.setText(suggestedValueOutput);

        return itemView;
    }

    @Override
    public int getCount() {
        //It controls the quantity of nutrients that are being shown in the summary
        if (!isExpanded){
            return 5;
        } else {
            return super.getCount();
        }
    }

    public boolean notifyClickToExpand(){
        isExpanded = !isExpanded;
        notifyDataSetChanged();
        return isExpanded;
    }
}
