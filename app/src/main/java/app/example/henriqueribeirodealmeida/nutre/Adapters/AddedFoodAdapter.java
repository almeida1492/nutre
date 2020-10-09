package app.example.henriqueribeirodealmeida.nutre.Adapters;

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

import app.example.henriqueribeirodealmeida.nutre.Entities.Food;
import app.example.henriqueribeirodealmeida.nutre.Entities.Meal;
import app.example.henriqueribeirodealmeida.nutre.FoodDetailsActivity;
import app.example.henriqueribeirodealmeida.nutre.NewMealActivity;
import com.example.henriqueribeirodealmeida.nutre.R;

import java.util.ArrayList;

public class AddedFoodAdapter extends ArrayAdapter<Food> {

    ArrayList<Food> foods;
    ArrayList<Meal> meals;
    Context context;

    public AddedFoodAdapter(@NonNull Context context, ArrayList<Food> foods, ArrayList<Meal> meals) {
        super(context, 0, foods);
        this.context = context;
        this.foods = foods;
        this.meals = meals;
    }

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

        final Food currentItem = getItem(position);


        TextView nameView = itemView.findViewById(R.id.name);
        nameView.setText(currentItem.getName());

        TextView quantityView = itemView.findViewById(R.id.item_info);
        final double quantity = (double) currentItem.getQuantityPerUnit();
        String quantityOutput = "Qtd.: " + quantity + " (" + currentItem.getMeasure() + ")";
        quantityView.setText(quantityOutput);

        ImageView menuView = itemView.findViewById(R.id.menu);
        if (meals != null){
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
                                            Intent intent = new Intent(context, FoodDetailsActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putParcelable("food", getFoodDetails(currentItem.getName()));
                                            bundle.putString("caller", "AddedFoodAdapter");
                                            bundle.putDouble("quantity", quantity);
                                            intent.putExtra("bundle", bundle);
                                            context.startActivity(intent);

                                            break;
                                        case R.id.edit:
                                            ((NewMealActivity)context).setInputFields(context, currentItem);
                                            foods.remove(position);
                                            notifyDataSetChanged();
                                            ((NewMealActivity)context).setListViewHeight();
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
        } else {
            menuView.setVisibility(View.INVISIBLE);
        }

        return itemView;
    }

    private Meal getFoodDetails(String name){
        for (int i = 0; i < meals.size(); i++){
            if (meals.get(i).getName().equals(name)){
                return meals.get(i);
            }
        }
        return null;
    }
}
