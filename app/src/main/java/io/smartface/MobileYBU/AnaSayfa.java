package io.smartface.MobileYBU;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;



public class AnaSayfa extends Activity {
    public static String PACKAGE_NAME;
    ImageButton yemekBtn;
    ImageButton otobusBtn;
    ImageButton radyoBtn;
    ImageButton ogrenciBtn;
    ImageButton olsunBtn;
    ImageButton aboutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.PACKAGE_NAME = getApplicationContext().getPackageName();

        setContentView(R.layout.activity_ana_sayfa);
        initialize();

        yemekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnaSayfa.this, Yemek.class));
            }
        });
        otobusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnaSayfa.this, Otobus.class));
            }
        });
        radyoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnaSayfa.this, Radyo.class));
            }
        });
        ogrenciBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://obs.ybu.edu.tr/oibs/ogrenci/login.aspx"));
                startActivity(browserIntent);
            }
        });
        olsunBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnaSayfa.this, AkademikTakvim.class));
            }
        });
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnaSayfa.this, AboutMe.class));
            }
        });

    }

    private void initialize() {
        yemekBtn = (ImageButton) findViewById(R.id.yemekBtn);
        radyoBtn = (ImageButton) findViewById(R.id.radyoBtn);
        ogrenciBtn = (ImageButton) findViewById(R.id.ogrenciBtn);
        otobusBtn = (ImageButton) findViewById(R.id.otobusBtn);
        olsunBtn = (ImageButton) findViewById(R.id.olsunBtn);
        aboutBtn = (ImageButton) findViewById(R.id.aboutBtn);
    }

    public static void log(String text){
        Log.i(AnaSayfa.PACKAGE_NAME,text);
    }

}
