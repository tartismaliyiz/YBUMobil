package io.smartface.MobileYBU;

import android.annotation.SuppressLint;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by YILDIZ on 06.08.2015.
 */
public class DuyuruWidgetJson {
    private ArrayList<JSONObject> sources = new ArrayList<JSONObject>();
    private ArrayList<String> intervalDescs = new ArrayList<String>();
    private ArrayList<Integer> intervalInts = new ArrayList<Integer>();
    private String urlString = null;
    public volatile boolean parsingComplete = true;
    public DuyuruWidgetJson(String url){
        this.urlString = url;
    }
    public ArrayList<JSONObject> getSources(){
        return sources;
    }
    public ArrayList<String> getIntervalDescs(){ return intervalDescs; }
    public ArrayList<Integer> getIntervalInts() { return intervalInts; }

    @SuppressLint("NewApi")
    public void readAndParseJSON(String in) {
        try {
            JSONArray allData = new JSONArray(in);
            JSONArray allSources = allData.getJSONArray(1);
            for(int i=0;i<allSources.length();i++){
                sources.add(allSources.getJSONObject(i));
            }
            JSONArray allIntervals = allData.getJSONArray(0);
            for(int i=0;i<allIntervals.length();i++){
                intervalDescs.add(allIntervals.getJSONObject(i).getString("desc"));
                intervalInts.add(Integer.parseInt(allIntervals.getJSONObject(i).getString("int")));
            }
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
