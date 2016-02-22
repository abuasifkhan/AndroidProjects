package com.example.asif.threads;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class threadTest extends Activity {

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            TextView text = (TextView) findViewById(R.id.asifsTextView);
            if(text.getText().toString()=="Told you I'm gonna change. :D")
                text.setText("I'm gonna change soon. ;)");
            else
                text.setText("Told you I'm gonna change. :D");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_test);
        Button button = (Button) findViewById(R.id.asifsButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        handler.sendEmptyMessage(0);
//                        TextView text = (TextView) findViewById(R.id.asifsTextView);
                    }
                };
                Thread myThread = new Thread(runnable);
                myThread.start();

//                SystemClock.sleep(2000);
//                TextView text = (TextView) findViewById(R.id.asifsTextView);
//                if(text.getText().toString()=="Told you I'm gonna change. :D")
//                    text.setText("I'm gonna change soon. ;)");
//                else
//                    text.setText("Told you I'm gonna change. :D");
            }
        });
    }
}
