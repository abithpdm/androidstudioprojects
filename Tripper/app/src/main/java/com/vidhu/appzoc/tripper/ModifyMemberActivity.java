package com.vidhu.appzoc.tripper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.SQLException;

public class ModifyMemberActivity extends Activity{



    EditText et;
    Button edit_bt, delete_bt;

    long member_id;

    SQLController dbcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_member);

        dbcon = new SQLController(ModifyMemberActivity.this);
        try {
            dbcon.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        et = (EditText)findViewById(R.id.edit_mem_id);
        edit_bt = (Button)findViewById(R.id.update_bt_id);
        delete_bt = (Button)findViewById(R.id.delete_bt_id);

        Intent i = new Intent();
        String memberID = i.getStringExtra("memberID");
        String memberName = i.getStringExtra("memberName");
//        member_id = Long.parseLong(memberID);
        et.setText(memberName);

        edit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String memName_upd = et.getText().toString();
//                dbcon.updateCategory(member_id, memName_upd);
                this.returnHome();

            }

            private void returnHome() {
                Intent home_intent = new Intent(getApplicationContext(),
                        HomePageActivity.class);

                startActivity(home_intent);
                finish();
            }
        });
        delete_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbcon.deleteCategory(member_id);
                this.returnHomet();
            }

            private void returnHomet() {
                Intent home_intent = new Intent(getApplicationContext(),
                        HomePageActivity.class);
                startActivity(home_intent);
               finish();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
