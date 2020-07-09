package com.minu.appzoc8.gridview;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {
GridView gridView;
    TextView text;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView=(GridView)findViewById(R.id.minu);

        text=(TextView)findViewById(R.id.text);
        imageView=(ImageView)findViewById(R.id.image);
        final ArrayList<CustomArrayGrid> personallist =new ArrayList<>();
        personallist.add(new CustomArrayGrid(R.drawable.sen2, "MINU"));
       CustomArrayGrid customArrayGrid=new CustomArrayGrid();
        
        customArrayGrid.setImage(R.drawable.sceb3);
        customArrayGrid.setText("revathy");
        personallist.add(customArrayGrid);
        personallist.add(new CustomArrayGrid( R.drawable.scen1,"ramu"));
        personallist.add(new CustomArrayGrid(R.drawable.scen4,"somu"));
        personallist.add(new CustomArrayGrid(R.drawable.scen5,"karthik"));
        personallist.add(new CustomArrayGrid(R.drawable.scen6,"hai"));
        personallist.add(new CustomArrayGrid(R.drawable.sceb3, "hgidgoi"));
        personallist.add(new CustomArrayGrid(R.drawable.sen2, "kavi"));
        CustomGridArrayAdapter customGridArrayAdapter=new CustomGridArrayAdapter(this,R.layout.customgridview,personallist
                );
        gridView.setAdapter(customGridArrayAdapter);

       gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(MainActivity.this, SingleGrid.class);



               intent.putExtra("NAME",personallist.get(position).getText());
               intent.putExtra("IMAGE",personallist.get(position).getImage());

               startActivity(intent);
           }
       });
    }
}


