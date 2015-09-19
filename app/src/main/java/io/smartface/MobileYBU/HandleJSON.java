package io.smartface.MobileYBU;


import android.annotation.SuppressLint;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class HandleJSON {
    private String ymk1 = "ymk1";
    private String ymk2 = "ymk2";
    private String ymk3 = "ymk3";
    private  String not = "true";

    private String urlString = null;

    public volatile boolean parsingComplete = true;
    public HandleJSON(String url){
        this.urlString = url;
    }
    public String getYmk1(){
        return ymk1;
    }
    public String getYmk2(){
        return ymk2;
    }
    public String getYmk3(){
        return ymk3;
    }
    public String getNot(){
        return not;
    }

    @SuppressLint("NewApi")
    public void readAndParseJSON(String in) {
        try {
            JSONObject reader = new JSONObject(in);

            JSONObject sys  = reader.getJSONObject("yemekler");
            ymk1 = sys.getString("ymk1");
            ymk2 = sys.getString("ymk2");
            ymk3 = sys.getString("ymk3");
            not = sys.getString("not");


            parsingComplete = false;


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void fetchJSON(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    // Starts the query
                    conn.connect();
                    InputStream stream = conn.getInputStream();

                    String data = convertStreamToString(stream);

                    readAndParseJSON(data);
                    stream.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        thread.start();
    }
    static String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}