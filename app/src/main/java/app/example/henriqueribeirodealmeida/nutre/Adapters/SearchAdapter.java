package app.example.henriqueribeirodealmeida.nutre.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import app.example.henriqueribeirodealmeida.nutre.Entities.Meal;
import com.example.henriqueribeirodealmeida.nutre.R;
import app.example.henriqueribeirodealmeida.nutre.SearchActivity;

import java.util.ArrayList;

public class SearchAdapter extends ArrayAdapter<Meal> {

    public static final String LOG_TAG = SearchActivity.class.getName();

    private ArrayList<Meal> originalData;
    private ArrayList<Meal> filteredData;
    private ItemFilter mFilter = new ItemFilter();

    public SearchAdapter(@NonNull Context context, ArrayList<Meal> foods) {
        super(context, 0, foods);

        originalData = foods;
        filteredData = foods;
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Nullable
    @Override
    public Meal getItem(int position) {
        return filteredData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = convertView;

        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_search, parent, false);
        }

        final Meal food = getItem(position);

        TextView nameView = itemView.findViewById(R.id.name);
        nameView.setText(food.getName());


        return itemView;
    }

    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();
            final ArrayList<Meal> list = originalData;

            int count = list.size();
            final ArrayList<Meal> nlist = new ArrayList<>(count);

            Meal MealToFilter;

            for (int i = 0; i < count; i++) {
                MealToFilter = list.get(i);
                if (MealToFilter.getName().toLowerCase().contains(filterString)) {

                    Meal mealClone = MealToFilter.getClone();

                    nlist.add(mealClone);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            filteredData = (ArrayList<Meal>) results.values;
            notifyDataSetChanged();
        }

    }
}
