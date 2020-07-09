package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.minu.appzoc8.listview.R;

import java.util.ArrayList;

import POJO.UserModel;

/**
 * Created by appzoc8 on 2/2/16.
 */


public class CustemAdapter extends ArrayAdapter<UserModel>{


    ArrayList<UserModel> arrayList;
    Context context;
    LayoutInflater inflater;

    public CustemAdapter(Context context, int resource,ArrayList<UserModel> arrayList) {
        super(context, resource);
        this.context=context;
        this.arrayList=arrayList;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.custem_view_layout, null);
        }

        TextView textView=(TextView) convertView.findViewById(R.id.textView);
        TextView textView1=(TextView) convertView.findViewById(R.id.textView);

        UserModel model=arrayList.get(position);

        String str=model.getUsername();
        textView1.setText(model.getMobileno());
        textView.setText(str);

        return convertView;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public UserModel getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
