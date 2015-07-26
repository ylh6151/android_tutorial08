package com.example.a.t12_domparser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class MainActivity extends ActionBarActivity {

    TextView textView;
    class MyTask extends AsyncTask<String, Void, Document>{

        @Override
        protected void onPostExecute(Document document) {
            String str = "";

            NodeList nodeList = document.getElementsByTagName("data");
            for(int i=0; i<nodeList.getLength(); i++){
                Element dataNode = (Element) nodeList.item(i);// <data>...</data>

                NodeList hourList = dataNode.getElementsByTagName("hour");
                Element hourNodeList = (Element)hourList.item(0); // <hour>15</hour>
                NodeList hourNode = hourNodeList.getChildNodes();
                String hourValue = hourNode.item(0).getNodeValue();// 15

                NodeList wfList = dataNode.getElementsByTagName("wfKor");
                Element wfNodeList = (Element)wfList.item(0); // <wfKor>비</wfKor>
                NodeList wfNode = wfNodeList.getChildNodes();
                String wfKorValue = wfNode.item(0).getNodeValue();// 비

                str += hourValue +"시 : "+wfKorValue+"\n";
            }

            textView.setText(str);
        }

        @Override
        protected Document doInBackground(String... params) {
            URL url;
            Document doc = null;

            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                url = new URL(params[0]);
                doc = db.parse(url.openStream());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return doc;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);

        MyTask task = new MyTask();
        task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153051000");

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
