package com.example.c.t25_style;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView selectedTextView, workingTextView;
    Button button1, button2, buttonEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedTextView = (TextView) findViewById(R.id.selectedTextView);
        workingTextView = (TextView) findViewById(R.id.workingTextView);
       /* button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        buttonEnter = (Button) findViewById(R.id.enterButton);
*/
        View.OnClickListener numberButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String str = workingTextView.getText().toString();
                if(str.equals("0")){
                    str = b.getText().toString();
                }else{
                    str += b.getText().toString();
                }
                workingTextView.setText(str);
            }
        };
        /*
        button1.setOnClickListener(numberButtonListener);
        button2.setOnClickListener(numberButtonListener);
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTextView.setText(workingTextview.getText().toString());

            }
        });*/

        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        int number = 1;
        for(int i = 2; i<tableLayout.getChildCount() -1; i++){
            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
            for(int k = 0; k <tableRow.getChildCount(); k++){
                Button button = (Button) tableRow.getChildAt(k);
                button.setText(""+ number);
                button.setOnClickListener(numberButtonListener);
                number++;
            }

            int bottomCount = tableLayout.getChildCount();
            TableRow bottomRow = (TableRow) tableLayout.getChildAt(bottomCount - 1);

            Button deleteButton = (Button) bottomRow.getChildAt(0);
            deleteButton.setText("지움");
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    workingTextView.setText("0");
                }
            });
            Button zeroButton = (Button) bottomRow.getChildAt(1);
            zeroButton.setText("0");
            zeroButton.setOnClickListener(numberButtonListener);

            Button enterButton = (Button) bottomRow.getChildAt(2);
            enterButton.setText("확인");
            enterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedTextView.setText(workingTextView.getText().toString());
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
