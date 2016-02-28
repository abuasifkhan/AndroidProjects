package com.example.asif.myapplication;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by abuas on 27-Feb-16.
 */
public class longClickActivity extends DialogFragment {
    Communicator communicator;
    Button deleteButton,editButton;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator = (Communicator) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Option");
        View view = inflater.inflate(R.layout.long_click_optionbar,container,false);

        deleteButton = (Button) view.findViewById(R.id.deleteOptionButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communicator.onDeleteClick(0);
                dismiss();
            }
        });

        editButton = (Button) view.findViewById(R.id.onOptionEditButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communicator.onDeleteClick(1);
                dismiss();
            }
        });

        return  view;
    }

    interface Communicator{
        public void onDeleteClick(int wannaDelete);
    }
}
