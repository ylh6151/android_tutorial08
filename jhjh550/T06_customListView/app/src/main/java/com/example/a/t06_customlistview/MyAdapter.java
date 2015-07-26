package com.example.a.t06_customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a.t06_customlistview.MainActivity.MyData;

import java.util.ArrayList;

/**
 * Created by a on 2015-04-15.
 */
public class MyAdapter extends BaseAdapter{

    Context context;
    int layout;
    ArrayList<MyData> list;

    public MyAdapter(Context context, int layout, ArrayList<MyData> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
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
        if(convertView == null){
            LayoutInflater inf =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =  inf.inflate(layout, null);
        }

        MyData data = list.get(position);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvContent = (TextView) convertView.findViewById(R.id.tvContent);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        tvTitle.setText(data.title);
        tvContent.setText(data.subTitle);
        imageView.setImageResource(data.imgIcon);

        return convertView;
    }
}






