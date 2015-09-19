package io.smartface.MobileYBU;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import java.util.Calendar;
import java.util.HashMap;


/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link DuyuruWidgetConfigureActivity DuyuruWidgetConfigureActivity}
 */
public class DuyuruWidget extends AppWidgetProvider {
    public static final String UPDATE_ONE = AnaSayfa.PACKAGE_NAME+".UPDATE_ONE_WIDGET";
    private static HashMap<Integer, Uri> uris = new HashMap<Integer, Uri>();

    @Override
    public void onReceive(Context context,
                          Intent intent)
    {
        String action = intent.getAction();
        if(action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE) ||
                action.equals(UPDATE_ONE))
        {
            //Check if there is a single widget ID.
            int widgetID = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            //If there is no single ID, call the super implementation.
            if(widgetID == AppWidgetManager.INVALID_APPWIDGET_ID)
                super.onReceive(context, intent);
                //Otherwise call our onUpdate() passing a one element array, with the retrieved ID.
            else
                this.onUpdate(context, AppWidgetManager.getInstance(context), new int[]{widgetID});
        }
        else
            super.onReceive(context, intent);
    }
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            DuyuruWidgetConfigureActivity.deleteTitlePref(context, appWidgetIds[i]);
            cancelAlarmManager(context, appWidgetIds[i]);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        String widgetText = DuyuruWidgetConfigureActivity.loadTitlePref(context, appWidgetId);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.duyuru_widget);
        Calendar c = Calendar.getInstance();
        views.setTextViewText(R.id.appwidget_text, c.get(c.HOUR)+":"+c.get(c.MINUTE)+":"+c.get(c.SECOND));
        AnaSayfa.log("Updated at: " + c.get(c.HOUR) + ":" + c.get(c.MINUTE) + ":" + c.get(c.SECOND));
        AnaSayfa.log("WidgetText: "+widgetText.split("|||")[1]);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
    protected void cancelAlarmManager(Context context, int widgetID)
    {
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intentUpdate = new Intent(context, DuyuruWidget.class);
        //AlarmManager are identified with Intent's Action and Uri.
        intentUpdate.setAction(DuyuruWidget.UPDATE_ONE);
        //Don't put the uri to cancel all the AlarmManager with action UPDATE_ONE.
        intentUpdate.setData(uris.get(widgetID));
        intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pendingIntentAlarm = PendingIntent.getBroadcast(context,
                0,
                intentUpdate,
                PendingIntent.FLAG_UPDATE_CURRENT);
        alarm.cancel(pendingIntentAlarm);
        uris.remove(widgetID);
    }

    public static void addUri(int id, Uri uri)
    {
        uris.put(new Integer(id), uri);
    }
}

