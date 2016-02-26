package com.example.asif.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Vector;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    dataBaseHandler myDatabase;
    private String MYLISTNAME;
    public static int REQ_FOR_ADD = 666;
    public static int REQ_FOR_UPDATE = 667;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MYLISTNAME = "all";
        myDatabase = new dataBaseHandler(this, null, null, 1);

        UpdateTaskList();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {                             // If Fab Button Clicked
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNewActivity.class);
                intent.putExtra("listName", MYLISTNAME);
                startActivityForResult(intent, REQ_FOR_ADD);
//                Toast.makeText(getApplicationContext(),"Going to AddNew", Toast.LENGTH_LONG);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void UpdateTaskList() {
        Vector<Task> taskList;
        taskList = myDatabase.databaseToTask();

        ListAdapter adapter = new CustomAdapter(this, taskList);
        ListView listView = (ListView) findViewById(R.id.taskListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {                 // When click on list
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: Do some works.
                Task nowTask = (Task) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, nowTask.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_FOR_ADD && resultCode == Activity.RESULT_OK) {   // RESULT FROM AddNewActivity Activity.
            Task newTask = (Task) data.getExtras().getSerializable("newTask");
            myDatabase.addTask(newTask);
            UpdateTaskList();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_all) {
            // TODO: Show all Tasks
            Toast.makeText(getBaseContext(),"Method not implemented yet.",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_calendar) {
            // TODO: Show all Calendar tasks. It'll be for later use.
//            Toast.makeText(getBaseContext(),"Method not implemented yet.",Toast.LENGTH_SHORT).show();
            calendarShowActivity calendar = new calendarShowActivity();
            calendar.show(getFragmentManager(),null);
        } else if (id == R.id.nav_edit) {
            // TODO: New activity which edits the Drawers group list
            Toast.makeText(getBaseContext(),"Method not implemented yet.",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_settings) {
            // TODO: Setting. It'll be for later.
            Toast.makeText(getBaseContext(),"Method not implemented yet.",Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

