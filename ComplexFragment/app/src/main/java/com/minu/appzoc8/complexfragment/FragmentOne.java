package com.minu.appzoc8.complexfragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by appzoc8 on 25/11/15.
 */
public class FragmentOne extends Fragment  {
    ListView PersonalDetails;
    TextView mName,mPhone;
    ImageView mDisplayImage;
    String EXTRA_MESSAGE;
    ArrayList<CustomList> personalList;




    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       View view=inflater.inflate(R.layout.fragmentone,container,false);
        PersonalDetails=(ListView) view.findViewById(R.id.PersonalDetails);
        mName=(TextView) view.findViewById(R.id.name);
        mPhone=(TextView) view.findViewById(R.id.phone);
        mDisplayImage=(ImageView) view.findViewById(R.id.image);
        personalList=new ArrayList<>();
        personalList.add(new CustomList("minu","5436739083",R.drawable.a1));
        personalList.add(new CustomList("revathy","25541980",R.drawable.a2));
        personalList.add(new CustomList("manu","765197886647",R.drawable.a3));
        personalList.add(new CustomList("karan", "764317654", R.drawable.a4));
        CustomArrayAdapter customArrayAdapter=new CustomArrayAdapter(getActivity(),R.layout.customview,personalList);

        PersonalDetails.setAdapter(customArrayAdapter);
        PersonalDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),SingleListItem.class);

                intent.putExtra("NAME", personalList.get(position).getName());
                intent.putExtra("IMAGE", personalList.get(position).getImage());
                intent.putExtra("PHNNUM", personalList.get(position).getPhone());
                getActivity().startActivity(intent);

            }
        });

        return view;

    }
}
