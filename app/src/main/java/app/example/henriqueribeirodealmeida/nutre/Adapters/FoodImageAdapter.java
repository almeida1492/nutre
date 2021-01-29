package app.example.henriqueribeirodealmeida.nutre.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import app.example.henriqueribeirodealmeida.nutre.FoodImage;
import com.example.henriqueribeirodealmeida.nutre.R;

import java.util.ArrayList;

public class FoodImageAdapter extends ArrayAdapter<FoodImage> {
    public FoodImageAdapter(Context context, ArrayList<FoodImage> foodList){
        super(context, 0, foodList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.food_image_spinner_row, parent, false
            );
        }

        ImageView imageViewFood = convertView.findViewById(R.id.image_view_food);
        FoodImage currentItem = getItem(position);

        if (currentItem != null) imageViewFood.setImageResource(currentItem.getFoodImage());

        return convertView;
    }
}
