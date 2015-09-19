package io.smartface.MobileYBU;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.smartface.MobileYBU.R;


public class AboutMe extends Activity {
    Button link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_me);

        link = (Button)findViewById(R.id.ybuLink);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ybubiltek.org"));
                startActivity(browserIntent);
            }
        });
    }

}
