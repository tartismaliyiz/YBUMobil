package io.smartface.MobileYBU;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import io.smartface.MobileYBU.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;


public class Yemek extends Activity {
    private String url1 = "http://api.ybubiltek.org/mobilybu/android/yemekler.php?tarih=";
    public TextView yemek1,yemek2,yemek3;
    public Button btnButon;
    public Button btn1, btn2, btn3;
    final Context context = this;
    private int year;
    private int month;
    private int day;

    final Calendar c = Calendar.getInstance();
    static final int DATE_PICKER_ID = 1111;

    private HandleJSON obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yemek);


        yemek1 = (TextView)findViewById(R.id.ymkTv1);
        yemek2 = (TextView)findViewById(R.id.ymkTv2);
        yemek3 = (TextView)findViewById(R.id.ymkTv3);

        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);

        btn1 = (Button) findViewById(R.id.oy1);
        btn2 = (Button) findViewById(R.id.oy2);
        btn3 = (Button) findViewById(R.id.oy3);



        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                final String imei=tm.getDeviceId();

                //yemek3
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.oy_ver);
                dialog.setTitle(yemek3.getText().toString() + " nasıldı sence ?");

                Button mukemmel = (Button) dialog.findViewById(R.id.mukemmel);
                Button kararsiz = (Button) dialog.findViewById(R.id.kararsiz);
                Button kotu = (Button) dialog.findViewById(R.id.kotu);

                mukemmel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String yemekTarih = year + "-" + month + "-" + day;
                        String sira = "3";
                        String oy = "3";
                        String urlYemek = "http://api.ybubiltek.org/mobilybu/android/oylama.php?yemekTarih=" + yemekTarih + "&sira=" + sira + "&oy=" + oy + "&imei=" + imei;
                        new HttpAsyncTask().execute(urlYemek);
                        dialog.dismiss();
                    }
                });
                kararsiz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String yemekTarih = year + "-" + month + "-" + day;
                        String sira = "3";
                        String oy = "2";
                        String urlYemek = "http://api.ybubiltek.org/mobilybu/android/oylama.php?yemekTarih=" + yemekTarih + "&sira=" + sira + "&oy=" + oy + "&imei=" + imei;
                        new HttpAsyncTask().execute(urlYemek);
                        dialog.dismiss();
                    }
                });
                kotu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String yemekTarih = year + "-" + month + "-" + day;
                        String sira = "3";
                        String oy = "1";
                        String urlYemek = "http://api.ybubiltek.org/mobilybu/android/oylama.php?yemekTarih=" + yemekTarih + "&sira=" + sira + "&oy=" + oy + "&imei=" + imei;
                        new HttpAsyncTask().execute(urlYemek);
                        dialog.dismiss();
                    }
                });
                dialog.show();

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                Window window = dialog.getWindow();
                lp.copyFrom(window.getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp);


            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //yemek2
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.oy_ver);
                dialog.setTitle(yemek2.getText().toString() + " nasıldı sence ?");

                Button mukemmel = (Button) dialog.findViewById(R.id.mukemmel);
                Button kararsiz = (Button) dialog.findViewById(R.id.kararsiz);
                Button kotu = (Button) dialog.findViewById(R.id.kotu);

                mukemmel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                        final String imei=tm.getDeviceId();
                        String yemekTarih = year + "-" + month + "-" + day;
                        String sira = "2";
                        String oy = "3";
                        String urlYemek = "http://api.ybubiltek.org/mobilybu/android/oylama.php?yemekTarih=" + yemekTarih + "&sira=" + sira + "&oy=" + oy + "&imei=" + imei;
                        new HttpAsyncTask().execute(urlYemek);
                        dialog.dismiss();
                    }
                });
                kararsiz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                        final String imei=tm.getDeviceId();
                        String yemekTarih = year + "-" + month + "-" + day;
                        String sira = "2";
                        String oy = "2";
                        String urlYemek = "http://api.ybubiltek.org/mobilybu/android/oylama.php?yemekTarih=" + yemekTarih + "&sira=" + sira + "&oy=" + oy + "&imei=" + imei;
                        new HttpAsyncTask().execute(urlYemek);
                        dialog.dismiss();
                    }
                });
                kotu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                        final String imei=tm.getDeviceId();
                        String yemekTarih = year + "-" + month + "-" + day;
                        String sira = "2";
                        String oy = "1";
                        String urlYemek = "http://api.ybubiltek.org/mobilybu/android/oylama.php?yemekTarih=" + yemekTarih + "&sira=" + sira + "&oy=" + oy + "&imei=" + imei;
                        new HttpAsyncTask().execute(urlYemek);
                        dialog.dismiss();
                    }
                });
                dialog.show();

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                Window window = dialog.getWindow();
                lp.copyFrom(window.getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //yemek1
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.oy_ver);
                dialog.setTitle(yemek1.getText().toString() + " nasıldı sence ?");

                Button mukemmel = (Button) dialog.findViewById(R.id.mukemmel);
                Button kararsiz = (Button) dialog.findViewById(R.id.kararsiz);
                Button kotu = (Button) dialog.findViewById(R.id.kotu);

                mukemmel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                        final String imei=tm.getDeviceId();
                        String yemekTarih = year + "-" + month + "-" + day;
                        String sira = "1";
                        String oy = "3";
                        String urlYemek = "http://api.ybubiltek.org/mobilybu/android/oylama.php?yemekTarih=" + yemekTarih + "&sira=" + sira + "&oy=" + oy + "&imei=" + imei;
                        new HttpAsyncTask().execute(urlYemek);
                        dialog.dismiss();
                    }
                });
                kararsiz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                        final String imei=tm.getDeviceId();
                        String yemekTarih = year + "-" + month + "-" + day;
                        String sira = "1";
                        String oy = "2";
                        String urlYemek = "http://api.ybubiltek.org/mobilybu/android/oylama.php?yemekTarih=" + yemekTarih + "&sira=" + sira + "&oy=" + oy + "&imei=" + imei;
                        new HttpAsyncTask().execute(urlYemek);
                        dialog.dismiss();
                    }
                });
                kotu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                        final String imei=tm.getDeviceId();
                        String yemekTarih = year + "-" + month + "-" + day;
                        String sira = "1";
                        String oy = "1";
                        String urlYemek = "http://api.ybubiltek.org/mobilybu/android/oylama.php?yemekTarih=" + yemekTarih + "&sira=" + sira + "&oy=" + oy + "&imei=" + imei;
                        new HttpAsyncTask().execute(urlYemek);
                        dialog.dismiss();
                    }
                });
                dialog.show();

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                Window window = dialog.getWindow();
                lp.copyFrom(window.getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp);
            }
        });

        month = month + 1;
