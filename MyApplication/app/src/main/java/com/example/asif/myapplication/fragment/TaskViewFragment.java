package com.example.asif.myapplication.fragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.asif.myapplication.R;
import com.example.asif.myapplication.pojo.Task;

/**
 * Created by asif on 28/02/16.
 */
public class TaskViewFragment extends DialogFragment {
    Communicator communicator;
    Button editButton;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator = (Communicator) activity;
    }
    public interface Communicator{
        public void onEditClick(boolean wannaEdit);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_view_layout,container,false);
        Task getTask =(Task) getArguments().getSerializable("newTask");

        TextView title,description,date, time;
        title = (TextView) view.findViewById(R.id.titleViewDetails);
        title.setText(getTask.getTitle());
        description=(TextView)view.findViewById(R.id.descriptionViewDetails);
        description.setText(getTask.getDescription());
        date = (TextView)view.findViewById(R.id.dateViewDetails);
        date.setText(getTask.getDate());
        time = (TextView)view.findViewById(R.id.timeViewDetails);
        time.setText(getTask.getTime());

        editButton = (Button) view.findViewById(R.id.onDetailsEditButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communicator.onEditClick(true);
                dismiss();
            }
        });

        return  view;
    }
}
