package app.example.henriqueribeirodealmeida.nutre;

import android.animation.ObjectAnimator;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.example.henriqueribeirodealmeida.nutre.Adapters.MealHistoryAdapter;
import app.example.henriqueribeirodealmeida.nutre.Adapters.SummaryAdapter;
import app.example.henriqueribeirodealmeida.nutre.Data.UserInfoContainer;
import app.example.henriqueribeirodealmeida.nutre.Entities.DailyMeal;
import app.example.henriqueribeirodealmeida.nutre.Entities.Food;
import app.example.henriqueribeirodealmeida.nutre.Entities.Meal;
import app.example.henriqueribeirodealmeida.nutre.Entities.Nutrient;
import app.example.henriqueribeirodealmeida.nutre.Entities.SummaryValues;
import app.example.henriqueribeirodealmeida.nutre.Fragments.DatePickerFragment;
import app.example.henriqueribeirodealmeida.nutre.Fragments.MealDetailsFragment;

import com.example.henriqueribeirodealmeida.nutre.R;
import com.transitionseverywhere.Fade;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.TransitionManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final static String DATETIMEKEY = "com.example.henriqueribeirodealmeida.nutre.datetimekey";
    private static final int NUTRIENTS_COUNT = 17;

    private SummaryValues summaryValues;
    private ArrayList<Nutrient> summaryItems;
    private SummaryAdapter summaryAdapter;
    private ArrayList<DailyMeal> dailyMeals;
    private String CalendarDate;
    double helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag("date picker");
        if(fragment != null) getSupportFragmentManager().beginTransaction().remove(fragment).commit();

       setContentView(R.layout.activity_main);

        final ViewGroup transitionsContainer = findViewById(R.id.transitions_container);
        final TextView  titleView          = transitionsContainer.findViewById(R.id.title);
        final RelativeLayout panelView     = findViewById(R.id.summary_panel);
        final GridView  summaryView        = findViewById(R.id.summary);
        final View      panelMask          = findViewById(R.id.panel_mask);
        final ListView  mealHistoryView    = findViewById(R.id.meal_history);
        final TextView  emptyView          = findViewById(R.id.empty);
        final TextView summaryTitle        = findViewById(R.id.summary_panel_title);
        final TextView summaryHeader       = findViewById(R.id.summary_panel_header);
        final TextView mealHistoryTitle    = findViewById(R.id.meal_history_title);

        //Transitions not used yet
        final ImageView newMealButton = transitionsContainer.findViewById(R.id.new_meal_action);
        final ImageView searchView    = transitionsContainer.findViewById(R.id.search_action);
        final ImageView userView      =  transitionsContainer.findViewById(R.id.user_action);
        final ImageView calendar      = transitionsContainer.findViewById(R.id.calendar_action);
        final ImageView newFood       =  transitionsContainer.findViewById(R.id.new_food);
        final FloatingActionButton floatButton      = findViewById(R.id.floatingButton);
        final TextView textfloatb      = transitionsContainer.findViewById(R.id.text_floatbutton);

        //Set fonts
        Typeface balooChettanType = Typeface.createFromAsset(getAssets(), "fonts/BalooChettan-Regular.ttf");
        Typeface notoSansType     = Typeface.createFromAsset(getAssets(), "fonts/NotoSans-Regular.ttf");
        titleView.setTypeface(balooChettanType);
        summaryTitle.setTypeface(balooChettanType);
        summaryHeader.setTypeface(notoSansType);
        mealHistoryTitle.setTypeface(balooChettanType);

        //Values for Date
        DateFormat df  = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        DateFormat cdf = new SimpleDateFormat("yyyy-MM-dd");

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String tempCalendarDate = prefs.getString(DATETIMEKEY, null);

        final String date = df.format(Calendar.getInstance().getTime());
        final String formattedDate;

        if (tempCalendarDate!=null) {
            Date CalDate = null;
            try {
                CalDate = cdf.parse(tempCalendarDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar c = Calendar.getInstance();
            c.setTime(CalDate);
            c.add(Calendar.MONTH, 2);
            String month;

            if(c.get(Calendar.MONTH) < 10){
                month = "0"+String.valueOf(c.get(Calendar.MONTH));
            }else{
                month = String.valueOf(c.get(Calendar.MONTH));
            }

            String year = String.valueOf(c.get(Calendar.YEAR));
            String day;

            if(c.get(Calendar.DAY_OF_MONTH) < 10){
                day = "0"+String.valueOf(c.get(Calendar.DAY_OF_MONTH));
            }else{
                day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
            }

            tempCalendarDate = year +"-"+month+"-"+day;

        }


        if(savedInstanceState==null){
            formattedDate = Helpers.formatDate(date, false);
        }else{
            formattedDate = Helpers.formatDate(tempCalendarDate + date.substring(10, 25), false);
        }

        final String CalendarDate = tempCalendarDate;
        summaryHeader.setText(formattedDate);

        /*ListView summaryListView = findViewById(R.id.summary);*/
        summaryValues = new SummaryValues(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        summaryItems = new ArrayList<>();

        //Create a new Nutrient object for each slot in summaryItems list in order to make possible
        //the setting in setSummaryItems()
        for (int i = 0; i < NUTRIENTS_COUNT; i++){
            summaryItems.add(new Nutrient());
        }
        //functions
        setSummaryItems();
        user();

        summaryAdapter = new SummaryAdapter(this, summaryItems, CalendarDate);
        summaryView.setAdapter(summaryAdapter);
        summaryView.setHorizontalSpacing(3);
        summaryView.setVerticalSpacing(20);
        setGridViewHeight(summaryView);


        panelView.setOnClickListener(new VisibleToggleClickListener() {

            @Override
            protected void changeVisibility(boolean visible) {
                findViewById(R.id.litlle_button).setRotation(visible ?180:0);
                TransitionManager.beginDelayedTransition(transitionsContainer, new Fade(Gravity.LEFT).setDuration(800));


                // This method call returns the panel status towards view expansion from the adapter
                panelMask.setVisibility(visible? View.INVISIBLE:View.VISIBLE);
                summaryAdapter.notifyClickToExpand();
                setGridViewHeight(summaryView);
            }
        });

        dailyMeals = new ArrayList<>();

        final MealHistoryAdapter mealHistoryAdapter = new MealHistoryAdapter(this, dailyMeals, CalendarDate);
        mealHistoryView.setAdapter(mealHistoryAdapter);
        setListViewHeight(mealHistoryView);

        //meal and food
        final LifecycleOwner lifecycleOwner   = this;
        final MealViewModel mealViewModel     = ViewModelProviders.of(this).get(MealViewModel.class);
        FoodViewModel foodViewModel           = ViewModelProviders.of(this).get(FoodViewModel.class);
        DailyMealViewModel dailyMealViewModel = ViewModelProviders.of(this).get(DailyMealViewModel.class);



        //daily meal started
        dailyMealViewModel.getAllDailyMeals(CalendarDate+"%").observe(this, new Observer<List<DailyMeal>>() {
            @Override
            public void onChanged(@Nullable final List<DailyMeal> liveMeals) {
               dailyMeals.clear();

                for (DailyMeal meal : liveMeals) {
                    try {
                        if (meal != null && meal.getName() != null && meal.getDate().substring(0, 10).equals(CalendarDate)) {
                            dailyMeals.add(meal);
                        }

                    } catch (NullPointerException e) {
                        if (meal != null && meal.getName() != null) {
                            dailyMeals.add(meal);
                        }
                    }
                }

                        mealHistoryAdapter.notifyDataSetInvalidated();
                        mealHistoryAdapter.notifyDataSetChanged();

                if (dailyMeals.isEmpty()) {
                      emptyView.setVisibility(View.VISIBLE);
                      mealHistoryView.setVisibility(View.GONE);
                   }
                setListViewHeight(mealHistoryView);
            }
        });

        foodViewModel.getAllFood(CalendarDate+"%").observe(this, new Observer<List<Food>>() {

            @Override
            public void onChanged(@Nullable final List<Food> foods) {

                int [] mealIds = new int[foods.size()];
                final HashMap<Integer, Integer> mealQuantity = new HashMap<>();

                for (int i=0; i < foods.size() ; i++) {
                    try {

                            Food food = foods.get(i);
                            mealIds[i] = food.getMealId();

                            if (mealQuantity.get(food.getMealId()) == null) {
                                mealQuantity.put(food.getMealId(), (int) food.getQuantityPerUnit());

                            } else {

                                    int oldQuantity = mealQuantity.get(food.getMealId());
                                    mealQuantity.put(food.getMealId(), oldQuantity + (int) food.getQuantityPerUnit());

                            }

                    }catch(NullPointerException e){

                    }
                    }

                mealViewModel.findByIds(mealIds).observe(lifecycleOwner, new Observer<List<Meal>>() {
                    @Override
                    public void onChanged(@Nullable final List<Meal> liveMeals) {

                            Float energy = 0.0f;
                            Float carbohydrate = 0.0f;
                            Float protein  = 0.0f;
                            Float totalFat = 0.0f;
                            Float saturatedFat = 0.0f;
                            Float riboflavin = 0.0f;
                            Float fibers   = 0.0f;
                            Float sodium   = 0.0f;
                            Float vitaminC = 0.0f;
                            Float calcium  = 0.0f;
                            Float iron     = 0.0f;
                            Float vitaminA = 0.0f;
                            Float water    = 0.0f;
                            Float potassium = 0.0f;
                            Float magnesium = 0.0f;
                            Float niacin = 0.0f;
                            Float thiamine = 0.0f;

                        for (Meal meal : liveMeals) {
                            int quantity = mealQuantity.get(meal.getId());


                                energy += meal.getEnergy() * meal.getUnityMultiplier() * quantity / 100;
                                carbohydrate += meal.getCarbohydrate() * meal.getUnityMultiplier() * quantity / 100;
                                water += meal.getWater() * meal.getUnityMultiplier() * quantity / 100;
                                protein += meal.getProtein() * meal.getUnityMultiplier() * quantity / 100;
                                totalFat += meal.getTotalFat() * meal.getUnityMultiplier() * quantity / 100;
                                saturatedFat += meal.getSaturatedFat() * meal.getUnityMultiplier() * quantity / 100;
                                riboflavin += meal.getRiboflavin() * meal.getUnityMultiplier() * quantity / 100;
                                fibers += meal.getFibers() * meal.getUnityMultiplier() * quantity / 100;
                                sodium += meal.getSodium() * meal.getUnityMultiplier() * quantity / 100;
                                vitaminC += meal.getVitaminC() * meal.getUnityMultiplier() * quantity / 100;
                                calcium += meal.getCalcium() * meal.getUnityMultiplier() * quantity / 100;
                                iron += meal.getIron() * meal.getUnityMultiplier() * quantity / 100;
                                vitaminA += meal.getVitaminA() * meal.getUnityMultiplier() * quantity / 100;
                                niacin += meal.getNiacin() * meal.getUnityMultiplier() * quantity / 100;
                                potassium += meal.getPotassium() * meal.getUnityMultiplier() * quantity / 100;
                                magnesium += meal.getMagnesium() * meal.getUnityMultiplier() * quantity / 100;
                                thiamine += meal.getThiamine() * meal.getUnityMultiplier() * quantity / 100;

                                                            }

                                    summaryItems.get(0).setValue(Math.round(energy));
                                    summaryItems.get(1).setValue(Math.round(water));
                                    summaryItems.get(2).setValue(Math.round(carbohydrate));
                                    summaryItems.get(3).setValue(Math.round(protein));
                                    summaryItems.get(4).setValue(Math.round(totalFat));
                                    summaryItems.get(5).setValue(Math.round(saturatedFat));
                                    summaryItems.get(6).setValue(Math.round(fibers));
                                    summaryItems.get(7).setValue(Math.round(sodium));
                                    summaryItems.get(8).setValue(Math.round(vitaminC));
                                    summaryItems.get(9).setValue(Math.round(calcium));
                                    summaryItems.get(10).setValue(Math.round(iron));
                                    summaryItems.get(11).setValue(Math.round(vitaminA));
                                    summaryItems.get(12).setValue(Math.round(potassium));
                                    summaryItems.get(13).setValue(Math.round(magnesium));
                                    summaryItems.get(14).setValue(Math.round(thiamine));
                                    summaryItems.get(15).setValue(Math.round(riboflavin));
                                    summaryItems.get(16).setValue(Math.round(niacin));

                                    summaryAdapter.notifyDataSetInvalidated();
                                    summaryAdapter.notifyDataSetChanged();

                                    for (int x = 0; x < NUTRIENTS_COUNT; x++) {

                                        summaryItems.get(x).setHuman();
                                        summaryItems.get(x).setPorcent();

                                    }


                    }//
                });//
            }//
        });//


           rotate(titleView);
           rotate(userView);
           rotate(searchView);
           rotate(newMealButton);
           rotate(floatButton);
           rotate(calendar);




        floatButton.setOnClickListener(new VisibleToggleClickListener() {
            @Override
            protected void changeVisibility(boolean visible) {

                TransitionManager.beginDelayedTransition(transitionsContainer, new Fade(Gravity.LEFT).setDuration(1200));


                textfloatb.setVisibility(visible?View.GONE:View.VISIBLE);
                textfloatb.setTranslationX(visible?0:-200);
                textfloatb.setText(visible?"":  dailyMeals.isEmpty()? "Adicione sua primeira\nrefeição do dia.": "Adicione uma Refeição.");

                floatButton.animate().setDuration(850);
                floatButton.animate().translationX(visible?0:-200);
                floatButton.animate().rotation(visible ?0 :-180);

            }


        });

        textfloatb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TrocaDeTela(MainActivity.this,NewMealActivity.class,R.anim.mover_esquerda,R.anim.fade_in);
            }
        });


        newFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 new TrocaDeTela(MainActivity.this,NewFood.class,R.anim.mover_esquerda,R.anim.fade_in);
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TrocaDeTela(MainActivity.this,SearchActivity.class,R.anim.mover_esquerda,R.anim.fade_in);
            }
        });

        userView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TrocaDeTela(MainActivity.this,UserActivity.class,R.anim.mover_esquerda,R.anim.fade_in);
            }
        });

        newMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TrocaDeTela(MainActivity.this,NewMealActivity.class,R.anim.mover_esquerda,R.anim.fade_in);

            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private void user(){

    final Context c = this;
    if(UserInfoContainer.getAge( c) == 0){
        new TrocaDeTela(MainActivity.this, UserActivity.class,R.anim.mover_esquerda,R.anim.fade_in);

    }
}

    private void setSummaryItems(){
        helper = UserInfoContainer.getEnergy(this);
        //helper = (int)Helpers.calculateRequiredEnergy(this);

        summaryItems.get(0).setName("Energia");
        summaryItems.get(0).setValue((int) summaryValues.getEnergy());
        summaryItems.get(0).setSuggestedValue(helper);
        summaryItems.get(0).setMeasure(" kcal");

        summaryItems.get(1).setName("Água");
        summaryItems.get(1).setValue((int) summaryValues.getWater());
        summaryItems.get(1).setSuggestedValue(UserInfoContainer.getWeight(this)*35);
        summaryItems.get(1).setMeasure(" ml");

        summaryItems.get(2).setName("Carboidrato");
        summaryItems.get(2).setValue((int) summaryValues.getCarbohydrate());
        summaryItems.get(2).setSuggestedValue((int) ((helper * 0.65)/4));
        summaryItems.get(2).setMeasure(" g");

        summaryItems.get(3).setName("Proteína");
        summaryItems.get(3).setValue((int) summaryValues.getProtein());
        summaryItems.get(3).setSuggestedValue(UserInfoContainer.getWeight(this));
        summaryItems.get(3).setMeasure(" g");

        summaryItems.get(4).setName("Gorduras totais");
        summaryItems.get(4).setValue((int) summaryValues.getTotalFat());
        summaryItems.get(4).setSuggestedValue((int) ((helper * 0.225)/9));
        summaryItems.get(4).setMeasure(" g");

        summaryItems.get(5).setName("Gordura Saturada");
        summaryItems.get(5).setValue((int) summaryValues.getSaturatedFat());
        summaryItems.get(5).setSuggestedValue((int) ((helper * 0.07)/9));
        summaryItems.get(5).setMeasure(" g");

        summaryItems.get(6).setName("Fibras");
        summaryItems.get(6).setValue((int) summaryValues.getFibers());
        summaryItems.get(6).setSuggestedValue(25);
        summaryItems.get(6).setMeasure(" g");

        summaryItems.get(7).setName("Sódio");
        summaryItems.get(7).setValue((int) summaryValues.getSodium());
        summaryItems.get(7).setSuggestedValue(2400);
        summaryItems.get(7).setMeasure(" mg");

        summaryItems.get(8).setName("Vitamina C");
        summaryItems.get(8).setValue((int) summaryValues.getVitaminC());
        summaryItems.get(8).setSuggestedValue(45);
        summaryItems.get(8).setMeasure(" mg");

        summaryItems.get(9).setName("Cálcio");
        summaryItems.get(9).setValue((int) summaryValues.getCalcium());
        summaryItems.get(9).setSuggestedValue(600);
        summaryItems.get(9).setMeasure(" mg");

        summaryItems.get(10).setName("Ferro");
        summaryItems.get(10).setValue((int) summaryValues.getIron());
        summaryItems.get(10).setSuggestedValue(14);
        summaryItems.get(10).setMeasure(" mg");

        summaryItems.get(11).setName("Vitamina A");
        summaryItems.get(11).setValue((int) summaryValues.getVitaminA());
        summaryItems.get(11).setSuggestedValue(600);
        summaryItems.get(11).setMeasure(" µg");

        summaryItems.get(12).setName("Potássio");
        summaryItems.get(12).setValue((int) summaryValues.getPotassium());
        summaryItems.get(12).setSuggestedValue(2400);
        summaryItems.get(12).setMeasure(" mg");

        summaryItems.get(13).setName("Magnésio");
        summaryItems.get(13).setValue((int) summaryValues.getMagnesium());
        summaryItems.get(13).setSuggestedValue(260);
        summaryItems.get(13).setMeasure(" mg");

        summaryItems.get(14).setName("Tiamina");
        summaryItems.get(14).setValue((int) summaryValues.getThiamine());
        summaryItems.get(14).setSuggestedValue(1.2);
        summaryItems.get(14).setMeasure(" mg");

        summaryItems.get(15).setName("Riboflavina");
        summaryItems.get(15).setValue((int) summaryValues.getRiboflavin());
        summaryItems.get(15).setSuggestedValue(10);
        summaryItems.get(15).setMeasure(" mg");

        summaryItems.get(16).setName("Niacina");
        summaryItems.get(16).setValue((int) summaryValues.getThiamine());
        summaryItems.get(16).setSuggestedValue(1.2);
        summaryItems.get(16).setMeasure(" mg");

    }

    public void showMealDetails(DailyMeal meal){
        final Bundle bundle = new Bundle();
        final ArrayList<Food> foods = new ArrayList<>();

        FoodViewModel foodViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        foodViewModel.findDailyMealFoods(meal.getId()).observe(this, new Observer<List<Food>>() {
            @Override
            public void onChanged(@Nullable List<Food> f) {
                if (f != null){
                    foods.addAll(f);
                }
            }
        });

        bundle.putParcelableArrayList("foods", foods);
        bundle.putString("name", meal.getName());
        bundle.putString("date", meal.getDate());
        bundle.putInt("foodicon", meal.getFoodicon());
        MealDetailsFragment dialog = new MealDetailsFragment();
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "MealDetailsFragment");
    }

    public void editMeal(DailyMeal meal){
        final Bundle bundle = new Bundle();
        final ArrayList<Food> foods = new ArrayList<>();

        final String mealType = meal.getName();
        final int mealId = meal.getId();
        final int mealicon = meal.getFoodicon();
        FoodViewModel foodViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        foodViewModel.findDailyMealFoods(meal.getId()).observe(this, new Observer<List<Food>>() {
            @Override
            public void onChanged(@Nullable List<Food> f) {
                if (f != null){
                    foods.addAll(f);
                    ArrayList<Food> foodClone = new ArrayList<>(foods);
                    Intent intent = new Intent(MainActivity.this, NewMealActivity.class);
                    bundle.putParcelableArrayList("foods", foodClone);
                    bundle.putString("name", mealType);
                    bundle.putInt("dailyMealId", mealId);
                    bundle.putInt("foodicon", mealicon);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
            }
        });
    }

    public void deleteMeal(DailyMeal meal) {
        DailyMealViewModel dailyMealViewModel = ViewModelProviders.of(this).get(DailyMealViewModel.class);
        dailyMealViewModel.delete(meal);
    }

    private void setListViewHeight(ListView listView) {

        float px = 500 * (listView.getResources().getDisplayMetrics().density);//teste

        ListAdapter adapter = listView.getAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec((int) px, View.MeasureSpec.EXACTLY);

        for (int i = 0; i < adapter.getCount(); i++) {

            View groupItem = adapter.getView(i, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += groupItem.getMeasuredHeight();
        }

        int height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        if (height < 10){
            height = 200;
        }
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    private void setGridViewHeight(GridView listView) {

        ListAdapter adapter = listView.getAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.EXACTLY);

        for (int i = 4; i < adapter.getCount() ; i++) {

            View groupItem = adapter.getView(i, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

        }


        int height = totalHeight/2;

        ViewGroup.LayoutParams params = listView.getLayoutParams();


        if (height < 10){
            height = 100;
        }

        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    public void setCalendarDate(String calendarDate) {
        this.CalendarDate = calendarDate;
        Toast.makeText(this, CalendarDate, Toast.LENGTH_LONG).show();
    }

    public void showDatePicker(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "date picker");
    }

    public void rotate(View view){
        ObjectAnimator titleview = ObjectAnimator.ofFloat(view, "rotation", 360);
        titleview.start();
        titleview.setDuration(1000);

    }

}
