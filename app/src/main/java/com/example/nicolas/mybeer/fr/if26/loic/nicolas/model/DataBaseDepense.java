package com.example.nicolas.mybeer.fr.if26.loic.nicolas.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DataBaseDepense extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Biere.db";
    public static final String TABLE_NAME = "biere_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "DEGRE";
    public static final String COL_4 = "NOTE";
    public static final String COL_5 = "COMMENT";


    public DataBaseDepense(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,DEGRE REAL,NOTE REAL, COMMENT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(Biere beer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,beer.getNom());
        contentValues.put(COL_3,beer.getDegre());
        contentValues.put(COL_4,beer.getNote());
        contentValues.put(COL_5,beer.getComment());
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public ArrayList<Biere> getAllData(String orderby) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] projection = {
                COL_1,COL_2, COL_3, COL_4, COL_5
        };
        ArrayList<Biere> beers = new ArrayList<>();
        Cursor res = db.query(TABLE_NAME, projection, null, null, null, null, orderby);
        while (res.moveToNext()) {
            beers.add(cursorToBeer(res));
        }
        return beers;
    }

    public Biere getBiere(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] projection = {
                COL_1,COL_2, COL_3, COL_4, COL_5
        };
        String selection = COL_1 + "= ?";
        String[] selectionArgs = {id + ""};
        Cursor res = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);
       res.moveToFirst();
        return cursorToBeer(res);
    }
  
    public boolean updateData(Biere beer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, beer.getId());
        contentValues.put(COL_2,beer.getNom());
        contentValues.put(COL_3,beer.getDegre());
        contentValues.put(COL_4,beer.getNote());
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { beer.getId()+"" });
        return true;
    }

    public Integer deleteData (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id+""});
    }

    public Biere cursorToBeer(Cursor c) {
        return new Biere(
                c.getString(c.getColumnIndex(COL_2)),
                c.getFloat(c.getColumnIndex(COL_3)),
                c.getFloat(c.getColumnIndex(COL_4)),
                c.getInt(c.getColumnIndex(COL_1)),
                c.getString(c.getColumnIndex(COL_5))

        );
    }
}