package com.example.asif.myapplication.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.asif.myapplication.pojo.Task;

import java.util.Vector;

/**
 * Created by asif on 25/02/16.
 */
public class DataBaseHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "task.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TASK_TABLE_NAME = "tasktable";
    public static final String LIST_TABLE_NAME = "listtablename";
    public static final String _ID = "id";
    public static final String LIST_NAME = "listname";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String DATE = "date";
    public static final String TIME = "time";

    public DataBaseHandler(Context context, String dataName, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + TASK_TABLE_NAME + " ( " + _ID + " integer primary key autoincrement, " + LIST_NAME
                        + " text, " + TITLE + " text, " + DESCRIPTION + " text, " + DATE + " text, " + TIME + " text);"
        );
        db.execSQL(
                "create table "+LIST_TABLE_NAME+" ("+_ID+" integer primary key autoincrement, "+LIST_NAME+" text)"+";"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TASK_TABLE_NAME);
        db.execSQL("drop table if exists "+LIST_TABLE_NAME);
        onCreate(db);
    }

    public void addTask(Task task) {
        ContentValues values = new ContentValues();
        values.put(LIST_NAME, task.getListName());
        values.put(TITLE, task.getTitle());
        values.put(DESCRIPTION, task.getDescription());
        values.put(DATE, task.getDate());
        values.put(TIME, task.getTime());

        SQLiteDatabase db = getWritableDatabase();
        long id = db.insertOrThrow(TASK_TABLE_NAME, null, values);
        db.close();
        task.set_id(id);
    }

    public void deleteTask(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from " + TASK_TABLE_NAME + " where " + _ID + "=" + id);
    }

    public void deleteList(String list) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from " + TASK_TABLE_NAME + " where " + LIST_NAME + "=\'" + list + "\'");
    }

    public void updateById(long id, Task task){
        String title = task.getTitle();
        String description = task.getDescription();
        String date = task.getDate();
        String time = task.getTime();

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(
                "UPDATE "+ TASK_TABLE_NAME +" SET "+TITLE+" = \'"+title+"\', "+DESCRIPTION+" = \'"+description+"\', "
                        +DATE+" = \'"+date+"\', "+TIME+" = \'"+time+"\' where "+_ID+"="+id
        );
    }

    public Vector<Task> databaseToTask() {       // Function to return the database as Task
        Vector<Task> dbTask = new Vector<Task>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TASK_TABLE_NAME + " ORDER BY "+TITLE+" ASC"+
                ";";

        // Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        // Move to first row in your results
        c.moveToFirst();
        if (c.getCount() > 0)
            do {
                if (c.getString(c.getColumnIndex(_ID)) != null) {
                    long id = c.getInt(c.getColumnIndex(_ID));
                    String ln = c.getString(c.getColumnIndex(LIST_NAME));
                    String title = c.getString(c.getColumnIndex(TITLE));
                    String dec = c.getString(c.getColumnIndex(DESCRIPTION));
                    String dt = c.getString(c.getColumnIndex(DATE));
                    String tm = c.getString(c.getColumnIndex(TIME));
                    dbTask.add(new Task(id, ln, title, dec, dt, tm));
                }
            } while (c.moveToNext());
        db.close();

        return dbTask;
    }

    public Vector<Task> databaseToTask(String listName){
        Vector<Task> dbTask = new Vector<Task>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TASK_TABLE_NAME + "WHERE "+LIST_NAME+" = \'"+listName+"\'"+ " ORDERED BY "+TITLE+" ASC"+
                ";";

        // Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        db.close();
        // Move to first row in your results
        c.moveToFirst();
        if (c.getCount() > 0)
            do {
                if (c.getString(c.getColumnIndex(_ID)) != null) {
                    long id = c.getInt(c.getColumnIndex(_ID));
                    String ln = c.getString(c.getColumnIndex(LIST_NAME));
                    String title = c.getString(c.getColumnIndex(TITLE));
                    String dec = c.getString(c.getColumnIndex(DESCRIPTION));
                    String dt = c.getString(c.getColumnIndex(DATE));
                    String tm = c.getString(c.getColumnIndex(TIME));
                    dbTask.add(new Task(id, ln, title, dec, dt, tm));
                }
            } while (c.moveToNext());

        return dbTask;
    }

    public Vector<String> getListNames(){
        Vector<String>allLists=new Vector<String>();
        allLists.add("all");
        allLists.add("temp");
        allLists.add("habijabi");
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT DISTINCT "+LIST_NAME+" FROM "+ LIST_TABLE_NAME +";";

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        if(c.getCount()>0){
            do{
                if(c.getString(c.getColumnIndex(_ID))!=null){
                    String ln = c.getString(c.getColumnIndex(LIST_NAME));
                    allLists.add(ln);
                }
            }while(c.moveToNext());
        }
        db.close();
        c.close();
        return allLists;
    }
}
