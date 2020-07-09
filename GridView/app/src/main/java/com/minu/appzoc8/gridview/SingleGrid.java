package com.minu.appzoc8.gridview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by appzoc8 on 18/11/15.
 */
public class SingleGrid extends Activity {
    TextView textView;
    ImageView image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singlegrid);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        textView = (TextView) findViewById(R.id.textView);
        image1=(ImageView) findViewById(R.id.imageview);
       // textView.setText(getIntent().getStringExtra("NAME"));

        String text=bundle.getString("NAME");
        textView.setText(getIntent().getStringExtra("NAME"));
        int image = bundle.getInt("IMAGE");

        textView.setText(text);
        image1 = (ImageView) findViewById(R.id.imageview);
        image1.setImageResource(image);
    }
}
