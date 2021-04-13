package app.example.henriqueribeirodealmeida.nutre.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.henriqueribeirodealmeida.nutre.R;

import java.util.ArrayList;

import app.example.henriqueribeirodealmeida.nutre.Entities.Nutrient;

public class EditFoodAdapter extends ArrayAdapter<Nutrient> {

    public EditFoodAdapter(@NonNull Context context, @NonNull ArrayList<Nutrient> summaryItems) {
        super(context, 0, summaryItems);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = convertView;

        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.add_new_food, parent, false);
        }


         final Nutrient nutrient = getItem(position);

        TextView nameView = itemView.findViewById(R.id.name21);
        nameView.setText(nutrient.getName());

        TextView typeView = itemView.findViewById(R.id.type);
        typeView.setText(nutrient.getMeasure());

       // NumberPicker value = itemView.findViewById(R.id.valorAdd);
        NumberPicker value = itemView.findViewById(R.id.valorAdd);


        value.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.format("%d ", value);
            }

        });
        value.setMaxValue(1000);


        value.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                nutrient.setValue(newVal);


            }

        });

        value.setValue((int) nutrient.getValue());











        return itemView;
        }

}
