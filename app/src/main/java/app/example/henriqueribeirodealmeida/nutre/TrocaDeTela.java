package app.example.henriqueribeirodealmeida.nutre;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;



public final class TrocaDeTela {

    public TrocaDeTela(Context PrimeiraTela, Class SegundaTela, int Entrando, int Saindo){
        Intent intent= new Intent(PrimeiraTela, SegundaTela);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(PrimeiraTela.getApplicationContext(),Entrando,Saindo);
        ActivityCompat.startActivity(PrimeiraTela,intent,activityOptionsCompat.toBundle());

    }
}
