package com.example.asif.myapplication.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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

import com.example.asif.myapplication.R;
import com.example.asif.myapplication.adapter.CustomAdapter;
import com.example.asif.myapplication.fragment.TaskOptionDialogFragment;
import com.example.asif.myapplication.fragment.TaskViewFragment;
import com.example.asif.myapplication.pojo.Task;
import com.example.asif.myapplication.storage.DataBaseHandler;

import java.util.Vector;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TaskOptionDialogFragment.Communicator, TaskViewFragment.Communicator {
    DataBaseHandler myDatabase;
    private String CURRENT_LIST_NAME;
    private Task updateOrDelete;
    public static int REQ_FOR_ADD = 666;
    public static int REQ_FOR_UPDATE = 667;
    public static int REQ_FOR_DELETE = 668;
    private Vector<String> allList;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDatabase = new DataBaseHandler(this, null, null, 1);
        allList = new Vector<String>();
        allList.addAll(myDatabase.getListNames());
//        System.out.println("size: "+allList.size());
        CURRENT_LIST_NAME = allList.elementAt(0);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        menu = navigationView.getMenu();
//
        for(int i=0;i<allList.size();i++){
            String tmp = allList.elementAt(i);
            menu.add(R.id.main_group,Menu.NONE,i,tmp);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        UpdateTaskList(CURRENT_LIST_NAME);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {                                         // If Fab Button Clicked
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNewActivity.class);
                intent.putExtra("listName", CURRENT_LIST_NAME);
                intent.putExtra("titleName","");
                intent.putExtra("descriptionName","");
                startActivityForResult(intent, REQ_FOR_ADD);
//                Toast.makeText(getApplicationContext(),"Going to AddNew", Toast.LENGTH_LONG);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onDeleteClick(int whatToDo) {
        if(whatToDo==0){                                                                            // Delete Option Clicked.
            myDatabase.deleteTask(updateOrDelete.get_id());
            UpdateTaskList(CURRENT_LIST_NAME);
            Snackbar.make(drawerLayout,"Task Deleted!",Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        myDatabase.addTask(updateOrDelete);
                        UpdateTaskList(CURRENT_LIST_NAME);
                    }catch (NullPointerException e){
                        System.out.println("NullPointerException caught!");
                    }
                }
            }).show();
        }
        else if(whatToDo==1){                                               // Edit Option Clicked.
            Intent intent = new Intent(MainActivity.this, AddNewActivity.class);
            intent.putExtra("listName", CURRENT_LIST_NAME);
            intent.putExtra("titleName", updateOrDelete.getTitle());
            intent.putExtra("descriptionName", updateOrDelete.getDescription());
            startActivityForResult(intent, REQ_FOR_UPDATE);
        }
    }

    @Override
    public void onEditClick(boolean wannaEdit) {                                                    // Method from TaskViewActivity for editing a task.
        // TODO: Edit Option.
        Intent intent = new Intent(MainActivity.this, AddNewActivity.class);
        intent.putExtra("listName", CURRENT_LIST_NAME);
        intent.putExtra("titleName", updateOrDelete.getTitle());
        intent.putExtra("descriptionName", updateOrDelete.getDescription());
        startActivityForResult(intent, REQ_FOR_UPDATE);
    }

    private void UpdateTaskList(String listname) {
        final Vector<Task> taskList;
        if(listname.equals(CURRENT_LIST_NAME))
            taskList = myDatabase.databaseToTask();
        else taskList = myDatabase.databaseToTask(listname);

        ListAdapter adapter = new CustomAdapter(this, taskList);
        ListView listView = (ListView) findViewById(R.id.taskListView);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {                     // When click on listView
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: Do some works.
                updateOrDelete = (Task) parent.getItemAtPosition(position);
                FragmentManager manager = getFragmentManager();
                TaskViewFragment taskView = new TaskViewFragment();
                Bundle args = new Bundle();
                args.putSerializable("newTask",updateOrDelete);
                taskView.setArguments(args);
                taskView.show(manager,null);
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {             // When Click on LongClick on ListView
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                updateOrDelete = (Task) parent.getItemAtPosition(position);

                FragmentManager manager = getFragmentManager();
                TaskOptionDialogFragment longclick = new TaskOptionDialogFragment();
                longclick.show(manager,null);
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_FOR_ADD && resultCode == Activity.RESULT_OK) {                       // RESULT FROM AddNewActivity Activity for Adding Task.
            final Task newTask = (Task) data.getExtras().getSerializable("newTask");
            myDatabase.addTask(newTask);
            UpdateTaskList(CURRENT_LIST_NAME);

            Snackbar.make(drawerLayout,"Task added!",Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        myDatabase.deleteTask(newTask.get_id());
                        UpdateTaskList(CURRENT_LIST_NAME);
                    }catch (NullPointerException e){
                        System.out.println("Null Pointer Caught!");
                    }
                }
            }).show();
        }
        else if(requestCode==REQ_FOR_UPDATE && resultCode==Activity.RESULT_OK){                     // RESULT from AddNewActivity Activity for Updating a Task.
            Task newTask = (Task) data.getExtras().getSerializable("newTask");
            myDatabase.updateById(updateOrDelete.get_id(), newTask);
            UpdateTaskList(CURRENT_LIST_NAME);
            Snackbar.make(drawerLayout,"Task Updated!",Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        myDatabase.updateById(updateOrDelete.get_id(),updateOrDelete);
                        UpdateTaskList(CURRENT_LIST_NAME);
                    }catch(NullPointerException e) {
                        System.out.println("Null Pointer Caught!");
                    }
                }
            }).show();
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
            Snackbar.make(drawerLayout,"Method not Implemented Yet. Coming Soon.",Snackbar.LENGTH_SHORT).show();
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
//            Toast.makeText(getBaseContext(),"Method not implemented yet.",Toast.LENGTH_SHORT).show();
            UpdateTaskList(CURRENT_LIST_NAME);
//            Snackbar.make(drawerLayout,"Method not Implemented Yet. Coming Soon.",Snackbar.LENGTH_SHORT).show();

        } else if (id == R.id.nav_calendar) {
            // TODO: Show all Calendar tasks. It'll be for later use.
//            Toast.makeText(getBaseContext(),"Method not implemented yet.",Toast.LENGTH_SHORT).show();
            Snackbar.make(drawerLayout,"Method not Implemented Yet. Coming Soon.",Snackbar.LENGTH_SHORT).show();
        } else if (id == R.id.nav_edit) {
            // TODO: New activity which edits the Drawers group list
            Snackbar.make(drawerLayout, "Method not Implemented Yet. Coming Soon.", Snackbar.LENGTH_SHORT).show();

        } else if (id == R.id.nav_settings) {
            // TODO: Setting. It'll be for later.
            Snackbar.make(drawerLayout, "Method not Implemented Yet. Coming Soon.", Snackbar.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

