package com.minu.appzoc8.progressbar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends Activity {
    Button b1;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button1);

    }

    public void download(View view) {
        progress = new ProgressDialog(this);
        progress.setMessage("Downloading MUsic");
        progress.setProgress(0);
        progress.setIndeterminate(true);
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.show();
        final int TotalProgressTime = 100;
        final Thread t = new Thread() {


            public void run() {
                int jumpTime=0;
                while (jumpTime<TotalProgressTime){
                    try
                    {
                        sleep(200);
                        jumpTime +=5;
                        progress.setProgress(jumpTime);
                    }
                    catch (InterruptedException e)
                    {
                      e.printStackTrace();
                    }
                }
            }
        };
        t.start();
        }
    }



