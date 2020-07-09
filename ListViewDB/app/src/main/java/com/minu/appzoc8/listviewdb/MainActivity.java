package com.minu.appzoc8.listviewdb;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {
    DBhelper db=new DBhelper(this);
    List list;
    ArrayAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db.onUpgrade(db.getWritableDatabase(), 1, 2);

        db.CreateItem(new Item(" Electronics"));
        db.CreateItem(new Item(" Food"));
        db.CreateItem(new Item(" cloth"));
        db.CreateItem(new Item(" Medicine"));
        db.CreateItem(new Item(" Certificate"));

        list=db.getAllItems();
        List listname=new ArrayList();
        for(int i=0;i<list.size();i++) {
            listname.add(i, list.get(i).getName());
        }


    }
}
