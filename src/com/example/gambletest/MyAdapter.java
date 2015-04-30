package com.example.gambletest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<String> {

    Context context;
    int layoutResourceId;
    String data[] = null;
    String color[] = null;

    public MyAdapter(Context context, int layoutResourceId, String[] data, String[] color) {
	super(context, layoutResourceId, data);
	this.layoutResourceId = layoutResourceId;
	this.context = context;
	this.data = data;
	this.color = color;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	View row = convertView;
	StringHolder holder = null;

	if (row == null) {
	    LayoutInflater inflater = ((Activity) context).getLayoutInflater();
	    row = inflater.inflate(layoutResourceId, parent, false);

	    holder = new StringHolder();
	    holder.txtTitle = (TextView) row.findViewById(R.id.text1);

	    row.setTag(holder);
	} else {
	    holder = (StringHolder) row.getTag();
	}

	holder.txtTitle.setText(data[position]);
	row.setBackgroundColor(Color.parseColor(color[position]));

	return row;
    }

    static class StringHolder {
	TextView txtTitle;
    }
}