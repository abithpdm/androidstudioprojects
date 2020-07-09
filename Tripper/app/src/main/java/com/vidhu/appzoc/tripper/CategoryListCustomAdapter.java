package com.vidhu.appzoc.tripper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by appzoc13 on 7/12/15.
 */
public class CategoryListCustomAdapter extends  ArrayAdapter{
    Context context;
    int layoutResourceId;
    ArrayList<CategoryList>data=null;

    public CategoryListCustomAdapter(Context context, int layoutResourceId,ArrayList<CategoryList>data) {
        super(context,layoutResourceId,data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        CategoryHolder holder=null;
        if (row==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new CategoryHolder();

            holder.name = (TextView)row.findViewById(R.id.txtName);
            holder.count=(TextView)row.findViewById(R.id.countId);

            row.setTag(holder);
        }else {
            holder = (CategoryHolder)row.getTag();
        }

        CategoryList category = data.get(position);
        holder.name.setText(category.getName());
        holder.count.setText(category.getCount());

        return row;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    private class CategoryHolder {
        TextView name;
        TextView count;
    }
}


