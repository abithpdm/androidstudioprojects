package com.vidhu.appzoc.tripper;

import android.app.Activity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

/**
 * Created by appzoc13 on 4/12/15.
 */
public class NavigationDrawerHelper {
    DrawerLayout mDrawerLayout;
    ListView mDrawerListview;
    Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;

    public void init(Activity activity,ListView.OnItemClickListener listener, Toolbar toolbar){
        mDrawerLayout=(DrawerLayout)activity.findViewById(R.id.drawer_layout);
        mDrawerListview=(ListView)activity.findViewById(R.id.left_drawer);
        this.toolbar=toolbar;

        ObjectDrawerITem[] drawerITem=new ObjectDrawerITem[3];

        drawerITem[0]=new ObjectDrawerITem(R.drawable.itemlist,"Check it out");
        drawerITem[1]=new ObjectDrawerITem(R.drawable.route,"Route your place");
        drawerITem[2]=new ObjectDrawerITem(R.drawable.places,"Search a place");



        DrawerItemCustomAdapter adapter=new DrawerItemCustomAdapter(activity,R.layout.list_drawer_item_row,drawerITem);

        mDrawerListview.setAdapter(adapter);
        mDrawerListview.setOnItemClickListener(listener);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerListview.setItemChecked(0,true);

        setupActionBar(activity);

    }

    private void setupActionBar(final Activity theActivity){
        final Activity activity=theActivity;
//
//        ActionBar actionBar=theActivity.getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);


        mDrawerToggle = new ActionBarDrawerToggle(theActivity, mDrawerLayout,toolbar,R.string.DrawerOpened,R.string.DrawerClosed

        ){
            public void onDrawerOpened(View drawerView) {
                activity.invalidateOptionsMenu();
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                activity.invalidateOptionsMenu();
                super.onDrawerClosed(drawerView);
            }
        };
    }

    public void handleSelect(int option){
        mDrawerListview.setItemChecked(option, true);
        mDrawerLayout.closeDrawer(mDrawerListview);
    }

    public void handleOnPrepareOptionMenu(Menu menu){
        boolean itemVisible=!mDrawerLayout.isDrawerOpen(mDrawerListview);
        for (int index = 0; index < menu.size(); index++) {
            MenuItem item = menu.getItem(index);
            item.setEnabled(itemVisible);
        }

    }
    public void handleOnOptionsItemSelected(MenuItem menuItem) {
        mDrawerToggle.onOptionsItemSelected(menuItem);

    }
    public void syncState() {
        mDrawerToggle.syncState();
    }

    public void setSelection(int option) {
        mDrawerListview.setItemChecked(option,true);
    }
}

