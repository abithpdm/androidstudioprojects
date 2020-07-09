package com.vidhu.appzoc.tripper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by appzoc13 on 5/12/15.
 */


 public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String TABLE_CATEGORY = "member";
    public static final String TABLE_SUB_CATEGORY="subMembers";

    public static final String CATEGORY_ID = "_id";
    public static final String CATEGORY_NAME = "name";

    public static final String SUB_CATEGORY_ID="S_id";
    public static final String SUB_CATEGORY_NAME="S_name";


    static final String DB_NAME = "CATEGORY.DB";
    static final int DB_VERSION = 2;

    public static final String KEY_STATE = "state";
    private static final String CREATE_TABLE = "create table IF NOT EXISTS "
            + TABLE_CATEGORY + "(" + CATEGORY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CATEGORY_NAME + " TEXT );";

    private static final String CREATE_SUB_TABLE="create table IF NOT EXISTS "
            + TABLE_SUB_CATEGORY + "( " + SUB_CATEGORY_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT ," + CATEGORY_ID +
            " TEXT NOT NULL , " + SUB_CATEGORY_NAME + " TEXT NOT NULL ,"
            + KEY_STATE +" TEXT NOT NULL " + " ); ";



    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_SUB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUB_CATEGORY);
        onCreate(db);
    }
}