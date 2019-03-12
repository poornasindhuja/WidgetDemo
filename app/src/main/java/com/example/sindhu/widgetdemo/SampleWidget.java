package com.example.sindhu.widgetdemo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

public class SampleWidget extends AppWidgetProvider {
    SharedPreferences sp;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        for (int widgetId:appWidgetIds){
            sp=context.getSharedPreferences(context.getPackageName(),Context.MODE_PRIVATE);
            String s=sp.getString("fruit","No fruit selected");
            Intent intent=new Intent(context,MainActivity.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(context,
                    2,intent,0);
            RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.widget_layout);
            views.setTextViewText(R.id.wid_text,s);
            views.setOnClickPendingIntent(R.id.wid_text,pendingIntent);
            appWidgetManager.updateAppWidget(widgetId,views);
        }
    }
}
