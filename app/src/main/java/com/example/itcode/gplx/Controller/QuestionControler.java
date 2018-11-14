package com.example.itcode.gplx.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.itcode.gplx.DTO.CauHoi;
import com.example.itcode.gplx.Helper.DBHelper;


import java.util.ArrayList;

public class CauHoiControler {
    private DBHelper dbHelper;
    public static String TB_TABLE = "TB_CAUHOI";

    public CauHoiControler(Context context){
        dbHelper = new DBHelper(context);
    }

    public ArrayList<CauHoi> getCauHoi(){
        ArrayList<CauHoi> cauHoiArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        String sqlSelect = "SELECT * FROM "+TB_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(sqlSelect, null);
        if (cursor.moveToFirst()){
            do {
                CauHoi cauHoi = new CauHoi();
                cauHoi.setIdCauHoi(cursor.getInt(0));
                cauHoi.setIdLoaiCauHoi(cursor.getInt(1));
                cauHoi.setCauHoiText(cursor.getString(2));
                cauHoi.setDapAnA(cursor.getString(3));
                cauHoi.setDapAnB(cursor.getString(4));
                cauHoi.setDapAnC(cursor.getString(5));
                cauHoi.setDapAnD(cursor.getString(6));
                cauHoi.setDapAnDung(cursor.getString(7));
                cauHoi.setCauHoiHinhAnh(cursor.getString(8));

                cauHoiArrayList.add(cauHoi);
            }while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return cauHoiArrayList;
    }
}
