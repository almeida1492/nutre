package app.example.henriqueribeirodealmeida.nutre.Data;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.List;


    public class DBAdapter extends BaseAdapter {
        private Context mContext;
        private List<Food> mFoodList;

        public DBAdapter(Context mContext, List<Food> mFoodList) {
            this.mContext = mContext;
            this.mFoodList = mFoodList;
        }

        @Override
        public int getCount() {
            return mFoodList.size();
        }

        @Override
        public Object getItem(int position) {
            return mFoodList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return mFoodList.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            return convertView;
        }
    }

