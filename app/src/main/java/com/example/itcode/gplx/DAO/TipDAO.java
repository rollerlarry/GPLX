package com.example.itcode.gplx.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.itcode.gplx.DTO.Tip;
import com.example.itcode.gplx.Helper.DBHelper;

import java.util.ArrayList;

public class TipDAO {
    private static DBHelper dbHelper;
    public static String TB_TABLE = "TB_MEO";

    public TipDAO(Context context){
        dbHelper = new DBHelper(context);
    }

    public static ArrayList<Tip> getTipList(int typeExam){
        ArrayList<Tip> tipArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        String sqlSelect ="";
        sqlSelect = "SELECT * FROM '"+TB_TABLE + "'WHERE ID_LoaiCauHoi = '" +typeExam+"'";
        //System.out.println(sqlSelect);
        Cursor cursor = sqLiteDatabase.rawQuery(sqlSelect, null);
        if (cursor.moveToFirst()){
            do {
                Tip tip = new Tip(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                        cursor.getString(3), cursor.getBlob(4));
                tipArrayList.add(tip);
                //System.out.println(tip.getImgTip());
            }while (cursor.moveToNext());
        }
        //System.out.println(tipArrayList);
        sqLiteDatabase.close();
        return tipArrayList;
    }
}