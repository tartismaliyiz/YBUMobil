package io.smartface.MobileYBU;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.smartface.MobileYBU.R;


public class AkademikTakvim extends Activity {
    Button takvimBtn;
    ListView takvimList;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akademik);

        takvimBtn = (Button) findViewById(R.id.takvimBtn);

        final String[] akademik = {"Akademik Takvim", "Lütfen Takvimi Seçiniz"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AkademikTakvim.this, R.layout.takvim_list_view, R.id.tvAkademik, akademik);
        ListView list = (ListView) findViewById(R.id.takvimList);
        list.setAdapter(adapter);


        takvimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AkademikTakvimJSON takvimJson = new AkademikTakvimJSON("http://api.ybubiltek.org/mobilybu/android/takvim.php");
                takvimJson.fetchJSON();
                while(takvimJson.parsingComplete);
                final ArrayList<JSONArray> takvimler = takvimJson.getTakvimler();

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.takvimler);
                dialog.setTitle("Takvimi Seçiniz");
                dialog.show();
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                Window window = dialog.getWindow();
                lp.copyFrom(window.getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp);

                Button btn1 = (Button) dialog.findViewById(R.id.takvim1);
                Button btn2 = (Button) dialog.findViewById(R.id.takvim2);
                Button btn3 = (Button) dialog.findViewById(R.id.takvim3);
                Button btn4 = (Button) dialog.findViewById(R.id.takvim4);

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<Spanned> volatileList = new ArrayList<Spanned>();
                        for(int i=0;i<takvimler.get(0).length();i++){
                            try {
                                volatileList.add(Html.fromHtml(takvimler.get(0).getString(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ArrayAdapter<Spanned> adapter = new ArrayAdapter<Spanned>(
                                AkademikTakvim.this,
                                R.layout.takvim_list_view, R.id.tvAkademik,
                                volatileList.toArray(new Spanned[volatileList.size()]));
                        ListView list = (ListView) findViewById(R.id.takvimList);
                        list.setAdapter(adapter);
                        dialog.dismiss();
                    }
                });

                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<Spanned> volatileList = new ArrayList<Spanned>();
                        for(int i=0;i<takvimler.get(1).length();i++){
                            try {
                                volatileList.add(Html.fromHtml(takvimler.get(1).getString(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ArrayAdapter<Spanned> adapter = new ArrayAdapter<Spanned>(
                                AkademikTakvim.this,
                                R.layout.takvim_list_view, R.id.tvAkademik,
                                volatileList.toArray(new Spanned[volatileList.size()]));
                        ListView list = (ListView) findViewById(R.id.takvimList);
                        list.setAdapter(adapter);
                        dialog.dismiss();
                    }
                });

                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<Spanned> volatileList = new ArrayList<Spanned>();
                        for(int i=0;i<takvimler.get(2).length();i++){
                            try {
                                volatileList.add(Html.fromHtml(takvimler.get(2).getString(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ArrayAdapter<Spanned> adapter = new ArrayAdapter<Spanned>(
                                AkademikTakvim.this,
                                R.layout.takvim_list_view, R.id.tvAkademik,
                                volatileList.toArray(new Spanned[volatileList.size()]));
                        ListView list = (ListView) findViewById(R.id.takvimList);
                        list.setAdapter(adapter);
                        dialog.dismiss();
                    }
                });

                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<Spanned> volatileList = new ArrayList<Spanned>();
                        for(int i=0;i<takvimler.get(3).length();i++){
                            try {
                                volatileList.add(Html.fromHtml(takvimler.get(3).getString(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ArrayAdapter<Spanned> adapter = new ArrayAdapter<Spanned>(
                                AkademikTakvim.this,
                                R.layout.takvim_list_view, R.id.tvAkademik,
                                volatileList.toArray(new Spanned[volatileList.size()]));
                        ListView list = (ListView) findViewById(R.id.takvimList);
                        list.setAdapter(adapter);
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}
