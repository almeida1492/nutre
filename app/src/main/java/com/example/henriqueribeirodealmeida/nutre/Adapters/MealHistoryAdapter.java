package com.example.henriqueribeirodealmeida.nutre.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.henriqueribeirodealmeida.nutre.Entities.DailyMeal;
import com.example.henriqueribeirodealmeida.nutre.FoodDetailsActivity;
import com.example.henriqueribeirodealmeida.nutre.Helpers;
import com.example.henriqueribeirodealmeida.nutre.NewMealActivity;
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

        ImageView menuView = itemView.findViewById(R.id.menu);
        menuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.menu:
                        PopupMenu popup = new PopupMenu(getContext(), v);
                        popup.getMenuInflater().inflate(R.menu.clipboard_popup, popup.getMenu());
                        popup.show();
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.details:
                                        //TODO (Henrique) open dialog to show meal details
                                        break;
                                    case R.id.delete:
                                        //TODO (Dióginis) remove meal from table
                                        break;
                                    default:
                                        break;
                                }
                                return true;
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        });

        return itemView;
    }
}
