package app.example.henriqueribeirodealmeida.nutre;
import android.animation.ObjectAnimator;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.henriqueribeirodealmeida.nutre.R;

public class SplashScreen extends AppCompatActivity {
    Animation mutreAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_screen_splash);


            TextView nutre =findViewById(R.id.nutre);

            nutre.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/BalooChettan-Regular.ttf"));


             mutreAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);

            nutre.setAnimation(mutreAnim);

       ObjectAnimator titleview = ObjectAnimator.ofFloat(nutre, "rotation", 370);
        titleview.start();
        titleview.setDuration(1000);






            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                     new  TrocaDeTela(SplashScreen.this,MainActivity.class,R.anim.fade_out,R.anim.fade_in);
                        finish();
                }
            },1500);


    }






}
