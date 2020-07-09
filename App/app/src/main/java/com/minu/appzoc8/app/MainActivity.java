package com.minu.appzoc8.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final Integer image[]={R.drawable.samsung,R.drawable.samsung,R.drawable.samsung,R.drawable.samsung,R.drawable.samsung,R.drawable.samsung};
 public static final String title[]={"Samsung" , "samsung " , "samsung","Samsung" , "samsung " , "samsung"};
   public static final String desc[]={"brand" , "new brand" , "mobile brand","mobile","computers" , "laptops"};
    ListView listView;
    List<pojo> rowitems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rowitems=new ArrayList<pojo>();
        for (int i = 0;i<title.length;i++){
            pojo item = new pojo();
            item.setTitle(title[i]);
            item.setImage(image[i]);
            item.setDesc(desc[i]);
            rowitems.add(item);
        }
        listView=(ListView)findViewById(R.id.listView);
        CustomBaseAdapter adp=new CustomBaseAdapter(MainActivity.this,rowitems);
listView.setAdapter(adp);



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
