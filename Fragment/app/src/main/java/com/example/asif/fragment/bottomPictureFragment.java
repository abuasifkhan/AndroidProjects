package com.example.asif.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by asif on 17/02/16.
 */
public class bottomPictureFragment extends Fragment {
    private static TextView topText;
    private static TextView bottomText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buttom_picture_fragment, container);
        topText = (TextView) view.findViewById(R.id.topText);
        bottomText = (TextView) view.findViewById(R.id.bottomText);
        return view;
    }
    public void setMemeText(String top, String bottom){
        topText.setText(top);
        bottomText.setText(bottom);
    }
}
