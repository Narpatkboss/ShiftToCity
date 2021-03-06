package com.example.shiftthecity08;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="HOUSEBHK.db";
    public static final String TABLE_NAME="houseinfo_table";
    public static final String COL_1="ID";
    public static final String COL_2="SmallItems";

    public static final String COL_3="NormalItems";
    public static final String COL_4="BigItems";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,SmallItems TEXT,NormalItems TEXT,BigItems TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Smallitem, String NormalItem, String BigItem, String s)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,Smallitem);
        contentValues.put(COL_3,NormalItem);
        contentValues.put(COL_4,BigItem);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAlldata(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id1,String smallitems1,String normalitems1,String bigitems1){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id1);
        contentValues.put(COL_2,smallitems1);
        contentValues.put(COL_3,normalitems1);
        contentValues.put(COL_4,bigitems1);
        db.update(TABLE_NAME,contentValues,"ID= ?",new String[] {id1});
        return true;

    }
}
