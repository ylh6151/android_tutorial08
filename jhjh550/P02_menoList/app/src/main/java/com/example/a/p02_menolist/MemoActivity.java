package com.example.a.p02_menolist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.util.Date;


public class MemoActivity extends ActionBarActivity {

    EditText editDate;
    EditText editMemo;
    private static final int TAKE_PICTURE = 1;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meno);

        Intent intent = getIntent();
        path = intent.getExtras().get("path").toString();


        editDate = (EditText) findViewById(R.id.editDate);
        editDate.setText(new Date().toString());
        editMemo = (EditText) findViewById(R.id.editMemo);
        final MemoDBHandler dbHandler = new MemoDBHandler(this);

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String memo = editMemo.getText().toString();
                String date = editDate.getText().toString();
                dbHandler.insert(memo, date, "");
            }
        });

        Button btnTakePhoto = (Button) findViewById(R.id.btnTakePhoto);
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                startActivityForResult(i, TAKE_PICTURE);
            }
        });

    }



    @Override
    protected void onResume() {
        super.onResume();

        Bitmap bitmap = null;

        try {
            //bitmap = MediaStore.Images.Media.getBitmap(
            //    getContentResolver(), Uri.fromFile(file));
            bitmap = BitmapFactory.decodeFile(path);

            ImageView img = (ImageView)findViewById(R.id.imageView);
            bitmap = rotateImg(bitmap, 90);
            img.setImageBitmap(bitmap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Bitmap rotateImg(Bitmap bitmap, int degree){
        if(degree !=0 && bitmap !=null){
            Matrix m = new Matrix();
            m.setRotate(degree, bitmap.getWidth()/2, bitmap.getHeight()/2);

            Bitmap converted = Bitmap.createBitmap(bitmap,0,0,
                    bitmap.getWidth(), bitmap.getHeight(),m, false);
            if(bitmap != converted){
                bitmap.recycle();
                bitmap = converted;
            }
        }

        return bitmap;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_meno, menu);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == TAKE_PICTURE){
            if(resultCode == RESULT_OK){

                Bitmap bitmap = null;

                try {
                    //bitmap = MediaStore.Images.Media.getBitmap(
                    //    getContentResolver(), Uri.fromFile(file));
                    bitmap = BitmapFactory.decodeFile(path);

                    ImageView img = (ImageView)findViewById(R.id.imageView);
                    bitmap = rotateImg(bitmap, 90);
                    img.setImageBitmap(bitmap);
                    img.invalidate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
