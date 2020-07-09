package com.minu.appzoc8.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by appzoc8 on 12/11/15.
 */
public class CustomGridArrayAdapter extends ArrayAdapter<CustomArrayGrid> {
    Context context;

    public CustomGridArrayAdapter(Context context, int resource, ArrayList<CustomArrayGrid> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int pos, View mView, ViewGroup parent) {
        ViewHolder viewHolder=new ViewHolder();
        if (mView == null) {
            mView= LayoutInflater.from(context).inflate(R.layout.newlayout,null);
            viewHolder.imageView=(ImageView)mView.findViewById(R.id.image);
            viewHolder.text=(TextView)mView.findViewById(R.id.text);
            mView.setTag(viewHolder);
        }
        else{
            viewHolder =(ViewHolder)mView .getTag();
        }


        viewHolder.imageView.setImageResource(getItem(pos).getImage());
        viewHolder.text.setText(getItem(pos).getText());

            return mView;
        }
        public class ViewHolder {
            TextView text;
            ImageView imageView;
        }

}
