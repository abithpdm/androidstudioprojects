package com.minu.appzoc8.app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by appzoc8 on 13/4/16.
 */
public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    List<pojo> rowitems;
    public CustomBaseAdapter(Context context,List<pojo> rowitems){
        this.context=context;
        this.rowitems=rowitems;
    }
    private class ViewHolder{
        ImageView imageView;
        TextView txtTitle;
        TextView txtdesc;
    }
    @Override
    public int getCount() {
        return rowitems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowitems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowitems.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        LayoutInflater mInflator =(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView==null){
            convertView=mInflator.inflate(R.layout.newlayout,null);
            holder=new ViewHolder();
            holder.txtTitle=(TextView)convertView.findViewById(R.id.txt1);
            holder.imageView=(ImageView)convertView.findViewById(R.id.img);
            holder.txtdesc=(TextView)convertView.findViewById(R.id.txt2);
            convertView.setTag(holder);

        }
        else {
            holder=(ViewHolder)convertView.getTag();
        }
        pojo rowitem = (pojo)getItem(position);
        holder.txtTitle.setText(rowitem.getTitle());
        holder.imageView.setImageResource(rowitem.getImage());
        holder.txtdesc.setText(rowitem.getDesc());
        return convertView;
    }

}
