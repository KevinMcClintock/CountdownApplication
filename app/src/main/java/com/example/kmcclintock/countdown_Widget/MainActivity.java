package com.example.kmcclintock.countdown_Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppWidgetProvider {

    //GetDateTime.dateTime dt = new GetDateTime.dateTime();

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        //DateFormat format = SimpleDateFormat.getTimeInstance(
                //SimpleDateFormat.MEDIUM, Locale.getDefault());
        //int dateTime = dt.getDay() + dt.getMonth() + dt.getYear();
       // CharSequence dateTimeFormat = format.format(dateTime);

        for (int i = 0; i < appWidgetIds.length; i++) {
            int currentWidgetId = appWidgetIds[i];
            String url = "http://www.tutorialspoint.com";


            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse(url));



            PendingIntent pending = PendingIntent.getActivity(context, 0, intent, 0);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_main);
            //views.setTextViewText(R.id.textView,dateTimeFormat);



            views.setOnClickPendingIntent(R.id.button, pending);
            appWidgetManager.updateAppWidget(currentWidgetId, views);
            Toast.makeText(context, "widget added", Toast.LENGTH_SHORT).show();
        }
    }
}