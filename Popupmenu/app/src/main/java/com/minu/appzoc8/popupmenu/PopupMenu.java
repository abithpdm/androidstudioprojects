package com.minu.appzoc8.popupmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PopupMenu extends AppCompatActivity {
Button button1;

    public PopupMenu(PopupMenu popupMenu, Button button1) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_menu);
        button1 =(Button)findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(PopupMenu.this,button1);
                popupMenu.getMenuInflater().inflate(R.menu.menu_popup_menu, popupMenu.getmenu);
                popupMenu.setOn
                }
        });
    }
    }
