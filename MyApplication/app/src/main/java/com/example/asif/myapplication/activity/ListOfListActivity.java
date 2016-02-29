package com.example.asif.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import com.example.asif.myapplication.storage.DataBaseHandler;
import android.os.Bundle;
import android.widget.ListView;

import com.example.asif.myapplication.R;
import com.example.asif.myapplication.storage.DataBaseHandler;

import java.util.Vector;

/**
 * Created by asif on 29/02/16.
 */
public class ListOfListActivity extends Activity {
    private DataBaseHandler db;
    private ListView listList;
    private Vector<String> allList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_list_layout);

        db = new DataBaseHandler(this,null,null,1);
        allList = new Vector<String>();
        allList.addAll(db.getListNames());
        listList = (ListView) findViewById(R.id.taskNameListView);
        
    }
}
