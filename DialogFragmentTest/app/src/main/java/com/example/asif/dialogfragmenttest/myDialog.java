package com.example.asif.dialogfragmenttest;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.zip.Inflater;

/**
 * Created by asif on 24/02/16.
 */
public class myDialog extends DialogFragment {
    Button button1, button2;
    Communacator communacator;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communacator = (Communacator) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setCancelable(false);
        View view = inflater.inflate(R.layout.dialogbox, container, false);
        button1 =(Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(),"Yes clicked", Toast.LENGTH_LONG).show();
                communacator.onDialogMessage("Yes Clicked");
                dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(),"No Clicked",Toast.LENGTH_LONG).show();
                communacator.onDialogMessage("No Clicked");
                dismiss();
            }
        });

        return view;
    }

    interface Communacator{
        public void onDialogMessage(String message);
    }

}
