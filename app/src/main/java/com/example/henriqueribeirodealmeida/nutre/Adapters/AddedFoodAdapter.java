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
import android.widget.Toast;

import com.example.henriqueribeirodealmeida.nutre.Entities.Food;
import com.example.henriqueribeirodealmeida.nutre.NewMealActivity;
import com.example.henriqueribeirodealmeida.nutre.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddedFoodAdapter extends ArrayAdapter<Food> {

    ArrayList<Food> foods;
    Context context;

    public AddedFoodAdapter(@NonNull Context context, ArrayList<Food> foods) {
        super(context, 0, foods);
        this.context = context;
        this.foods = foods;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

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
        String quantityOutput = "Qtd.: " + quantity + " (" + currentItem.getMeasure() + ")";
        quantityView.setText(quantityOutput);

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
                                        //Or Some other code you want to put here.. This is just an example.
                                        Toast.makeText(getContext(), "detalhes", Toast.LENGTH_LONG).show();
                                        break;
                                    case R.id.delete:
                                        foods.remove(position);
                                        notifyDataSetChanged();
                                        ((NewMealActivity)context).setListViewHeight();
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
