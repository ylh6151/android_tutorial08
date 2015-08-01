package com.example.oraclejava4.t12_xml;

import android.os.AsyncTask;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by oraclejava4 on 15. 8. 1..
 */
public class MyXMLTask extends AsyncTask<String, Void, Document>
{

    public MyXMLTask(TextView textView) {
        this.textView = textView;
    }

    private TextView textView;

    private String str="";

    public String getStr(){
        return str;
    }

    @Override
    protected Document doInBackground(String... params) {

        URL url;
        Document doc = null;

        try {
            url = new URL(params[0]);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(url.openStream());
        } catch (Exception e){
            e.printStackTrace();
        }
        return doc;
    }

    private String getTagValue(Element dataElement, String tagName){
        NodeList nodeList = dataElement.getElementsByTagName(tagName);
        Element element = (Element)nodeList.item(0); //<day>0</day>
        NodeList textNodeList = element.getChildNodes();
        return textNodeList.item(0).getNodeValue(); //0
    }


    @Override
    protected void onPostExecute(Document document) {
        super.onPostExecute(document);

        str="";
        NodeList nodeList = document.getElementsByTagName("data");
        for(int i = 0; i<nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            Element dataElement = (Element)node;// <data>...</data>

            str += "day :" + getTagValue(dataElement, "day");
            str += "시간 :" + getTagValue(dataElement, "hour");
            str += "온도 :" + getTagValue(dataElement, "temp");
            str += "날씨 :" + getTagValue(dataElement, "wfKor");
            str += "\n";

        }

        textView.setText(str);
    }
}
