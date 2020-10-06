package com.hufs.hw2_2_customlistview;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MyAdapter extends BaseAdapter {
    private final Context context;
    private final String[] values;

    public MyAdapter(Context context, String[] values) {
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        TextView textView = (TextView) itemView.findViewById(R.id.label);
        ImageView imageView = (ImageView)itemView.findViewById(R.id.icon);
        textView.setText(values[position]);
        String s = values[position];
        if(s.startsWith("App") || s.startsWith("Cher") || s.startsWith("Ban")){
            imageView.setImageResource(R.drawable.good);
        }else{
            imageView.setImageResource(R.drawable.fig);
        }
        return itemView;
    }
}
