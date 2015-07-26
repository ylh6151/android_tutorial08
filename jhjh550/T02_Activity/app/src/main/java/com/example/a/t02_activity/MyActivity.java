package com.example.a.t02_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by a on 2015-04-14.
 */
public class MyActivity extends ActionBarActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_test);

        Intent myIntent = getIntent();
        String strID = myIntent.getExtras().get("myID").toString();
        String strPW = myIntent.getExtras().get("myPW").toString();

        TextView textID = (TextView)findViewById(R.id.text1);
        TextView textPW = (TextView)findViewById(R.id.text2);

        textID.setText("id: "+strID);
        textPW.setText("pw: "+strPW);

        Button btnClose = (Button)findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("result", "my result");
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }


}

