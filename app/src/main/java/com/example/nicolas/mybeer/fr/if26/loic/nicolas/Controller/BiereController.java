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

    /**
     * Simple constructor
     * @param dataBaseOpenHelper
     */
    public BiereController(DataBaseOpenHelper dataBaseOpenHelper) {
        this.dataBaseOpenHelper = dataBaseOpenHelper;
        this.db = dataBaseOpenHelper.getWritableDatabase();
    }

    /**
     * Insert a beer into the database
     * @param beer
     * @return
     */
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

    /**
     * get all beers from the database
     * @param orderby
     * @return
     */
    public ArrayList<Biere> getAllData(String orderby) {
        ArrayList<Biere> beers = new ArrayList<>();
        Cursor res = db.query(DataBaseOpenHelper.TABLE_NAME, projection, null, null, null, null, orderby);
        while (res.moveToNext()) {
            beers.add(cursorToBeer(res));
        }
        return beers;
    }

    /**
     * Get one beer from the database thanks to its id
     * @param id
     * @return
     */
    public Biere getBiere(int id){
        String selection = DataBaseOpenHelper.COL_1 + "= ?";
        String[] selectionArgs = {id + ""};
        Cursor res = db.query(DataBaseOpenHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        res.moveToFirst();
        return cursorToBeer(res);
    }

    /**
     * Delete a beer from the database
     * @param id
     * @return
     */
    public int deleteData (int id) {
        return db.delete(DataBaseOpenHelper.TABLE_NAME, "ID = ?",new String[] {id+""});
    }

    /**
     * get a cursor, return a beer, as simple as that
     * @param c
     * @return
     */
    private Biere cursorToBeer(Cursor c) {
        return new Biere(
                c.getString(c.getColumnIndex(DataBaseOpenHelper.COL_2)),
                c.getFloat(c.getColumnIndex(DataBaseOpenHelper.COL_3)),
                c.getFloat(c.getColumnIndex(DataBaseOpenHelper.COL_4)),
                c.getInt(c.getColumnIndex(DataBaseOpenHelper.COL_1))
        );
    }
}
