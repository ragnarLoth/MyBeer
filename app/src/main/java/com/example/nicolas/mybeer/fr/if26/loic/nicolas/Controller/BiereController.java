package com.example.nicolas.mybeer.fr.if26.loic.nicolas.Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nicolas.mybeer.fr.if26.loic.nicolas.Model.Biere;
import com.example.nicolas.mybeer.fr.if26.loic.nicolas.Utils.DataBaseOpenHelper;

import java.util.ArrayList;

/**
 * Controller inspired by DAO
 * Allow access, modification and creation of Beers from de database
 */
public class BiereController {
    private DataBaseOpenHelper dataBaseOpenHelper;
    private SQLiteDatabase db;

    private static String[] projection = {
            DataBaseOpenHelper.COL_1,
            DataBaseOpenHelper.COL_2,
            DataBaseOpenHelper.COL_3,
            DataBaseOpenHelper.COL_4
    };

    public BiereController(DataBaseOpenHelper dataBaseOpenHelper) {
        this.dataBaseOpenHelper = dataBaseOpenHelper;
        this.db = dataBaseOpenHelper.getWritableDatabase();
    }

    public boolean insertData(Biere beer) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dataBaseOpenHelper.COL_2,beer.getNom());
        contentValues.put(dataBaseOpenHelper.COL_3,beer.getDegre());
        contentValues.put(dataBaseOpenHelper.COL_4,beer.getNote());
        long result = db.insert(dataBaseOpenHelper.TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public ArrayList<Biere> getAllData(String orderby) {
        ArrayList<Biere> beers = new ArrayList<>();
        Cursor res = db.query(DataBaseOpenHelper.TABLE_NAME, projection, null, null, null, null, orderby);
        while (res.moveToNext()) {
            beers.add(cursorToBeer(res));
        }
        return beers;
    }

    public Biere getBiere(int id){
        String selection = DataBaseOpenHelper.COL_1 + "= ?";
        String[] selectionArgs = {id + ""};
        Cursor res = db.query(DataBaseOpenHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        res.moveToFirst();
        return cursorToBeer(res);
    }

    public Integer deleteData (int id) {
        return db.delete(DataBaseOpenHelper.TABLE_NAME, "ID = ?",new String[] {id+""});
    }

    public Biere cursorToBeer(Cursor c) {
        return new Biere(
                c.getString(c.getColumnIndex(DataBaseOpenHelper.COL_2)),
                c.getFloat(c.getColumnIndex(DataBaseOpenHelper.COL_3)),
                c.getFloat(c.getColumnIndex(DataBaseOpenHelper.COL_4)),
                c.getInt(c.getColumnIndex(DataBaseOpenHelper.COL_1))
        );
    }
}
