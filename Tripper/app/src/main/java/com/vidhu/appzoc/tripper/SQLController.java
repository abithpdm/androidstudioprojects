package com.vidhu.appzoc.tripper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

import static com.vidhu.appzoc.tripper.DataBaseHelper.TABLE_SUB_CATEGORY;

/**
 * Created by appzoc13 on 10/12/15.
 */
public class SQLController {
    private DataBaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public SQLController(Context c) {
        context = c;
    }

    public SQLController open() throws SQLException {
        dbHelper = new DataBaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;

    }

    public void close() {
        dbHelper.close();
    }


    public void insertCategory(String name) {
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.CATEGORY_NAME, name);
        database.insert(DataBaseHelper.TABLE_CATEGORY, null, cv);
//        database.close();

    }

    public void insertSubCategory(String categoryId,String subName,String status){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBaseHelper.CATEGORY_ID,categoryId);
        contentValues.put(DataBaseHelper.SUB_CATEGORY_NAME,subName);
        contentValues.put(DataBaseHelper.KEY_STATE,status);
        database.insert(TABLE_SUB_CATEGORY, null, contentValues);

//        database.close();
    }


    public ArrayList<CategoryList> readCategory() {

        String[] allColumns = new String[] { DataBaseHelper.CATEGORY_ID,
                DataBaseHelper.CATEGORY_NAME };

        String[] columns = new String[] { DataBaseHelper.SUB_CATEGORY_NAME };
        ArrayList<CategoryList> list=new ArrayList<>();
        Cursor c = database.query(DataBaseHelper.TABLE_CATEGORY, allColumns, null,
                null, null, null, null);
        if (c != null) {

            c.moveToFirst();

            while (!c.isAfterLast()) {
                int id=c.getInt(0);
                String cItem = c.getString(1);
                Cursor findEntry = database.query(DataBaseHelper.TABLE_SUB_CATEGORY, columns, DataBaseHelper.CATEGORY_ID + "=? and " + DataBaseHelper.KEY_STATE + "=?", new String[]{id + "", "1"}, null, null, null);

                int count=findEntry.getCount();
                CategoryList categoryList=new CategoryList();
                categoryList.setId(id);
                categoryList.setCount(count + "");
                categoryList.setName(cItem);
                list.add(categoryList);
                c.moveToNext();
            }
        }

      return list;
    }

   public ArrayList<SubCategoryList> readSubCategory(String categoryId) {
       String[] Columns = new String[]{DataBaseHelper.SUB_CATEGORY_ID, DataBaseHelper.SUB_CATEGORY_NAME, DataBaseHelper.KEY_STATE};
       ArrayList<SubCategoryList> subList = new ArrayList<>();
//       Cursor cursor = database.query(DataBaseHelper.TABLE_SUB_CATEGORY, Columns, DataBaseHelper.CATEGORY_ID + "= ? ", new String[]{DataBaseHelper.SUB_CATEGORY_NAME}, DataBaseHelper.KEY_STATE, null,null);
       Cursor cursor = database.query(DataBaseHelper.TABLE_SUB_CATEGORY, Columns, DataBaseHelper.CATEGORY_ID + "=? ", new String[]{categoryId}, null, null, null);

//       int count=findEntry.getCount();
       if (cursor != null) {
           cursor.moveToFirst();
           while (!cursor.isAfterLast()) {
               int id = cursor.getInt(0);
              String subName = cursor.getString(1);
               int subStatus = cursor.getInt(2);

               SubCategoryList subCategoryList = new SubCategoryList();

               subCategoryList.setSubCategory_id(id);
               subCategoryList.setSubCategory_name(subName);
               subCategoryList.setStatus(subStatus);

               subList.add(subCategoryList);
               cursor.moveToNext();
           }
       }

       return subList;
  }

public void updateIsChecked(String id,String value){
                database = dbHelper.getWritableDatabase();
                ContentValues newValues = new ContentValues();
                newValues.put(DataBaseHelper.KEY_STATE, value);
                database.update(DataBaseHelper.TABLE_SUB_CATEGORY, newValues, DataBaseHelper.SUB_CATEGORY_ID+" =? ", new String[]{id});
                database.close();
            }

//    public int updateCategory(long memberID, String memberName) {
//        ContentValues cvUpdate = new ContentValues();
//        cvUpdate.put(DataBaseHelper.CATEGORY_NAME, memberName);
//        int i = database.update(DataBaseHelper.TABLE_CATEGORY, cvUpdate,
//                DataBaseHelper.CATEGORY_ID + " = " + memberID, null);
//        return i;
//    }
//
//    public int updateSubCategory(long subMemberId,String subMemberName){
//        ContentValues cvUpdate=new ContentValues();
//    cvUpdate.put(DataBaseHelper.SUB_CATEGORY_NAME,subMemberName);
//    int i=database.update(TABLE_SUB_CATEGORY,cvUpdate,DataBaseHelper.CATEGORY_ID + "=" +subMemberId,null);
//    return i;
//    }

    public int StatusCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SUB_CATEGORY;
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public void deleteCategory(long memberID) {
        database.delete(DataBaseHelper.TABLE_CATEGORY, DataBaseHelper.CATEGORY_ID + "="
                + memberID, null);
    }
    public void deleteSubCategory(long subMemberId){
        database.delete(TABLE_SUB_CATEGORY,DataBaseHelper.SUB_CATEGORY_ID + "=" + subMemberId,null);
    }



}


