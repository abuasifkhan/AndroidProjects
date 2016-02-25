package com.example.asif.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by asif on 23/02/16.
 */
public class AddNewActivity extends Activity {
    public final int REQ_CODE_DATE = 37;
    public final int REQ_CODE_TIME = 38;
    EditText givenTitle, givenDescription;
    TextView givenTime, givenDate;
    Button okButton, cancelButton;
    String listName, title, decription, date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnew);

        title = decription = date = time = null;
        listName = getIntent().getExtras().getString("listName");

        givenTitle = (EditText) findViewById(R.id.titleInput);
        givenDescription = (EditText) findViewById(R.id.descriptionInput);

        givenTime = (TextView) findViewById(R.id.timeInput);
        givenTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddNewActivity.this, TimePickerActivity.class);
//                Calendar cal = Calendar.getInstance(TimeZone.getDefault());
//                i.putExtra("currHour",cal.get(Calendar.HOUR));
//                i.putExtra("currMin", cal.get(Calendar.MINUTE));
                startActivityForResult(i, REQ_CODE_TIME);
            }
        });

        givenDate = (TextView) findViewById(R.id.dateInput);
        givenDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddNewActivity.this, DatePickerActivity.class);
//                Calendar cal = Calendar.getInstance(TimeZone.getDefault());
//                i.putExtra("currMonth",cal.MONTH);
//                i.putExtra("currYear",cal.YEAR);
                startActivityForResult(i, REQ_CODE_DATE);
            }
        });

        okButton = (Button) findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title = givenTitle.getText().toString();
                decription = givenDescription.getText().toString();
                if (title == null || decription == null || date == null || time == null) {
                    Toast.makeText(getBaseContext(), "Fill up all the fields please.", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent iData = new Intent();
//                iData.putExtra("listName",listName);
//                iData.putExtra("title",title);
//                iData.putExtra("description",decription);
//                iData.putExtra("date",date);
//                iData.putExtra("time", time);
                Task newTask = new Task(listName, title, decription, date, time);
                iData.putExtra("newTask", newTask);
                //iData.putExtra("newTask", (Serializable)newTask);
                setResult(Activity.RESULT_OK, iData);
                finish();
            }
        });

        cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Input Day, Month, Year
        if (requestCode == REQ_CODE_DATE) {
            if (resultCode == Activity.RESULT_OK) {
                int day = data.getExtras().getInt("day");
                int month = data.getExtras().getInt("month");
                int year = data.getExtras().getInt("year");
                givenDate.setText(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
                date = givenDate.getText().toString();
            }
        }

        // Input Hour, Minute
        if (requestCode == REQ_CODE_TIME) {
            if (resultCode == Activity.RESULT_OK) {
                int hour = data.getExtras().getInt("hour");
                int minute = data.getExtras().getInt("minute");
                String AM_PM = data.getExtras().getString("AM_PM");
                givenTime.setText(String.valueOf(hour) + ":" + String.valueOf(minute) + " " + AM_PM);
                time = givenTime.getText().toString();
            }
        }
    }
}
