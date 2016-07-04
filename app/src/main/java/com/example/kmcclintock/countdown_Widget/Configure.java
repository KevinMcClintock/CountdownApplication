package com.example.kmcclintock.countdown_Widget;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by KMcClintock on 26/06/2016.
 * TODO in depth descriptions
 */
public class Configure extends Activity {

    private Configure context;
    private int widgetID;
    GetDateTime.dateTime dt = new GetDateTime.dateTime();

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
        //final TextView tv = (TextView)findViewById(R.id.textView);
        final Calendar today = Calendar.getInstance();
        DatePicker dp = (DatePicker)findViewById(R.id.datePicker);


        //Set all the date information
        dp.init(today.get(Calendar.YEAR)
                ,today.get(Calendar.MONTH)
                ,today.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker view,
                                              int year, int monthOfYear,int dayOfMonth) {
                        dt.setYear(year);
                        dt.setMonth(monthOfYear);
                        dt.setDay(dayOfMonth);
                    }});


        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){


                //DateFormat format = SimpleDateFormat.getTimeInstance(
                        //SimpleDateFormat.MEDIUM, Locale.getDefault());
                int day = dt.getDay();
                int month = dt.getMonth();
                int year = dt.getYear();
                int dateTimeFormat = day + month + year;
                CharSequence wantedDate = getString(dateTimeFormat);

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(wantedDate.toString()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(wantedDate.toString()));


                PendingIntent pending = PendingIntent.getActivity(context, 0, intent, 0);
                views.setOnClickPendingIntent(R.id.button, pending);
                widgetManager.updateAppWidget(widgetID, views);

                Intent resultValue = new Intent();
                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,widgetID);

                //Set the return result
                setResult(RESULT_OK,resultValue);



                views.setTextViewText(R.id.textView,wantedDate);


                //Close Activity
                finish();
            }

        });

    }
}
