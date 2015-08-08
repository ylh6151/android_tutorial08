package com.example.c.t19_location;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    String str = "";
    TextView textView;
    Geocoder geocoder;

    EditText editLat;
    EditText editLong;
    EditText editAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        LocationManager manager = (LocationManager)getSystemService(LOCATION_SERVICE);
        List<String> providers = manager.getAllProviders();


        for(int i = 0; i < providers.size(); i++){
            str += providers.get(i) + ": " + manager.isProviderEnabled(providers.get(i))+"\n";
        }
        textView.setText(str);

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                str += "\nlatitude" + location.getLatitude();
                str += "longitude" + location.getLongitude();
                str += "alt" + location.getAltitude();
                textView.setText(str);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        editLat = (EditText) findViewById(R.id.editLatitude);
        editLong = (EditText) findViewById(R.id.editLongitude);
        editAddress = (EditText) findViewById(R.id.editAddress);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0.1f, locationListener);  //10000 milisecond = 10 ÃÊ
        manager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 10000, 0.1f, locationListener);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0.1f, locationListener);
        //manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

        geocoder = new Geocoder(this);
        Button btnUp = (Button) findViewById(R.id.btnUp);
        final Button btnDown = (Button) findViewById(R.id.btnDown);
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double dblLat = Double.parseDouble(editLat.getText().toString());
                Double dblLong = Double.parseDouble(editLong.getText().toString());

                List<Address> list = null;
                try {
                   list = geocoder.getFromLocation(dblLat, dblLong, 10);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(list != null){
                    editAddress.setText(list.get(0).toString());
                }
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
