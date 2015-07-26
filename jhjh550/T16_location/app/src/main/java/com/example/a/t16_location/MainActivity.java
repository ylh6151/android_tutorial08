package com.example.a.t16_location;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    TextView textView;
    Geocoder coder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coder = new Geocoder(this);
        Button btnRevGeocode = (Button)findViewById(R.id.btnRevGeocode);
        btnRevGeocode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editLat = (EditText)findViewById(R.id.editLat);
                EditText editLon = (EditText)findViewById(R.id.editLon);
                String strLat = editLat.getText().toString();
                String strLon = editLon.getText().toString();

                List<Address> list = null;
                try{
                    list = coder.getFromLocation(Double.parseDouble(strLat),
                            Double.parseDouble(strLon), 10);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(list != null) {
                    EditText editAddress = (EditText) findViewById(R.id.editAddress);
                    editAddress.setText(list.get(0).toString());
                }
            }
        });

        Button btnGeocode = (Button)findViewById(R.id.btnGeocode);
        btnGeocode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Address> list = null;
                String address = ((EditText)findViewById(R.id.editAddress)).getText().toString();

                try {
                    list = coder.getFromLocationName(address, 10);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(list != null){
                    textView.setText(list.get(0).toString());
                }
            }
        });

        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        textView = (TextView)findViewById(R.id.textView);
        String str = "";

        List<String> providers = manager.getAllProviders();
        for(int i=0; i<providers.size(); i++){
            str += "위치 제공자 "+providers.get(i)+"의 상태 : "+
                    manager.isProviderEnabled(providers.get(i)) + "\n";
        }

        textView.setText(str);

        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                String strLoc = "위도 : "+location.getLatitude() + " 경도 : "+
                        location.getLongitude() + " 고도 : "+ location.getAltitude();

                textView.setText(strLoc);
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

        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                0, 0, listener);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                0, 0, listener);
        manager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER,
                0, 0, listener);
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
