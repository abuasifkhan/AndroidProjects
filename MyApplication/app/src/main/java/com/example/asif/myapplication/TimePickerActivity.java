package com.example.asif.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

/**
 * Created by asif on 23/02/16.
 */
public class TimePickerActivity extends Activity {
    private Button timeButton;
    private TimePicker timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gettime);
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        timeButton=(Button)findViewById(R.id.timeButton);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iData = new Intent();
                int hour, minute;
                String AM_PM;

                hour = timePicker.getCurrentHour();
                minute=timePicker.getCurrentMinute();
                hour = hour>12?(hour-12):hour;

                if(timePicker.getCurrentHour()>12)AM_PM="PM";
                else AM_PM="AM";
                iData.putExtra("hour",hour);
                iData.putExtra("minute",minute);
                iData.putExtra("AM_PM",AM_PM);
                setResult(Activity.RESULT_OK, iData);
                finish();
            }
        });

    }
}
