package com.example.asif.dialogfragmenttest;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements myDialog.Communacator{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showDialog(View v){
        FragmentManager manager = getFragmentManager();
        myDialog md = new myDialog();
        md.show(manager,null);
    }

    @Override
    public void onDialogMessage(String message) {
        Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
    }
}
