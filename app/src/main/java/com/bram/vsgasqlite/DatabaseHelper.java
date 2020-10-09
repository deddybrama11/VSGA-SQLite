package com.bram.vsgasqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "buku.db";
    public static final String TABLE_NAME = "tb_buku";
    public static final String COL_1 = "id_buku";
    public static final String COL_2 = "nama_buku";
    public static final String COL_3 = "jumlah";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+"("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_2+" TEXT,"+COL_3+" INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public long addBuku(String nama_buku, Integer jumlah){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, nama_buku);
        contentValues.put(COL_3, jumlah);
        long insert = db.insert(TABLE_NAME, null, contentValues);

        return insert;
    }
    public ArrayList<GetSet> getAllBukuList(){
        ArrayList<GetSet> dataBukuList = new ArrayList<>();
        String name = "";
        Integer jml;
        String selectQuery = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c= db.rawQuery(selectQuery,null);
        if (c.moveToFirst()){
            do {
                name = c.getString(c.getColumnIndex(COL_2));
                jml = c.getInt(c.getColumnIndex(COL_3));

                dataBukuList.add(new GetSet(name,jml));
            }
            while (c.moveToNext());
            Log.d("DatabaseHelper.java", "getAllStudentList: "+dataBukuList);
        }
        return dataBukuList;
    }
}
