package com.example.kmcclintock.countdown_Widget;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

/**
 * Created by KMcClintock on 26/06/2016.
 * TODO in depth descriptions
 */
public class Configure extends Activity {

    private Configure context;
    private int widgetID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configure);
        setResult(RESULT_CANCELED);
        context = this;

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            widgetID = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        //Create views
        final AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);
        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_main);

        Button b = (Button)findViewById(R.id.launch);
        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //intent.setData(Uri.parse(url));

                PendingIntent pending = PendingIntent.getActivity(context, 0, intent, 0);
                views.setOnClickPendingIntent(R.id.button, pending);
                widgetManager.updateAppWidget(widgetID, views);

                Intent resultValue = new Intent();
                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,widgetID);
                //Set the return result
                setResult(RESULT_OK,resultValue);

                //Close Activity
                finish();
            }

        });

    }
}
