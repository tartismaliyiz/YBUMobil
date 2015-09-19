package io.smartface.MobileYBU;

import android.annotation.SuppressLint;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by YILDIZ on 29.07.2015.
 */
public class AkademikTakvimJSON {
    private ArrayList<JSONArray> takvimler = new ArrayList<JSONArray>();
    private String urlString = null;
    public volatile boolean parsingComplete = true;
    public AkademikTakvimJSON(String url){
        this.urlString = url;
    }
    public ArrayList<JSONArray> getTakvimler(){
        return takvimler;
    }

    @SuppressLint("NewApi")
    public void readAndParseJSON(String in) {
        try {
            JSONObject tumTakvimler  = new JSONObject(in);
            takvimler.add(tumTakvimler.getJSONArray("graduated"));
            takvimler.add(tumTakvimler.getJSONArray("undergraduate"));
            takvimler.add(tumTakvimler.getJSONArray("ydyo"));
            takvimler.add(tumTakvimler.getJSONArray("medicine"));
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
