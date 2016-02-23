package com.example.asif.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by asif on 23/02/16.
 */
public class addNew extends Activity{
    EditText givenTitle, givenDescription;
    TextView givenTime, givenDate;
    public final int REQ_CODE_DATE=37;
    public final int REQ_CODE_TIME=38;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnew);

        givenTitle=(EditText) findViewById(R.id.titleInput);
        givenDescription = (EditText) findViewById(R.id.descriptionInput);

        givenTime = (TextView) findViewById(R.id.timeInput);
        givenTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(addNew.this, getTime.class);
                startActivityForResult(i, REQ_CODE_TIME);
            }
        });

        givenDate = (TextView) findViewById(R.id.dateInput);
        givenDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(addNew.this, getDate.class);
                startActivityForResult(i, REQ_CODE_DATE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Input Day, Month, Year
        if(requestCode==REQ_CODE_DATE){
            if(resultCode==Activity.RESULT_OK){
                int day = data.getExtras().getInt("day");
                int month = data.getExtras().getInt("month");
                int year = data.getExtras().getInt("year");
                givenDate.setText(String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year) );

            }
        }

        // Input Hour, Minute
        if(requestCode==REQ_CODE_TIME){
            if(resultCode==Activity.RESULT_OK){
                int hour = data.getExtras().getInt("hour");
                int minute = data.getExtras().getInt("minute");
                String AM_PM = data.getExtras().getString("AM_PM");
                givenTime.setText(String.valueOf(hour)+":"+String.valueOf(minute)+" "+AM_PM);
            }
        }
    }
}
