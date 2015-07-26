package com.example.a.t25_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by a on 2015-04-20.
 */
public class MyFragment extends Fragment {

    TextView textViewCounter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.counter_fragment, container, false);

        textViewCounter = (TextView)root.findViewById(R.id.textViewCount);
        Button btnIncrease = (Button)root.findViewById(R.id.btnIncrease);
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = textViewCounter.getText().toString();
                int count = Integer.parseInt(str);
                count++;
                textViewCounter.setText(""+count);
            }
        });

        return root;
    }
}
