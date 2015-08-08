package com.example.c.t21_fragment2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by c on 2015-08-08.
 */
public class MyCounterFragment extends Fragment {
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.couter_fragment, container, false);
        textView = (TextView)rootView.findViewById(R.id.textViewCounter);
        Button btnIncrease = (Button)rootView.findViewById(R.id.btnIncrease);
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(textView.getText().toString());
                number++;
                textView.setText(Integer.toString(number));
            }
        });
        return rootView;
    }
}
