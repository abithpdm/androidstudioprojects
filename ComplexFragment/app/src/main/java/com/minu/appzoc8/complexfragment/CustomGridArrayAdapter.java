package com.minu.appzoc8.complexfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by appzoc8 on 27/11/15.
 */
public class CustomGridArrayAdapter extends ArrayAdapter<CustomArrayGrid> {
    Context context;
    ArrayList<CustomArrayGrid> arrayList;

    public CustomGridArrayAdapter(Context context, int resource, ArrayList objects) {
        super(context, resource, objects);
        this.context=context;
        this.arrayList=objects;
    }

    @Override
    public View getView(int pos, View mView, ViewGroup parent) {
        ViewHolder viewHolder=new ViewHolder();
        if (mView==null){
            mView= LayoutInflater.from(context).inflate(R.layout.newgridlayout,null);
            viewHolder.imageView=(ImageView) mView.findViewById(R.id.image);
            viewHolder.text=(TextView) mView.findViewById(R.id.text);
            mView.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder) mView.getTag();
        }
        CustomArrayGrid customArrayGrid=arrayList.get(pos);
        viewHolder.text.setText(customArrayGrid.getText());
        viewHolder.imageView.setImageResource(customArrayGrid.getImage());
        return mView;
    }

    public class ViewHolder
    {

        TextView text;
        ImageView imageView;
    }
}
