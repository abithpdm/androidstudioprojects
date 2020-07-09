package com.vidhu.appzoc.tripper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by appzoc13 on 4/12/15.
 */
public class DrawerItemCustomAdapter extends BaseAdapter {
    Context mContext;
    int mLayoutResourceId;
    ObjectDrawerITem mData[] = null;

    public DrawerItemCustomAdapter(Context mContext, int mLayoutResourceId, ObjectDrawerITem[] mData) {
        this.mContext = mContext;
        this.mLayoutResourceId = mLayoutResourceId;
        this.mData = mData;
    }



    @Override
    public int getCount() {
        return mData.length;
    }

    @Override
    public Object getItem(int position) {
        return mData[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        ObjectDrawerITem objectDrawerItem = mData[position];

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(mLayoutResourceId, parent, false);

        ImageView iconImageView = (ImageView) listItem.findViewById(R.id.drawer_item_icon);
        TextView nameTextView = (TextView) listItem.findViewById(R.id.drawer_item_name);

        iconImageView.setImageDrawable(listItem.getResources().getDrawable(objectDrawerItem.getIcon()));
        nameTextView.setText(objectDrawerItem.getName());

        return listItem;
    }
}

