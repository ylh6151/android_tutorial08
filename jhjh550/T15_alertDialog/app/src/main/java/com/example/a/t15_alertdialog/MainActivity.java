package com.example.a.t15_alertdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private static final int DIALOG_TEST = 1;
    private static final int DIALOG_CUSTOM = 2;

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case DIALOG_TEST:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("종료 확인");
                builder.setMessage("어플리케이션을 종료하시겠습니까?");
                builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Destroy canceled", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog dialog = builder.create();
                return dialog;

            case DIALOG_CUSTOM:
                Dialog customDialog = new Dialog(MainActivity.this);
                customDialog.setContentView(R.layout.custom_dialog);
                TextView customText = (TextView)customDialog.findViewById(R.id.customText);
                customText.setText("Hello world");
                return customDialog;
        }

        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.btnAlertDialog);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_TEST);
            }
        });

        Button btnProgressDialog = (Button)findViewById(R.id.btnProgressDialog);
        btnProgressDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog dialog = ProgressDialog.show(
                        MainActivity.this,"", "로딩중", true, false);

                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        dialog.dismiss();
                    }
                }).start();
            }
        });

        Button btnProgress2 = (Button)findViewById(R.id.btnProgressDialog2);
        btnProgress2.setOnClickListener(new View.OnClickListener() {
            ProgressDialog dialog;
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(MainActivity.this);
                dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                dialog.setMessage("Now Loading...");
                dialog.setCancelable(false);
                dialog.show();
                dialog.setProgress(0);


                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        int progress = 0;
                        while(progress<100){
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            progress++;
                            dialog.setProgress(progress);
                        }

                        dialog.dismiss();
                    }
                }).start();
            }
        });

        Button btnCustomDialog = (Button)findViewById(R.id.btnCutstomDialog);
        btnCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_CUSTOM);
            }
        });
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
