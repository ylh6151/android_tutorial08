package com.example.c.t23_json;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.PipedOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    String str = "[{'name':'Kim', 'tel':'010-3837-2837', 'age':'33'}," +
            "{'name':'Park', 'tel':'010-3444-6666', 'age':'21'}," +
            "{'name':'Lee', 'tel':'010-2222-8888', 'age':'45'}]";

    class GetContacts extends AsyncTask<Void,Void,Void>{
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(pDialog.isShowing()){
                pDialog.dismiss();
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            String strJson = getResponseString("http://api.androidhive.info/contacts/", GET, null);
            Log.d("JSONdoinBackground", strJson);

/*            {
                "id": "c200",
                "name": "Ravi Tamada",
                "email": "ravi@gmail.com",
                "address": "xx-xx-xxxx,x - street, x - country",
                "gender" : "male",
                "phone": {
                    "mobile": "+91 0000000000",
                    "home": "00 000000",
                    "office": "00 000000"
                }
            }*/

            try {
                JSONObject obj = new JSONObject(strJson);
                JSONArray contacts = obj.getJSONArray("contacts");
                for(int i = 0; i <contacts.length(); i++){
                    JSONObject contact = contacts.getJSONObject(i);
                    String id = contact.getString("id");
                    String name = contact.getString("name");
                    String email = contact.getString("email");
                    String gender = contact.getString("gender");
                    JSONObject phone = contact.getJSONObject("phone");
                    String mobile = phone.getString("mobile");
                    String home = phone.getString("home");
                    String office = phone.getString("office");
                    //Log.i("jsonlogging", "name :"+ name + ", email :" + email + ", gender :" + gender);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public String getResponseString(String url, int method, List<NameValuePair> params) {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = null;
            HttpEntity btnEntity = null;
            try {
                if (method == POST) {
                    HttpPost httpPost = new HttpPost(url);
                    if (params != null) {

                        httpPost.setEntity(new UrlEncodedFormEntity(params));
                    }
                    httpResponse = httpClient.execute(httpPost);
                } else if (method == GET) {
                    if(params != null){
                        String paramString = URLEncodedUtils.format(params, "utf-8");
                        url += "?"+paramString;
                    }
                    HttpGet httpGet = new HttpGet();
                    httpResponse = httpClient.execute(httpGet);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return responseStr;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            JSONArray array = new JSONArray(str);
            for(int i = 0; i <array.length(); i++){
                JSONObject obj = array.getJSONObject(i);
                String name = obj.getString("name");
                String tel = obj.getString("tel");
                int age = obj.getInt("age");
                Log.i("jsonlogging", "name :"+ name + ", tel :" + tel + ", age :" + age);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        GetContacts contacts = new GetContacts();
        contacts.execute();
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

    public static final int POST = 1;
    public static final int GET = 2;
    String responseStr = "";

}
