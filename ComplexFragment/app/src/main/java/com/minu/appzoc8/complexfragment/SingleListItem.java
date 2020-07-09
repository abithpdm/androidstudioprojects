package com.minu.appzoc8.complexfragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;

import static android.content.Intent.getIntent;

/**
 * Created by appzoc8 on 26/11/15.
 */
public class SingleListItem extends Activity {
    TextView textView, textView1;
    ImageView image1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singlelistitem);
        textView = (TextView) findViewById(R.id.textView);
        textView1 = (TextView) findViewById(R.id.textView1);
        image1 = (ImageView) findViewById(R.id.imageview);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        textView.setText(getIntent().getStringExtra("NAME"));
        textView1.setText(getIntent().getStringExtra("PHNNUM"));
        int image = extras.getInt("IMAGE");
        image1.setImageResource(image);
    }
}