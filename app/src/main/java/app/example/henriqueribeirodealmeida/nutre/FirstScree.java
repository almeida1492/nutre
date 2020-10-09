package app.example.henriqueribeirodealmeida.nutre;

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

        final ImageView searchView = findViewById(R.id.search_action);
        ImageView userView = findViewById(R.id.user_action);
        TextView title =  findViewById(R.id.textView_scree);
        Button button =  findViewById(R.id.initial_button);
        TextView info =  findViewById(R.id.text_info);
        final TextView search = findViewById(R.id.text_search);
        final Context c = this;
        //font
        Typeface balooChettanType = Typeface.createFromAsset(getAssets(), "fonts/BalooChettan-Regular.ttf");
        button.setTypeface(balooChettanType);
        title.setTypeface(balooChettanType);

       
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserInfoContainer.getAge( c) != 0){
                    Intent intent = new Intent(FirstScree.this, MainActivity.class);
                    ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.mover_esquerda,R.anim.fade_in);
                    ActivityCompat.startActivity(FirstScree.this,intent,activityOptionsCompat.toBundle());

                    }else {

                    Intent intent = new Intent(FirstScree.this, UserActivity.class);
                    ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.mover_esquerda, R.anim.fade_in);
                    ActivityCompat.startActivity(FirstScree.this, intent, activityOptionsCompat.toBundle());
                }
            }  });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstScree.this, UserActivity.class);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.mover_esquerda,R.anim.fade_in);
                ActivityCompat.startActivity(FirstScree.this,intent,activityOptionsCompat.toBundle());



            }
        });

        userView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Intent intent = new Intent(FirstScree.this, UserActivity.class);
                    ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.mover_esquerda, R.anim.fade_in);
                    ActivityCompat.startActivity(FirstScree.this, intent, activityOptionsCompat.toBundle());

            }

        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstScree.this, SearchActivity.class);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.mover_esquerda,R.anim.fade_in);
                ActivityCompat.startActivity(FirstScree.this,intent,activityOptionsCompat.toBundle());

            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstScree.this, SearchActivity.class);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.mover_esquerda,R.anim.fade_in);
                ActivityCompat.startActivity(FirstScree.this,intent,activityOptionsCompat.toBundle());

            }
        });


    }
}
