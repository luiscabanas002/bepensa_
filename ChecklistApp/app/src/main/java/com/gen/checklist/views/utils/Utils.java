package com.gen.checklist.views.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {

    private static final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
                    | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION |
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, //Covers status bar
            PixelFormat.TRANSLUCENT);

    public static void hideNavigations(Activity context) {
   /*     View decorView = context.getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);*/
    }

    public static boolean isValidString(String string) {
        return string != null && string.length() > 0;
    }

    public static String getDateString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static Date getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date date = new Date();
        dateFormat.format(date);
        return date;
    }

    public static String getDateTimeString(Date mDate){
        DateFormat mDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return mDateFormat.format(mDate);
    }

    public static String getDateTimeString(){
        DateFormat mDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return mDateFormat.format(new Date());
    }

    public static String getDateString(Date mDate){
        DateFormat mDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return mDateFormat.format(mDate);
    }

    public static String getHourString() {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("hh:mm a");
        return mSimpleDateFormat.format(new Date());
    }

    public static String getHour(Date mDate) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        return mSimpleDateFormat.format(mDate);
    }


    public static String getHour24String() {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        return mSimpleDateFormat.format(new Date());
    }

    public static String getHourString(Date mDate){
        DateFormat mDateFormat = new SimpleDateFormat("hh:mm a");
        return mDateFormat.format(mDate);
    }

    public static String formatDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH);
        return dateFormat.format(date);
    }

    public static String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        return dateFormat.format(date);
    }

    public Date convertDate(String fecha, String hora){
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        try {
            return mSimpleDateFormat.parse((fecha + " " + hora));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDateStringWS(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        dateFormat.format(date);
        Calendar cal = dateFormat.getCalendar();
        cal.setTimeZone(TimeZone.getTimeZone("America/Merida"));
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, (date.getHours()));
        return "/Date(" + cal.getTimeInMillis() + ")/";
    }

    public static Date convertStringToDate(String fecha, String hora) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            return mSimpleDateFormat.parse((fecha + " " + hora));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }


    public static Calendar convertDateToCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static void hideKeyboard(EditText editText, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public static String convertJson(Object obj, Class classType){
        Gson mGson = new Gson();
        String json = mGson.toJson(obj,classType);
        return json;
    }

    public static String roundTwoDecimals(float d) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("es", "MX"));
        format.setGroupingUsed(true);

        float number = d;

        number = Math.round(number * 100);
        number = number / 100;

        String result = format.format(number);
        result = result.replace("$", "");
        return result;
    }

    public static boolean validateRangeDate(Date fechaInicio, Date fechaFin, String format) {
        boolean result = false;
        try {
            DateFormat formatter = new SimpleDateFormat(format);
            Date today = new Date();
            Date mDate = formatter.parse(formatter.format(today));

            boolean afterFechaFin = mDate.after(fechaFin);
            boolean isEqualFechaFin = (mDate.compareTo(fechaFin) == 0 ? true : false);

            boolean afterFechaInicio = mDate.after(fechaInicio);
            boolean isEqualFechaInicio = (mDate.compareTo(fechaInicio) == 0 ? true : false);


            if (!afterFechaFin || isEqualFechaFin) {
                if (afterFechaInicio || isEqualFechaInicio) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getJSON(String url) {
        HttpURLConnection con = null;
        try {
            URL u = new URL(url);
            con = (HttpURLConnection) u.openConnection();

            con.connect();


            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            return sb.toString();


        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    private static ConnectivityManager mConnectivityManager;
    public static Boolean checkWifi(Context mContext) {
        mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mConnectivityManager != null) {
            NetworkInfo info = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Boolean checkDatasMovil(Context mContext){
        mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mConnectivityManager != null) {
            NetworkInfo info = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

}