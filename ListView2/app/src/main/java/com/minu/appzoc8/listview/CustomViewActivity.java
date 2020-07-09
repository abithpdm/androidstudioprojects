package com.minu.appzoc8.listview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import POJO.UserModel;
import adapter.CustemAdapter;

public class CustomViewActivity extends AppCompatActivity {

    ListView listView;
    CustemAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Bundle b=getIntent().getExtras();
        String name=b.getString("name");
        Toast.makeText(CustomViewActivity.this, name, Toast.LENGTH_SHORT).show();


        listView=(ListView) findViewById(R.id.listView);

        ArrayList<UserModel> arrayList=new ArrayList<>();
        for(int i=1;i<10;i++) {
            UserModel model = new UserModel();
            model.setUsername("name"+i);
            model.setMobileno("1234567");
            arrayList.add(model);
        }

        adapter=new CustemAdapter(CustomViewActivity.this,R.layout.custem_view_layout,arrayList);
        listView.setAdapter(adapter);

    }
        }
