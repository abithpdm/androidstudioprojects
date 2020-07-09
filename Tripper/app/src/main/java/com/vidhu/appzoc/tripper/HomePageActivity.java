package com.vidhu.appzoc.tripper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;

import java.sql.SQLException;


public class HomePageActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private NavigationDrawerHelper mNavigationDrawerHelper;
    SQLController dbController;
    private Toolbar toolbar;
    private ImageButton AddCategory;
    private ImageButton AddSubCategory;
    private Button exit;

    private Fragment mFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        AddCategory = (ImageButton) findViewById(R.id.addCategory);
        AddSubCategory = (ImageButton) findViewById(R.id.addsubCategory);
        exit=(Button)findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertbox = new AlertDialog.Builder(HomePageActivity.this)
                .setMessage("Do you want to exit application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {

                        finish();
                        //close();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();



            }
        });


        mNavigationDrawerHelper = new NavigationDrawerHelper();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbController = new SQLController(HomePageActivity.this);
        try {
            dbController.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mNavigationDrawerHelper.init(this, this, toolbar);


        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        android.support.v4.app.Fragment fragmentS1 = new ListItemFragment();
        fragmentTransaction.replace(R.id.fragment_container, fragmentS1);
        fragmentTransaction.commit();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
        return true;
    }








    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        mNavigationDrawerHelper.handleSelect(position);


        switch (position) {
            case 0:
                mFragment = new ListItemFragment();
                break;
            case 1:
              mFragment = new MainActivity();
                break;
            case 2:
                mFragment = new PlaceFragment();
                break;
//            case 3:
//                mFragment= new MainActivity();
//                break;


        }
        attachFragment();

    }

//    add.setOnClickListener(new View.OnClickListener() {
//        public void onClick(View v) {
//
//            AlertDialog.Builder alert = new AlertDialog.Builder(HomePageActivity.this);
//            alert.setTitle("Item"); //Set Alert dialog title here
//            alert.setMessage("Enter item name"); //Message here
//
//            // Set an EditText view to get user input
//            final EditText input = new EditText(HomePageActivity.this);
//            alert.setView(input);
//
//            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int whichButton) {
//
//                    String srt = input.getEditableText().toString();
//
//                }
//            });
//            alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int whichButton) {
//                    // Canceled.
//                    dialog.cancel();
//                }
//            });
//            AlertDialog alertDialog = alert.create();
//            alertDialog.show();
//
//        }
//    });
//public boolean onKeyDown(int keyCode, KeyEvent event) {
//    if (keyCode == KeyEvent.KEYCODE_BACK) {
//        exitByBackKey();
//
//        //moveTaskToBack(false);
//
//        return true;
//    }
//    return super.onKeyDown(keyCode, event);
//}

//    protected void exitByBackKey() {
//
//        AlertDialog alertbox = new AlertDialog.Builder(this)
//                .setMessage("Do you want to exit application?")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//
//                    // do something when the button is clicked
//                    public void onClick(DialogInterface arg0, int arg1) {
//
//                        finish();
//                        //close();
//
//
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//
//                    // do something when the button is clicked
//                    public void onClick(DialogInterface arg0, int arg1) {
//                    }
//                })
//                .show();
//
//    }

//

    private void attachFragment() {
        if (mFragment != null) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, mFragment).commit();

        } else {
            Log.e("HomePageActivity", "Error in creating fragment");
        }
    }
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mNavigationDrawerHelper.syncState();
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mNavigationDrawerHelper.handleOnPrepareOptionMenu(menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        mNavigationDrawerHelper.syncState();
        super.onConfigurationChanged(newConfig);
    }



//                Intent add_mem = new Intent(HomePageActivity.this, AddActivity.class);
//                startActivity(add_mem);
//







}

