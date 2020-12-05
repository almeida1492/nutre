package app.example.henriqueribeirodealmeida.nutre;

import android.animation.ObjectAnimator;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.henriqueribeirodealmeida.nutre.R;

import app.example.henriqueribeirodealmeida.nutre.Data.UserInfoContainer;

public class FirstScree extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_first_scree);

        TextView textnewfood = findViewById(R.id.variavel);
        ImageView newmeal = findViewById(R.id.new_meal);
        TextView textnewmeal       = findViewById(R.id.text_newmeal);
        final ImageView searchView = findViewById(R.id.search_action);
        ImageView userView         = findViewById(R.id.user_action);
        TextView title             =  findViewById(R.id.textView_scree);
        Button button              =  findViewById(R.id.initial_button);
        TextView info              =  findViewById(R.id.text_info);
        final TextView search      = findViewById(R.id.text_search);
        MealViewModel mealViewModel = ViewModelProviders.of(this).get(MealViewModel.class);
        final Context c = this;
        //font
        Typeface balooChettanType = Typeface.createFromAsset(getAssets(), "fonts/BalooChettan-Regular.ttf");
        button.setTypeface(balooChettanType);
        title.setTypeface(balooChettanType);
        search.setTypeface(balooChettanType);
        info.setTypeface(balooChettanType);
        textnewfood.setTypeface(balooChettanType);
        textnewmeal.setTypeface(balooChettanType);

       
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserInfoContainer.getAge( c) != 0){

                    new TrocaDeTela(FirstScree.this,MainActivity.class,R.anim.mover_esquerda,R.anim.fade_in);

                    }else {
                    new TrocaDeTela(FirstScree.this,UserActivity.class,R.anim.mover_esquerda,R.anim.fade_in);
                }
            }  });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TrocaDeTela(FirstScree.this,UserActivity.class,R.anim.mover_esquerda,R.anim.fade_in);

            }
        });

        userView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TrocaDeTela(FirstScree.this,UserActivity.class,R.anim.mover_esquerda,R.anim.fade_in);

            }

        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TrocaDeTela(FirstScree.this,SearchActivity.class,R.anim.mover_esquerda,R.anim.fade_in);


            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TrocaDeTela(FirstScree.this,SearchActivity.class,R.anim.mover_esquerda,R.anim.fade_in);


            }
        });

      textnewfood.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              new TrocaDeTela(FirstScree.this,NewFood.class,R.anim.mover_esquerda,R.anim.fade_in);
          }
      });


        if(UserInfoContainer.getAge(c) != 0){
            newmeal.setVisibility(View.VISIBLE);
            textnewmeal.setVisibility(View.VISIBLE);
                textnewmeal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new TrocaDeTela(FirstScree.this,NewMealActivity.class,R.anim.mover_esquerda,R.anim.fade_in);
                    }
                });

        }else{
            newmeal.setVisibility(View.GONE);
            textnewmeal.setVisibility(View.GONE);

        }





    }


}
