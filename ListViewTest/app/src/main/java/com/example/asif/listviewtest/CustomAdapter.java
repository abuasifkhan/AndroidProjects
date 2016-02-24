package com.example.asif.listviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by asif on 24/02/16.
 */
public class CustomAdapter extends ArrayAdapter<String> {
    public CustomAdapter(Context context, String[] animals) {
        super(context, R.layout.custom_list_view,animals);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_list_view, parent, false);
        String item = getItem(position);
        TextView textView =(TextView) customView.findViewById(R.id.textView);
        ImageView imageView = (ImageView) customView.findViewById(R.id.imageView);
        textView.setText(item);
        imageView.setImageResource(R.drawable.me);
        return customView;
    }
}
