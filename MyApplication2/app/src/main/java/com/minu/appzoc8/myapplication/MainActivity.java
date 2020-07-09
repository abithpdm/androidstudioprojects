package com.minu.appzoc8.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String[] Vegetables= new String[] {
            "Carrot",
            "Brinjal",
            "Ladies Finger",
            "Tomato"

    };
    String[] Colour_Veg=new String[]{
            "Orange",
            "Violet",
            "Green",
            "Green"

    };
    int[] Veg_ima = new int[]{
            R.drawable.carrot,
            R.drawable.brinjal,
            R.drawable.ladiesfi,
            R.drawable.tomato

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
        for(int i=0;i<Vegetables.length;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", "Vegetables : " + Vegetables[i]);
            hm.put("col","Colour: " + Colour_Veg[i]);
            hm.put("pictur", Integer.toString(Veg_ima[i]) );
            aList.add(hm);
        }
        String[] from = { "txt","col","pictur" };
        int[] to = { R.id.veg,R.id.col,R.id.veg_imag};


        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_linearlayout, from, to);

        // Getting a reference to listview of main.xml layout file
        ListView listView = ( ListView ) findViewById(R.id.listview);

        // Setting the adapter to the listView
        listView.setAdapter(adapter);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
