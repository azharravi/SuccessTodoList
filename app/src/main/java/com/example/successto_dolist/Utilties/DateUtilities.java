package com.example.successto_dolist.Utilties;

import android.app.DatePickerDialog;
import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateUtilities {

    private final static String Format_Date = "dd/mm/yyyy";
    private final static String Format_Time = "dd/mm/yyyy hh:mm:ss";

    public static DatePickerDialog showDialog(Context context, DatePickerDialog.OnDateSetListener dateSetListener) {
        Calendar calendar = Calendar.getInstance();
        return new DatePickerDialog(
                context,
                dateSetListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
        );
    }
    public static String toString(int year, int month, int dayOfMonth){
        return dayOfMonth + "/" + (month + 1) + "/" + year;
    }

    public static String toString(Long Date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Date);
        return calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
    }

    public static Long toLong(String Date){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Format_Time, Locale.getDefault());
            return simpleDateFormat.parse(Date + " 00:00:00").getTime();
        }catch (ParseException exception){
            exception.printStackTrace();
        }
        return null;
    }

    public static String today(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
    }

}
