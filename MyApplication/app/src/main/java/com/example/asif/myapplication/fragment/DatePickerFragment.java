package com.example.asif.myapplication.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.asif.myapplication.R;

/**
 * Created by asif on 23/02/16.
 */
public class DatePickerFragment extends Activity {
    DatePicker datePicker;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getdate);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        button = (Button) findViewById(R.id.dateButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iData = new Intent();
                int year = datePicker.getYear();
                int month = datePicker.getMonth() + 1;
                int day = datePicker.getDayOfMonth();
                Toast.makeText(getBaseContext(), "Button Clicked", Toast.LENGTH_LONG);
                iData.putExtra("year", year);
                iData.putExtra("month", month);
                iData.putExtra("day", day);
                setResult(Activity.RESULT_OK, iData);
                finish();
            }
        });
    }

}
