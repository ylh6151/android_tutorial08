package com.example.a.t14_camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;


public class MainActivity extends ActionBarActivity {

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        Bundle bundle = new Bundle();
        bundle.putString("path", path);

        outState.putBundle("saved_data", bundle);
    }

    private static final int TAKE_PICTURE = 1;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        path = Environment.getExternalStorageDirectory().toString()+"/imageTest.jpg";
        Button btnTake = (Button)findViewById(R.id.btnTake);
        btnTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                startActivityForResult(i, TAKE_PICTURE);
            }
        });

        if(savedInstanceState != null){
            savedInstanceState.getBundle("saved_data");

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
