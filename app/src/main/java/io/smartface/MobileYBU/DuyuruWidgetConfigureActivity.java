package io.smartface.MobileYBU;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;


/**
 * The configuration screen for the {@link DuyuruWidget DuyuruWidget} AppWidget.
 */
public class DuyuruWidgetConfigureActivity extends Activity {

    int widgetID = AppWidgetManager.INVALID_APPWIDGET_ID;
    ListView widgetConfigureList;
    int seconds;
    DuyuruWidgetConfigureListAdapter adapter;
    private static final String PREFS_NAME = "io.smartface.MobileYBU.DuyuruWidget";
    private static final String PREF_PREFIX_KEY = "ybumobil_";
    private Spinner intervalSpinner;

    public DuyuruWidgetConfigureActivity() {
        super();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED);

        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            widgetID = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (widgetID == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }

        setContentView(R.layout.duyuru_widget_configure);

        widgetConfigureList = (ListView) findViewById(R.id.widgetSourceList);
        final DuyuruWidgetJson jsonData = new DuyuruWidgetJson("http://api.ybubiltek.org/mobilybu/android/duyuruWidget.php");
        jsonData.fetchJSON();
        while(jsonData.parsingComplete);
        String[] preCheck = loadTitlePref(DuyuruWidgetConfigureActivity.this, widgetID).split("|||")[1].split(";;;");
        this.adapter = new DuyuruWidgetConfigureListAdapter(
                getApplicationContext(),
                R.layout.widget_source_list_row,
                jsonData.getSources(),
                preCheck
        );
        widgetConfigureList.setAdapter(this.adapter);

        findViewById(R.id.add_button).setOnClickListener(mOnClickListener);

        intervalSpinner = (Spinner)findViewById(R.id.updateIntervals);
        //jsonData.getIntervalDescs().toArray(new String[jsonData.getIntervalDescs().size()])
        intervalSpinner.setAdapter(
                new DuyuruWidgetSpinnerListAdapter(
                        getApplicationContext(),
                        R.layout.update_intervals_spinner_row,
                        R.id.intervalDesc,
                        jsonData.getIntervalDescs()
                )
        );
        intervalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                seconds = jsonData.getIntervalInts().get(i);
                AnaSayfa.log("Seconds changed: "+seconds);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            final Context context = DuyuruWidgetConfigureActivity.this;

            // When the button is clicked, store the string locally
            String widgetText = "";
            if(adapter != null){
                for(String sourceLink : adapter.getCheckedItems())
                    widgetText += sourceLink + ";;;";
            }
            saveTitlePref(context, widgetID, seconds+"|||"+widgetText);
            // It is the responsibility of the configuration activity to update the app widget
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            DuyuruWidget.updateAppWidget(context, appWidgetManager, widgetID);
            //Create and launch the AlarmManager.
            //N.B.:
            //Use a different action than the first update to have more reliable results.
            //Use explicit intents to have more reliable results.
            Uri uri = new Uri.Builder().appendPath(""+widgetID).build();
            Intent intentUpdate = new Intent(context, DuyuruWidget.class);
            intentUpdate.setAction(DuyuruWidget.UPDATE_ONE);//Set an action anyway to filter it in onReceive()
            intentUpdate.setData(uri);//One Alarm per instance.
            //We will need the exact instance to identify the intent.
            DuyuruWidget.addUri(widgetID, uri);
            intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
            PendingIntent pendingIntentAlarm = PendingIntent.getBroadcast(DuyuruWidgetConfigureActivity.this,
                    0,
                    intentUpdate,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            //If you want one global AlarmManager for all instances, put this alarmManger as
            //static and create it only the first time.
            //Then pass in the Intent all the ids and do not put the Uri.
            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis()+(seconds*1000),
                    (seconds*1000),
                    pendingIntentAlarm);
            Log.d(AnaSayfa.PACKAGE_NAME, "Created Alarm. Action = " + DuyuruWidget.UPDATE_ONE +
                    " URI = " + uri.toString() +
                    " Seconds = " + seconds);
            // Make sure we pass back the original appWidgetId
            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
            setResult(RESULT_OK, resultValue);
            finish();
        }
    };

    // Write the prefix to the SharedPreferences object for this widget
    static void saveTitlePref(Context context, int appWidgetId, String text) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putString(PREF_PREFIX_KEY + appWidgetId, text);
        prefs.commit();
    }

    // Read the prefix from the SharedPreferences object for this widget.
    // If there is no preference saved, get the default from a resource
    static String loadTitlePref(Context context, int appWidgetId) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        String titleValue = prefs.getString(PREF_PREFIX_KEY + appWidgetId, null);
        if (titleValue != null) {
            return titleValue;
        } else {
            return context.getString(R.string.appwidget_text);
        }
    }

    static void deleteTitlePref(Context context, int appWidgetId) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.remove(PREF_PREFIX_KEY + appWidgetId);
        prefs.commit();
    }
}

