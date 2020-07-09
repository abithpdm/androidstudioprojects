package com.vidhu.appzoc.tripper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends Activity {


    long Delay=5000;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);
        Timer RunSplash = new Timer();


        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {

                finish();


                Intent myIntent = new Intent(SplashScreenActivity.this,
                        HomePageActivity.class);
                startActivity(myIntent);
            }
        };
        RunSplash.schedule(ShowSplash, Delay);
    }
}
