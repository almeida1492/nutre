package com.example.henriqueribeirodealmeida.nutre;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FirstScree extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_scree);

        ImageView searchView = findViewById(R.id.search_action);
        ImageView userView = findViewById(R.id.user_action);
        TextView title =  findViewById(R.id.textView_scree);
        Button button =  findViewById(R.id.initial_button);
        TextView info =  findViewById(R.id.text_info);
        TextView search = findViewById(R.id.text_search);

        Typeface balooChettanType = Typeface.createFromAsset(getAssets(), "fonts/BalooChettan-Regular.ttf");


        title.setTypeface(balooChettanType);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //  if(){

                startActivity(new Intent(FirstScree.this, MainActivity.class));

                //}else{
                //        Toast.makeText(FirstScree.this, "Para comecar preencha a informao do usuario ", Toast.LENGTH_SHORT).show();
                //        }
            }  });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstScree.this, UserActivity.class);
                startActivity(intent);

            }
        });

        userView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstScree.this, UserActivity.class);
                startActivity(intent);

            }

        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstScree.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstScree.this, SearchActivity.class);
                startActivity(intent);
            }
        });


    }
}
