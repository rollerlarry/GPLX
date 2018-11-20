package com.example.itcode.gplx.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.itcode.gplx.DTO.Question;
import com.example.itcode.gplx.Helper.DBHelper;

import java.util.ArrayList;

public class QuestionDAO {
    private static DBHelper dbHelper;
    public static String TB_TABLE = "TB_CAUHOI";

    public QuestionDAO(Context context){
        dbHelper = new DBHelper(context);
    }

    public ArrayList<Question> getQuestionList(int typeExam){
        ArrayList<Question> questionArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        String sqlSelect ="";
        if(typeExam==3)
        {
            int type[] =new int[3];
            type[0]=0;
            type[1]=1;
            type[2]=2;
            sqlSelect ="SELECT * FROM '"+TB_TABLE +"'" +
                    "WHERE ID_LoaiCauHoi= '" +type[0] +"' OR ID_LoaiCauHoi= '" +type[1] +"' OR ID_LoaiCauHoi= '" +type[2]+"'"+
                    "ORDER BY RANDOM() LIMIT 5";
        }
        else
        {
            sqlSelect = "SELECT * FROM '"+TB_TABLE + "'WHERE ID_LoaiCauHoi = '" +typeExam + "'LIMIT 5";

        }
        Cursor cursor = sqLiteDatabase.rawQuery(sqlSelect, null);
        if (cursor.moveToFirst()){
            do {
                Question question = new Question(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                        cursor.getString(7), cursor.getString(8), null);

                questionArrayList.add(question);
            }while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return questionArrayList;
    }
}
