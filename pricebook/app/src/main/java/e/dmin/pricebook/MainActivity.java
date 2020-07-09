package e.dmin.pricebook;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
//import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText model_id ;
    EditText price ;
    TextView lst ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model_id=findViewById(R.id.modelid);
        price=findViewById(R.id.priceid);
        lst=findViewById(R.id.lst);

        }
    public void additem(View view){
        MyDBHandler dbHandler = new MyDBHandler(this , null,null,1);
        String modelnumber=model_id.getText().toString();
        int rate =Integer.parseInt(price.getText().toString());
        Model model=new Model(modelnumber,rate);
        dbHandler.addHandler(model);
        model_id.setText("");
        price.setText("");
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void findprice(View view){
        MyDBHandler dbHandler=new MyDBHandler(this,null,null,1);
        Model model=dbHandler.findHandler(model_id.getText().toString());
        if (model !=null){
            lst.setText(String.valueOf(model.getModelNumber())+" "+model.getPrice()+System.getProperty("line.seperator"));
            model_id.setText("");
            price.setText("");
        }else {
            lst.setText("No model found");
           // model_id.setText("");
            //price.setText("");
        }

        }

    public void removemodel(View view){
        MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);
        boolean result = dbHandler.deleteHandler(model_id.getText().toString());
        if (result)
        {
            model_id.setText("");
            price.setText("");
            lst.setText("Model deleted");
        }else {
            model_id.setText("No model found");
        }

    }
    public void updatemodel(View view){

        MyDBHandler dbHandler=new MyDBHandler(this,null,null,1);
        boolean result=dbHandler.updateHandler(model_id.getText().toString(),Integer.parseInt(price.getText().toString()));
        if (result){
            model_id.setText("");
            price.setText("");
            lst.setText("Model price updated");
        }else
        { model_id.setText("No such model found");}
    }


}