if(isOnline()) {
    String finalUrl = url1 + year + "-" + month + "-" + day;

    obj = new HandleJSON(finalUrl);
    obj.fetchJSON();
    while (obj.parsingComplete) ;
    yemek1.setText(obj.getYmk1());
    yemek2.setText(obj.getYmk2());
    yemek3.setText(obj.getYmk3());


}else{
    Toast.makeText(getApplicationContext(), "İnternet bağlantısı sağlanamadı!", Toast.LENGTH_SHORT).show();
    finish();
}
        btnButon = (Button) findViewById(R.id.tarih);


        btnButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline()) {
                    showDialog(DATE_PICKER_ID);
                }else{
                    Toast.makeText(getApplicationContext(), "İnternet bağlantısı sağlanamadı!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });


    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:
                return new DatePickerDialog(this, pickerListener, year, month - 1,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {


            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;

            month = month + 1;

            String finalUrl = url1 + year + "-" + month + "-" + day;
if(isOnline()){
            obj = new HandleJSON(finalUrl);
            obj.fetchJSON();
            while(obj.parsingComplete);
            yemek1.setText(obj.getYmk1());
            yemek2.setText(obj.getYmk2());
            yemek3.setText(obj.getYmk3());


        }else{
            Toast.makeText(getApplicationContext(), "İnternet bağlantısı sağlanamadı!", Toast.LENGTH_SHORT).show();
            finish();
        }

        }
    };

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }




    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {

        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Teşekkürler!", Toast.LENGTH_LONG).show();
        }
    }


}