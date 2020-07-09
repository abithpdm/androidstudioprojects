package com.minu.appzoc8.complexfragment;

import android.content.Context;
import android.support.v4.app.NotificationCompatSideChannelService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by appzoc8 on 26/11/15.
 */
public class CustomArrayAdapter extends ArrayAdapter<CustomList> {
    Context context;

    public CustomArrayAdapter(Context context, int resource, ArrayList objects) {
        super(context, resource, objects);
        this.context=context;
    }


    public View getView(int pos,View mView,ViewGroup parent)
    {
        ViewHolder viewHolder=new ViewHolder();
        if (mView == null)
        {
        mView= LayoutInflater.from(context).inflate(R.layout.customview,null);
            viewHolder.mName=(TextView) mView.findViewById(R.id.name);
            viewHolder.mPhone=(TextView) mView.findViewById(R.id.phone);
            viewHolder.image=(ImageView) mView.findViewById(R.id.image);
            mView.setTag(viewHolder);

        }
        else {
            viewHolder=(ViewHolder) mView.getTag();
        }
            viewHolder.mName.setText(getItem(pos).getName());
        viewHolder.mPhone.setText(getItem(pos).getPhone());
        viewHolder.image.setImageResource(getItem(pos).getImage());
        return mView;
    }



    public class ViewHolder
    {
        ImageView image;
        TextView mName,mPhone;
    }
}
