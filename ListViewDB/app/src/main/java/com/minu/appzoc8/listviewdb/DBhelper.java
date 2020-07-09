package com.minu.appzoc8.listviewdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by appzoc8 on 1/12/15.
 */
public class DBhelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CATEGORY";
    private static final String TABLE_ITEMS = "items";

    private static final String ITEM_ID = "id";
    private static final String ITEM_NAME = "name";

    private static final String[] COLUMNS = {ITEM_ID, ITEM_NAME};

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_ITEM = "CREATE TABLE items ( " + " id INTEGER PRIMARY KEY AUTO INCREMENT ," + "name TEXT" + " ) ";
        db.execSQL(CREATE_TABLE_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS items");
        this.onCreate(db);

    }

    public void CreateItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ITEM_NAME, item.getName());

        db.insert(TABLE_ITEMS, null, values);
        db.close();
    }

    public Item readItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ITEMS, COLUMNS, "id=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor!=null)
            cursor.moveToFirst();
            Item item=new Item();
            item.setId(Integer.parseInt(cursor.getString(0)));
            item.setName(cursor.getString(1));

            return item;
        }

    public List getAllItems(){

        List items=new LinkedList();
        String query="SELECT * FROM" +TABLE_ITEMS;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        Item item=null;
        if (cursor.moveToFirst()) {
            do {
                item = new Item();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setName(cursor.getString(1));
                items.add(item);
            }while (cursor.moveToNext());
        }


    return items;
    }
    public int UpdateItem(Item item){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put("NAME", item.getName());
        int i=db.update(TABLE_ITEMS,values,ITEM_ID + "=?",new String[]{String.valueOf(item.getId())});
         db.close();
    return i;
    }

    public void deleteItem(Item item){

        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_ITEMS,ITEM_ID + "=?" ,new String[]{String.valueOf(item.getId())});
        db.close();
    }
}