package com.example.shankar.mysqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONObject;

public class DatabaseHelperClass extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sqlitedb.db";
    public final static String TABLE_NAME = "logintable";
    public static final String COL_1 = "USERNAME";
    public static final String COL_2 = "EMAILID";
    public static final String COL_3 = "GENDER";
    public static final String COL_4 = "PASSWORD";


    public DatabaseHelperClass(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqdb) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COL_1 + " TEXT," + COL_2 + " TEXT,"
                + COL_3 + " TEXT,"
                + COL_4 + " TEXT" + ")";
        sqdb.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqdb, int oldVersion, int newVersion) {
        sqdb.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqdb);
    }

    public boolean insertData(String username, String emailid, String gender, String password) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, username);
        contentValues.put(COL_2, emailid);
        contentValues.put(COL_3, gender);
        contentValues.put(COL_4, password);
        Long result = sqdb.insert(TABLE_NAME, null, contentValues);
        if (result == -1) return true;

        return false;
    }

    public Cursor getData(String id) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID='" + id + "'";
        Cursor cursor = sqdb.rawQuery(query, null);
        return cursor;
    }

    public Cursor getAllData() {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        Cursor cursor = sqdb.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }

    public JSONArray getDataJArry() {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        JSONArray jArry = new JSONArray();
        JSONObject jObj = new JSONObject();
        String strQry = "select * FROM "+ TABLE_NAME;
        Cursor cursor =sqdb .rawQuery(strQry, null);
        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    jObj = new JSONObject();
                    jObj.put("user_nm", cursor.getString(0) + "");
                    jObj.put("email_id", cursor.getString(1));
                    jObj.put("gender", cursor.getString(2) + "");
                    jObj.put("password", cursor.getString(3) + "");
                    jArry.put(jObj);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return jArry;
    }
}
