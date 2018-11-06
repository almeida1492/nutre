package com.example.henriqueribeirodealmeida.nutre;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.henriqueribeirodealmeida.nutre.Adapters.AddedFoodAdapter;
import com.example.henriqueribeirodealmeida.nutre.Entities.Food;

import java.util.ArrayList;

public class newMealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meal);

        ImageView upButton = findViewById(R.id.up_button);
        ImageView addMeal = findViewById(R.id.add_meal);
        Button addItemButton = findViewById(R.id.add_item);
        ListView addedFoodList = findViewById(R.id.added_food_list);
        ScrollView mainScrollView = findViewById(R.id.main_scroll_view);

        //temp
        ArrayList<Food> foods = new ArrayList<>();
        foods.add(new Food("Arroz Branco", 2, "colher"));
        foods.add(new Food("Feijão Preto", 1, "concha"));
        foods.add(new Food("Carne Vermelha", 1, "unidade"));
        foods.add(new Food("Alface Americana", 1, "unidade"));

        AddedFoodAdapter adapter = new AddedFoodAdapter(this, foods);
        addedFoodList.setAdapter(adapter);
        addedFoodList.setFocusable(false);
        setListViewHeight(addedFoodList);
    }

    private void setListViewHeight(ListView listView) {

        ListAdapter adapter = listView.getAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.EXACTLY);

        for (int i = 0; i < adapter.getCount(); i++) {

            View groupItem = adapter.getView(i, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));

        if (height < 10){
            height = 200;
        }

        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
