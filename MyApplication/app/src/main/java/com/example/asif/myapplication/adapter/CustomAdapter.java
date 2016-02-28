package com.example.asif.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asif.myapplication.R;
import com.example.asif.myapplication.pojo.Task;

import java.util.Vector;

/**
 * Created by asif on 25/02/16.
 */
public class CustomAdapter extends ArrayAdapter<Task> {
    public CustomAdapter(Context context, Vector<Task> taskList) {
        super(context, R.layout.custom_list_view, taskList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_list_view, parent, false);
        Task item = getItem(position);
        TextView titleView = (TextView) customView.findViewById(R.id.TitleTextView);
        TextView descriptionView = (TextView) customView.findViewById(R.id.descriptionTextView);
        titleView.setText(item.getTitle());
        descriptionView.setText(item.getDescription());
        return customView;
    }
}
