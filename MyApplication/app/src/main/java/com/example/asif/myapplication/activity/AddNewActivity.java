package com.example.asif.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asif.myapplication.R;
import com.example.asif.myapplication.fragment.DatePickerFragment;
import com.example.asif.myapplication.fragment.TimePickerFragment;
import com.example.asif.myapplication.pojo.Task;

/**
 * Created by asif on 23/02/16.
 */
public class AddNewActivity extends Activity {
    public final int REQ_CODE_DATE = 37;
    public final int REQ_CODE_TIME = 38;
    EditText givenTitle, givenDescription;
    TextView givenTime, givenDate;
    Button okButton, cancelButton;
    String listName, title, description, date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnew);

        title = description = date = time = null;
        listName = getIntent().getExtras().getString("listName");

        givenTitle = (EditText) findViewById(R.id.titleInput);
        givenTime = (TextView) findViewById(R.id.timeInput);
        givenDescription = (EditText) findViewById(R.id.descriptionInput);
        givenDate = (TextView) findViewById(R.id.dateInput);
        okButton = (Button) findViewById(R.id.okButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        putData();

        givenTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddNewActivity.this, TimePickerFragment.class);
//                Calendar cal = Calendar.getInstance(TimeZone.getDefault());
//                i.putExtra("currHour",cal.get(Calendar.HOUR));
//                i.putExtra("currMin", cal.get(Calendar.MINUTE));
                startActivityForResult(i, REQ_CODE_TIME);
            }
        });

        givenDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddNewActivity.this, DatePickerFragment.class);
//                Calendar cal = Calendar.getInstance(TimeZone.getDefault());
//                i.putExtra("currMonth",cal.MONTH);
//                i.putExtra("currYear",cal.YEAR);
                startActivityForResult(i, REQ_CODE_DATE);
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title = givenTitle.getText().toString();
                description = givenDescription.getText().toString();
                date = givenDate.getText().toString();
                time = givenTime.getText().toString();
                if (title==null || description==null || date.equals("Pick Date") || time.equals("Pick Time")) {
                    Toast.makeText(getBaseContext(), "Fill up all the fields please.", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent iData = new Intent();
//                iData.putExtra("listName",listName);
//                iData.putExtra("title",title);
//                iData.putExtra("description",description);
//                iData.putExtra("date",date);
//                iData.putExtra("time", time);
                Task newTask = new Task(listName, title, description, date, time);
                iData.putExtra("newTask", newTask);
                //iData.putExtra("newTask", (Serializable)newTask);
                setResult(Activity.RESULT_OK, iData);
                finish();
            }
        });

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

    private void putData(){
        Intent i = getIntent();
        String title=i.getExtras().getString("titleName");
        String description = i.getExtras().getString("descriptionName");
        if(title!="") givenTitle.setText(title);
        if(description!="")givenDescription.setText(description);
    }
}
