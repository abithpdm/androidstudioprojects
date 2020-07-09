package e.dmin.pricebook;
import android.annotation.TargetApi;
import android.content.ContentValues;
//import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

public class MyDBHandler extends SQLiteOpenHelper  {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pricebookDB.db";
    private static final String TABLE_NAME="Items";
    private static final String COLUMN_MODEL = "ModelNumber";
    private static final String COLUMN_PRICE = "Price";

    public MyDBHandler(@Nullable Context context, @Nullable String name , @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_MODEL + "STRING PRIMARYKEY,"+ COLUMN_PRICE + "INTEGER )";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
   // public String loadHandler(){}

    public void addHandler(Model items) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_MODEL ,items.getModelNumber());
        values.put(COLUMN_PRICE,items.getPrice());
        SQLiteDatabase db =this.getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();

    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Model findHandler(String modelnumber){
        String query = "SELECT "+ COLUMN_PRICE +" FROM " + TABLE_NAME + " WHERE " + COLUMN_MODEL + "=" +"'"+ modelnumber +"'";
        SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            Model model = new Model();
            model = new Model();
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                model.setModelNumber(cursor.getString(0));
                model.setPrice(Integer.parseInt(cursor.getString(1)));
                cursor.close();
            } else {
                model = null;
            }
            db.close();

        return model;

    }
    public boolean deleteHandler(String modelnumber){
        boolean result =false;
        String query = "select * From"+TABLE_NAME + "WHERE"+COLUMN_MODEL+"= '"+String.valueOf(modelnumber+"'");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query ,null);
        Model model=new Model();
        if (cursor.moveToFirst())
        {
            model.setModelNumber(cursor.getString(0));
            db.delete(TABLE_NAME,COLUMN_MODEL+"=?",new String[] {String.valueOf(model.getModelNumber())});
            cursor.close();
            result =true;
        }
    db.close();
        return result;
    }
    public boolean updateHandler(String modelnumber,int price){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues args =new ContentValues();
        args.put(COLUMN_PRICE , price);
        args.put(COLUMN_MODEL,modelnumber);
        return db.update(TABLE_NAME,args,COLUMN_MODEL+"="+modelnumber,null)>0;
    }

}
