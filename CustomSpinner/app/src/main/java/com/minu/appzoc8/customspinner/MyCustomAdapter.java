package com.minu.appzoc8.customspinner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.zip.Inflater;

import static android.content.Intent.getIntent;

/**
 * Created by appzoc8 on 20/11/15.
 */
public class MyCustomAdapter extends ArrayAdapter<String> {
    public String[] DayOfWeek;
    Context context;



    public MyCustomAdapter(Context context, int textViewResourceId, String[] objects) {
        super(context, textViewResourceId, objects);
        this.context=context;
        this.DayOfWeek=objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
       //  super.getView(position, convertView, parent);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View  row = inflater.inflate(R.layout.secondactivity, parent, false);

        TextView label = (TextView) row.findViewById(R.id.weekofday);
       label.setText(DayOfWeek[position]);
      // String DayOfWeek=label.getText().toString();
        ImageView icon = (ImageView) row.findViewById(R.id.icon);


        if (position==0) {

            icon.setImageResource(R.drawable.day2);
        }
        else {
            icon.setImageResource(R.drawable.day1);
        }
return row;

    }

    private LayoutInflater getLayoutInflator() {

        return null;
    }


}
