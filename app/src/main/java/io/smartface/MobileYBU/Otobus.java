package io.smartface.MobileYBU;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import io.smartface.MobileYBU.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Otobus extends Activity {
    final Context context = this;
    Button sellect;

    private OtobusJSON obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otobus);
        sellect = (Button) findViewById(R.id.otobusSec);


        listele();
        sellect.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.duraklar);
                dialog.setTitle("Durak Seç");
                dialog.show();
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                Window window = dialog.getWindow();
                lp.copyFrom(window.getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp);

                Button durak1 = (Button) dialog.findViewById(R.id.durak1);
                Button durak2 = (Button) dialog.findViewById(R.id.durak2);
                Button durak3 = (Button) dialog.findViewById(R.id.durak3);
                Button durak4 = (Button) dialog.findViewById(R.id.durak4);
                Button durak5 = (Button) dialog.findViewById(R.id.durak5);
                Button durak6 = (Button) dialog.findViewById(R.id.durak6);
                Button durak7 = (Button) dialog.findViewById(R.id.durak7);

                durak1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isOnline()){
                            obj = new OtobusJSON("http://api.ybubiltek.org/mobilybu/android/otobus.php?durak=0");
                            obj.fetchJSON();
                            while(obj.parsingComplete);
                            String otobusler = obj.getOtobus();
                            List<String> myList = new ArrayList<String>(Arrays.asList(otobusler.split("<-a->")));
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Otobus.this, R.layout.otobus_list_view, R.id.otobus, myList);
                            ListView list = (ListView) findViewById(R.id.listOtobus);
                            list.setAdapter(adapter);
                            dialog.dismiss();
                        }else{
                            Toast.makeText(getApplicationContext(), "İnternet bağlantısı sağlanamadı!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });

                durak2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isOnline()){
                            obj = new OtobusJSON("http://api.ybubiltek.org/mobilybu/android/otobus.php?durak=1");
                            obj.fetchJSON();
                            while(obj.parsingComplete);
                            String otobusler = obj.getOtobus();
                            List<String> myList = new ArrayList<String>(Arrays.asList(otobusler.split("<-a->")));
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Otobus.this, R.layout.otobus_list_view, R.id.otobus, myList);
                            ListView list = (ListView) findViewById(R.id.listOtobus);
                            list.setAdapter(adapter);
                            dialog.dismiss();
                        }else{
                            Toast.makeText(getApplicationContext(), "İnternet bağlantısı sağlanamadı!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });

                durak3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isOnline()){
                            obj = new OtobusJSON("http://api.ybubiltek.org/mobilybu/android/otobus.php?durak=2");
                            obj.fetchJSON();
                            while(obj.parsingComplete);
                            String otobusler = obj.getOtobus();
                            List<String> myList = new ArrayList<String>(Arrays.asList(otobusler.split("<-a->")));
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Otobus.this, R.layout.otobus_list_view, R.id.otobus, myList);
                            ListView list = (ListView) findViewById(R.id.listOtobus);
                            list.setAdapter(adapter);
                            dialog.dismiss();
                        }else{
                            Toast.makeText(getApplicationContext(), "İnternet bağlantısı sağlanamadı!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });

                durak4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isOnline()){
                            obj = new OtobusJSON("http://api.ybubiltek.org/mobilybu/android/otobus.php?durak=3");
                            obj.fetchJSON();
                            while(obj.parsingComplete);
                            String otobusler = obj.getOtobus();
                            List<String> myList = new ArrayList<String>(Arrays.asList(otobusler.split("<-a->")));
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Otobus.this, R.layout.otobus_list_view, R.id.otobus, myList);
                            ListView list = (ListView) findViewById(R.id.listOtobus);
                            list.setAdapter(adapter);
                            dialog.dismiss();
                        }else{
                            Toast.makeText(getApplicationContext(), "İnternet bağlantısı sağlanamadı!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });

                durak5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isOnline()){
                            obj = new OtobusJSON("http://api.ybubiltek.org/mobilybu/android/otobus.php?durak=4");
                            obj.fetchJSON();
                            while(obj.parsingComplete);
                            String otobusler = obj.getOtobus();
                            List<String> myList = new ArrayList<String>(Arrays.asList(otobusler.split("<-a->")));
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Otobus.this, R.layout.otobus_list_view, R.id.otobus, myList);
                            ListView list = (ListView) findViewById(R.id.listOtobus);
                            list.setAdapter(adapter);
                            dialog.dismiss();
                        }else{
                            Toast.makeText(getApplicationContext(), "İnternet bağlantısı sağlanamadı!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });

                durak6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isOnline()){
                            obj = new OtobusJSON("http://api.ybubiltek.org/mobilybu/android/otobus.php?durak=5");
                            obj.fetchJSON();
                            while(obj.parsingComplete);
                            String otobusler = obj.getOtobus();
                            List<String> myList = new ArrayList<String>(Arrays.asList(otobusler.split("<-a->")));
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Otobus.this, R.layout.otobus_list_view, R.id.otobus, myList);
                            ListView list = (ListView) findViewById(R.id.listOtobus);
                            list.setAdapter(adapter);
                            dialog.dismiss();
                        }else{
                            Toast.makeText(getApplicationContext(), "İnternet bağlantısı sağlanamadı!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });

                durak7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isOnline()){
                            obj = new OtobusJSON("http://api.ybubiltek.org/mobilybu/android/otobus.php?durak=6");
                            obj.fetchJSON();
                            while(obj.parsingComplete);
                            String otobusler = obj.getOtobus();
                            List<String> myList = new ArrayList<String>(Arrays.asList(otobusler.split("<-a->")));
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Otobus.this, R.layout.otobus_list_view, R.id.otobus, myList);
                            ListView list = (ListView) findViewById(R.id.listOtobus);
                            list.setAdapter(adapter);
                            dialog.dismiss();
                        }else{
                            Toast.makeText(getApplicationContext(), "İnternet bağlantısı sağlanamadı!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
            }


        });



    }



    private void listele() {
        String[] otobusler = {"Lütfen Durak Seçiniz"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Otobus.this, R.layout.otobus_list_view, R.id.otobus, otobusler);
        ListView list = (ListView) findViewById(R.id.listOtobus);
        list.setAdapter(adapter);
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}


