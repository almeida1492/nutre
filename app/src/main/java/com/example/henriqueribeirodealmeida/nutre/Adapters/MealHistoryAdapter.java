package com.example.henriqueribeirodealmeida.nutre.Adapters;

import android.content.Context;
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
import com.example.henriqueribeirodealmeida.nutre.Helpers;
import com.example.henriqueribeirodealmeida.nutre.MainActivity;
import com.example.henriqueribeirodealmeida.nutre.R;

import java.util.ArrayList;

public class MealHistoryAdapter extends ArrayAdapter<DailyMeal> {

    Context context;

    public MealHistoryAdapter(@NonNull Context context, ArrayList<DailyMeal> dailyMeals) {
        super(context, 0, dailyMeals);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = convertView;

        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_meal, parent, false);
        }

        final DailyMeal currentItem = getItem(position);

        ImageView iconView = itemView.findViewById(R.id.meal_icon);
        switch (currentItem.getName()){
            case "Café da manhã":
                iconView.setImageResource(R.drawable.breakfast);
                break;
            case "Almoço":
                iconView.setImageResource(R.drawable.lunch);
                break;
            case "Janta":
                iconView.setImageResource(R.drawable.lunch);
                break;
            case "Ceia":
                iconView.setImageResource(R.drawable.supper);
                break;
        }

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
                                        ((MainActivity)context).showMealDetails(currentItem);
                                        break;
                                    case R.id.delete:
                                        ((MainActivity)context).deleteMeal(currentItem);
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
