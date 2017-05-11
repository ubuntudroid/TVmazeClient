package com.rainerlang.tvmazeclient.helper;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class DateHelper {

  public static String dateString(GregorianCalendar cal){
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat fmt = new SimpleDateFormat("yyy-MM-dd");
    fmt.setCalendar(cal);
    return fmt.format(cal.getTime());
  }

}
