package com.example.asif.myapplication;

import android.widget.DatePicker;

import java.io.Serializable;

/**
 * Created by asif on 25/02/16.
 */
public class Task implements Serializable{
    private long _id;
    private String listName, Title, Description, Date, Time;
    public Task(String ListName, String title, String description, String date, String time){
        listName = ListName;
        Title = title;
        Description = description;
        Date = date;
        Time = time;
    }
    public Task(int id, String ListName, String title, String description, String date, String time){
        listName = ListName;
        Title = title;
        Description = description;
        Date = date;
        Time = time;
        _id = id;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }
    public String getListName(){
        return listName;
    }
    public String getTitle(){
        return Title;
    }
    public String getDescription(){
        return Description;
    }
    public String getDate(){
        return Date;
    }
    public String getTime(){
        return Time;
    }
}
