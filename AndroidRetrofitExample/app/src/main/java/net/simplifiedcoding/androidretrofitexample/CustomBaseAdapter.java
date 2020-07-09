package net.simplifiedcoding.androidretrofitexample;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.List;

//import com.theopentutorials.android.activities.R;
//import com.theopentutorials.android.beans.RowItem;

public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    List<Book> rowItems;

    public CustomBaseAdapter(Context context, List<Book> items) {
        this.context = context;
        this.rowItems = items;
    }

    /*private view holder class*/
    private class ViewHolder {

        TextView txtTitle;
        TextView txtemail;


    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.activity_show_book_details, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.textViewBookName);

            holder.txtemail=(TextView)convertView.findViewById(R.id.textViewBookEmail);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Book rowItem = (Book) getItem(position);

        holder.txtTitle.setText(rowItem.getName());
        ;
        holder.txtemail.setText(rowItem.getEmail());
        //holder.txtemail.setText(rowItem.getPrice());



        return convertView;
    }


    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }
}
