package app.example.henriqueribeirodealmeida.nutre.Fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment {

    private final static String DATETIMEKEY = "com.example.henriqueribeirodealmeida.nutre.datetimekey";
    String calendarDate;

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            Date d = new Date(year-1900, month-1, day);
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormatter.format(d);

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor;
            editor = prefs.edit();
            editor.putString(DATETIMEKEY, strDate);
            editor.apply();
            getActivity().recreate();
        }
    };


    public static String getDate(Context c){
        SharedPreferences sp = c.getSharedPreferences(DATETIMEKEY, Context.MODE_PRIVATE);
        if (sp.getString(DATETIMEKEY, null) == null){
            setDate(c, "");
        }
        return sp.getString(DATETIMEKEY, "");
    }

    public static void setDate(Context c, String name){
        SharedPreferences sp = c.getSharedPreferences(DATETIMEKEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(DATETIMEKEY, name);
        editor.apply();
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int y,m,d;
        calendarDate = prefs.getString(DATETIMEKEY, null);

        try{

                    y = Integer.parseInt(calendarDate.substring(0,4).replaceAll("[^0-9]",""));
                    m = Integer.parseInt(calendarDate.substring(5,7).replaceAll("[^0-9]","") );
                    d = Integer.parseInt(calendarDate.substring(8,10).replaceAll("[^0-9]",""));

                return new DatePickerDialog(getActivity(), dateSetListener, y, m, d);

        }catch(NullPointerException e){
            return new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
        }
    }
}




