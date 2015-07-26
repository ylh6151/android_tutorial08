package com.example.c.t06_customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by c on 2015-07-26.
 */
public class MyAdapter extends BaseAdapter {
    ArrayList<MainActivity.MyData> list;
    int itemLayout;
    Context context;

    //constructor
    public MyAdapter(Context context ,int itemLayout, ArrayList<MainActivity.MyData> list) {
        this.list = list;
        this.itemLayout = itemLayout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            //置段 持失獣 (convertView == null)
            if (convertView == null) {
                LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inf.inflate(itemLayout, null);
            }

            MainActivity.MyData data = list.get(position);
            TextView title = (TextView) convertView.findViewById(R.id.textTitle);
            TextView subTitle = (TextView) convertView.findViewById(R.id.textSubTitle);
            ImageView imgView = (ImageView) convertView.findViewById(R.id.imageView);
            title.setText(data.title);
            subTitle.setText(data.subTitle);
            imgView.setImageResource(data.imgIcon);

            return convertView;
    }
}
