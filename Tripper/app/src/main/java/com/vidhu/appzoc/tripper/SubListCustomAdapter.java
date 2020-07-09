package com.vidhu.appzoc.tripper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by appzoc13 on 19/12/15.
 */
public class SubListCustomAdapter extends ArrayAdapter {


    Context context;
    int layoutResourceId;
    ArrayList<SubCategoryList> data = null;
    boolean[] subItemChecked;
    SQLController dbController;

    public SubListCustomAdapter(Context context, int layoutResourceId, ArrayList<SubCategoryList> data) {
        super(context, layoutResourceId,data);
        this.context=context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
        dbController=new SQLController(context);
        try {
            dbController.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;

        SubCategoryHolder holder=null;
        if (row==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new SubCategoryHolder();

            holder.subItemName = (TextView)row.findViewById(R.id.sName);
            holder.checkBox=(CheckBox)row.findViewById(R.id.checkBox);
            row.setTag(holder);
        }else {
            holder = (SubCategoryHolder)row.getTag();
        }

        SubCategoryList subCategory = data.get(position);
        final String id=subCategory.subCategory_id + "";
        final int status=subCategory.getStatus();
        if(status==1)
        {
            holder.checkBox.setChecked(true);
        }
        else
        {
            holder.checkBox.setChecked(false);
        }
        holder.subItemName.setText(subCategory.getSubCategory_name());
//holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//    @Override
//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        if (isChecked) {
//            dbController.updateIsChecked(id, "1");
//        } else {
//            dbController.updateIsChecked(id, "0");
//        }
//
//    }
//});
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status==1)
                {
                    dbController.updateIsChecked(id, "0");
                }
                else
                {
                    dbController.updateIsChecked(id, "1");
                }
            }
        });

        return row;
    }



    private class SubCategoryHolder{
        TextView subItemName;
        CheckBox checkBox;
    }
}