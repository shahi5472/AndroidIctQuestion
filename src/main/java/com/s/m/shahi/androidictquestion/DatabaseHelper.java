package com.s.m.shahi.androidictquestion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "personalnotebook.db";
    public static final String TABLE_NAME = "note";
    public static final String ID = "Id";
    public static final String QUESTION = "Question";
    public static final String CORRECT = "Correct";
    public static final String OPTION1 = "Option1";
    public static final String OPTION2 = "Option2";
    public static final String OPTION3 = "Option3";
    public static final String OPTION4 = "Option4";
    public static final String UNDEFINED = "Undefined";
    public static final int DATABASE_VERSION = 2;

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + QUESTION + " VARCHAR(255) ," + CORRECT + " VARCHAR(255)," + OPTION1 + " VARCHAR(255)," + OPTION2 + " VARCHAR(255)," + OPTION3 + " VARCHAR(255)," + OPTION4 + " VARCHAR(255)," + UNDEFINED + " VARCHAR(255));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            Toast.makeText(context, "Exception : " + e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch (Exception e) {
            Toast.makeText(context, "Exception : " + e, Toast.LENGTH_SHORT).show();
        }
    }

    public boolean check(String qUESTION) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT Question FROM " + TABLE_NAME + " where Question=?", new String[]{qUESTION});
        boolean exists = (cursor.getCount() > 0);
        if (exists) {
            return true;
        } else {
            return false;
        }
    }

    public double saveData(String qUESTION, String cORRECT, String oPTION1, String oPTION2, String oPTION3, String oPTION4, String undefined) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(QUESTION, qUESTION);
        contentValues.put(CORRECT, cORRECT);
        contentValues.put(OPTION1, oPTION1);
        contentValues.put(OPTION2, oPTION2);
        contentValues.put(OPTION3, oPTION3);
        contentValues.put(OPTION4, oPTION4);
        contentValues.put(UNDEFINED, undefined);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return result;
    }

    public Cursor getDataInfo(String sql) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery(sql, null);
    }
}