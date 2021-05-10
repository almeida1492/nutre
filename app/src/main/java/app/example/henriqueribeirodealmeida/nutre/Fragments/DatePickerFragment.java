package app.example.henriqueribeirodealmeida.nutre.Fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment {

    private final static String DATETIMEKEY = "com.example.henriqueribeirodealmeida.nutre.datetimekey";
    private final static String DATE = "DATE";
    String calendarDate;
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            Date d = new Date(year-1900, month-1, day);
            String strDate = dateFormatter.format(d);

            setDate(getActivity(),strDate);
            getActivity().recreate();
        }
    };

    public static String getDate(Context c){
        SharedPreferences sp = c.getSharedPreferences(DATETIMEKEY, Context.MODE_PRIVATE);
        return sp.getString(DATE, "");
    }

    public static void setDate(Context c, String strDate){
        SharedPreferences sp = c.getSharedPreferences(DATETIMEKEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(DATE, strDate);
        editor.apply();
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        int y,m,d;
        calendarDate = getDate(getActivity());

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




