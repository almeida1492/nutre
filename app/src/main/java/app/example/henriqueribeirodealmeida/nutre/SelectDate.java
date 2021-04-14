package app.example.henriqueribeirodealmeida.nutre;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

import com.example.henriqueribeirodealmeida.nutre.R;

import app.example.henriqueribeirodealmeida.nutre.Fragments.DatePickerFragment;

public class SelectDate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);
        showDatePicker2();

        new TrocaDeTela(SelectDate.this,MainActivity.class,R.anim.mover_esquerda,R.anim.fade_in);

    }
    public void showDatePicker2() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "date picker");
    }
}