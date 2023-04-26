package com.example.utspapb2;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class latitudeinfo extends AppWidgetProvider {

    public static final String preference = "com.example.utspapb2";
    public static final String LATITUDE_KEY = "latitudeKey";
    public static final String LONGITUDE_KEY = "longitudeKey";

    public static final String POI_NAME_KEY = "poinameKey";

    private static final String COUNT_KEY = "count";

    private BroadcastReceiver receiver;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        SharedPreferences prefs = context.getSharedPreferences(preference, 0);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.latitudeinfo);

        float latitudeVal = prefs.getFloat(LATITUDE_KEY,0);
        views.setTextViewText(R.id.POILatitudeValue, String.valueOf(latitudeVal));

        float longitudeVal = prefs.getFloat(LONGITUDE_KEY,0);
        views.setTextViewText(R.id.POILongitudeValue, String.valueOf(longitudeVal));

        String POIName = prefs.getString(POI_NAME_KEY, "Sekolah Vokasi UGM");
        views.setTextViewText(R.id.POINameValue, POIName);

        // membuat sebuah intent
        Intent intent = new Intent(context, latitudeinfo.class);
        // mengirim sebuah broadcast ketika widget update
        intent.setAction("android.intent.action.MY_SHARED_PREFERENCES_CHANGED");


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int appWidgetId : appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, appWidgetId);



        }
    }

    private void updateWidget(Context context) {
        // Retrieve the SharedPreferences value
        SharedPreferences prefs = context.getSharedPreferences(preference, Context.MODE_PRIVATE);

        // Update the widget
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.latitudeinfo);

        float latitudeVal = prefs.getFloat(LATITUDE_KEY,0);
        views.setTextViewText(R.id.POILatitudeValue, String.valueOf(latitudeVal));

        float longitudeVal = prefs.getFloat(LONGITUDE_KEY,0);
        views.setTextViewText(R.id.POILongitudeValue, String.valueOf(longitudeVal));

        String POIName = prefs.getString(POI_NAME_KEY, "Sekolah Vokasi UGM");
        views.setTextViewText(R.id.POINameValue, POIName);

        AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context, latitudeinfo.class), views);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        super.onEnabled(context);

        // Define the BroadcastReceiver to listen for changes in the shared preference
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                updateWidget(context);
            }
        };


        // Register the SharedPreferences receiver
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.MY_SHARED_PREFERENCES_CHANGED");
        context.getApplicationContext().registerReceiver(receiver, filter);
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
        super.onDisabled(context);

        // Unregister the SharedPreferences receiver
        context.getApplicationContext().unregisterReceiver(receiver);
    }
}