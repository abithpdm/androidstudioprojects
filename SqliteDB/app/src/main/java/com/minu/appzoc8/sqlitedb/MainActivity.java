package com.minu.appzoc8.sqlitedb;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDBHelper db=new MyDBHelper(this);
        db.addBook(new Book("ANDROID DEVELOPMENT","JHON"));
        db.addBook(new Book("ANDROID PROGRAMMING","STEVE"));
        db.addBook(new Book("LEARN ANDROID","MARK"));
        List<Book> list=db.getAllBooks();
        db.deleteBooks(list.get(0));
        db.getAllBooks();
    }
}
