package com.minu.appzoc8.spinnerexample;

import android.app.Activity;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {
    Spinner spinnerState;
    TextView items;
    String[] List= new String[]{"apple", "orange", "grapes", "watermelon", "guava", "mango", "chikku"};
    String[] List= new String[]{"apple", "orange", "grapes", "watermelon", "guava", "mango", "chikku"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerState=(Spinner) findViewById(R.id.spinnerstate);
        items=(TextView) findViewById(R.id.items);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,List);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerState.setAdapter(arrayAdapter);
        spinnerState.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerState.setSelection(position);
        String selstate=(String)spinnerState.getSelectedItem();
        items.setText("selected option is:"+selstate);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
