package com.cool4code.doncampoapp.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by COOL4CODE TEAM @cool4code on 9/28/14.
 * Indeed DonCampo Team
 * David Alméciga @dagrinchi
 * Marcos Aguilera @marcode_ely
 */

public class DatabaseHandler extends SQLiteOpenHelper{
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "placitadb";
    // Labels table name
    private static final String TABLE_NAME = "units";
    // Labels Table Columns names
    private static final String KEY_ID = "Code";
    private static final String KEY_NAME = "Name";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Getting all labels
     * returns list of labels
     * */
    public List<String> getAllLabels(){
        List<String> labels = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return labels;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*not using yet*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*not using yet*/
    }
}
