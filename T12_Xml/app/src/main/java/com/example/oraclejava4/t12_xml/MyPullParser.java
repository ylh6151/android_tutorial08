package com.example.oraclejava4.t12_xml;

import android.os.AsyncTask;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by oraclejava4 on 15. 8. 1..
 */
public class MyPullParser extends AsyncTask<String, Void, Void>{
    private String str = "";

    private TextView textView;

    MyPullParser(TextView textView){
        this.textView = textView;
    }

    public String getStr(){
        return str;
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            URL url = new URL(params[0]);
            xpp.setInput(url.openStream(), "UTF-8");

            String[] keyword = {"hour", "day", "temp", "wfKor"};

            boolean bRead = false;
            int eventType = xpp.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        String tagName = xpp.getName();
                        for(String s: keyword){
                            if(tagName.equals(s)){
                                bRead = true;
                                str += tagName;
                            }
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if(bRead == true) {
                            str += xpp.getText();
                            xpp.getText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;

                }
                eventType = xpp.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        textView.setText(str);
    }
}
