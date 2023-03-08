package com.example.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import java.text.DateFormat;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    //variable untuk key shared preference
    private static final String mSharedPrefile = "com.example.android.appwidget";
    private static final String COUNT_KEY = "count";

    // fungsi ini dipanggil saat appwidget update pada waktu tertentu
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        // mengambil nilai dari sharedpreferences
        SharedPreferences prefs = context.getSharedPreferences(mSharedPrefile, 0);
        int count = prefs.getInt(COUNT_KEY+appWidgetId, 0);
        count++;
        // mengambil date dalam bentuk string saat method dipanggil
        String dateString = DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date());

        // membuat sebuah remoteviews objek yang akan menampilkan view di dalam sebuah layout file
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        // ubah text dalam widget
        views.setTextViewText(R.id.appwidget_id, String.valueOf(appWidgetId));
        views.setTextViewText(R.id.appwidget_update, dateString);
        views.setTextViewText(R.id.appwidget_count, String.valueOf(count));

        // membuat sebuah intent
        Intent intent = new Intent(context, NewAppWidget.class);
        // mengirim sebuah broadcast ketika widget update
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        // membuat sebuah array kosong sebesar jumlah id widget yang dibuat
        int[] idArray = new int[] {appWidgetId};
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray);

        // mengambil broadcast saat terdapat  broadcast
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, appWidgetId, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        views.setOnClickPendingIntent(R.id.button_update, pendingIntent);

        // memasuki edit mode untuk mengubah nilai pada sharedprefs
        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor.putInt(COUNT_KEY+appWidgetId, count);
        prefEditor.apply();

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
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
}