package app.example.henriqueribeirodealmeida.nutre.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.henriqueribeirodealmeida.nutre.R;

public class InfoIMCFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_imc_info, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return rootView;
    }
}
