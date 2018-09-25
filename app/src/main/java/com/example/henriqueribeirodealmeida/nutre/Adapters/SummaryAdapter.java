package com.example.henriqueribeirodealmeida.nutre.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.henriqueribeirodealmeida.nutre.Entities.Nutrient;
import com.example.henriqueribeirodealmeida.nutre.Entities.SummaryValues;
import com.example.henriqueribeirodealmeida.nutre.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SummaryAdapter extends ArrayAdapter<Nutrient>{

    private boolean isExpanded = false;

    public SummaryAdapter(@NonNull Context context, @NonNull ArrayList<Nutrient> summaryItems) {
        super(context, 0, summaryItems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = convertView;

        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.summary_panel_item, parent, false);
        }

        Nutrient nutrient = getItem(position);

        TextView nameView = itemView.findViewById(R.id.item_name);
        nameView.setText(nutrient.getName());

        TextView valueView = itemView.findViewById(R.id.item_value);
        String valueOutput = String.valueOf(nutrient.getValue());
        valueView.setText(valueOutput);

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

    public void notifyClickToExpand(){
        isExpanded = !isExpanded;
        notifyDataSetChanged();
    }
}
