package com.jakebethune.smarthome.activity;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.RemoteViews;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jakebethune.smarthome.R;

/**
 * Implementation of App Widget functionality.
 */
public class SmartHomeWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, DeviceActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0 );
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.smart_home_widget);
            views.setOnClickPendingIntent(R.id.widget_home_button, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);



        }
    }
}

