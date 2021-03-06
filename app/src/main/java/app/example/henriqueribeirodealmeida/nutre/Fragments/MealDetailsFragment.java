package app.example.henriqueribeirodealmeida.nutre.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import app.example.henriqueribeirodealmeida.nutre.Adapters.AddedFoodAdapter;
import app.example.henriqueribeirodealmeida.nutre.Entities.Food;
import app.example.henriqueribeirodealmeida.nutre.Helpers;

import com.example.henriqueribeirodealmeida.nutre.R;

import java.util.ArrayList;

public class MealDetailsFragment extends DialogFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_meal_details, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        Bundle bundle = getArguments();
        ArrayList<Food> foods;
        foods = bundle.getParcelableArrayList("foods");
        String name = bundle.getString("name");
        String date = bundle.getString("date");
        TextView nameView = rootView.findViewById(R.id.name);
        nameView.setText(name);
        ImageView iconView = rootView.findViewById(R.id.meal_icon);
        switch (bundle.getInt("foodicon")){
            case R.drawable.breakfast:
                iconView.setImageResource(R.drawable.breakfast);
                break;
            case R.drawable.lunch:
                iconView.setImageResource(R.drawable.lunch);
                break;
            case R.drawable.supper:
                iconView.setImageResource(R.drawable.supper);
                break;
            case R.drawable.meal_placeholder:
                iconView.setImageResource(R.drawable.meal_placeholder);
                break;
        }



        TextView dateView = rootView.findViewById(R.id.item_info);
        String formattedDate = Helpers.formatDate(date, true);
        dateView.setText(formattedDate);

        if (foods != null){
            AddedFoodAdapter adapter = new AddedFoodAdapter(getContext(), foods);
            ListView foodsView = rootView.findViewById(R.id.added_food_list);
            foodsView.setAdapter(adapter);
        }



        return rootView;




    }

}
