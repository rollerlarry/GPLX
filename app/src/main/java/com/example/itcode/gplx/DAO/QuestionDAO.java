package com.example.itcode.gplx.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.itcode.gplx.DTO.Question;
import com.example.itcode.gplx.Helper.DBHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuestionDAO {
    private static DBHelper dbHelper;
    public static String TB_TABLE = "TB_CAUHOI";

    public QuestionDAO(Context context){
        dbHelper = new DBHelper(context);
    }
//    public int countQuestion(int type)
//    {
//        int count=0;
//        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
//        String sqlSelect ="";
//        if (type==0)
//        {
//            Cursor cursor;
//            sqlSelect = "SELECT COUNT(*) FROM '"+TB_TABLE +"'" +
//                    "WHERE ID_LoaiCauHoi= '" +type +"'";
//            cursor = sqLiteDatabase.rawQuery(sqlSelect, null);
//            if (cursor.moveToFirst()){
//                count=cursor.getInt(0);
//            }
//        }
//        else
//            if (type==1)
//            {
//                Cursor cursor;
//                sqlSelect = "SELECT COUNT(*) FROM '"+TB_TABLE +"'" +
//                        "WHERE ID_LoaiCauHoi= '" +type +"'";
//                cursor = sqLiteDatabase.rawQuery(sqlSelect, null);
//                if (cursor.moveToFirst()){
//                    count=cursor.getInt(0);
//                }
//            }
//            else
//                if(type==2)
//                {
//                    Cursor cursor;
//                    sqlSelect = "SELECT COUNT(*) FROM '"+TB_TABLE +"'" +
//                            "WHERE ID_LoaiCauHoi= '" +type +"'";
//                    cursor = sqLiteDatabase.rawQuery(sqlSelect, null);
//                    if (cursor.moveToFirst()){
//                        count=cursor.getInt(0);
//                    }
//                }
//        sqLiteDatabase.close();
//        return count;
//    }
    public int countQuestion(int type)
    {
        int count = 0;
        ArrayList<Question> questionArrayList;
        questionArrayList = getQuestionDatabase(type);
        count = questionArrayList.size();
        return count;
    }
    public ArrayList<Question> getQuestionDatabase(int type)
    {
        ArrayList<Question> questionDatabaseArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        String sqlSelect ="";
        if(type==0)
        {
            Cursor cursor;
            sqlSelect = "SELECT * FROM '"+TB_TABLE +"'" +
                    "WHERE ID_LoaiCauHoi= '" +type +"'";
            cursor = sqLiteDatabase.rawQuery(sqlSelect, null);
            if (cursor.moveToFirst()){
                do {
                    Question question = new Question(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                            cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                            cursor.getString(7), cursor.getString(8), null);

                    questionDatabaseArrayList.add(question);
                }while (cursor.moveToNext());
            }
        }
        else
            if(type==1)
            {
                Cursor cursor;
                sqlSelect = "SELECT * FROM '"+TB_TABLE +"'" +
                        "WHERE ID_LoaiCauHoi= '" +type +"'";
                cursor = sqLiteDatabase.rawQuery(sqlSelect, null);
                if (cursor.moveToFirst()){
                    do {
                        Question question = new Question(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                                cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                                cursor.getString(7), cursor.getString(8), null);

                        questionDatabaseArrayList.add(question);
                    }while (cursor.moveToNext());
                }
            }
            else
                if(type==2)
                {
                    Cursor cursor;
                    sqlSelect = "SELECT * FROM '"+TB_TABLE +"'" +
                            "WHERE ID_LoaiCauHoi= '" +type +"'";
                    cursor = sqLiteDatabase.rawQuery(sqlSelect, null);
                    if (cursor.moveToFirst()){
                        do {
                            Question question = new Question(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                                    cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                                    cursor.getString(7), cursor.getString(8), null);

                            questionDatabaseArrayList.add(question);
                        }while (cursor.moveToNext());
                    }
                }
        sqLiteDatabase.close();
        return questionDatabaseArrayList;
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
            Cursor cursor;
            String sqlSelect0 = "SELECT * FROM '"+TB_TABLE +"'" +
                    "WHERE ID_LoaiCauHoi= '" +type[0] +"' ORDER BY RANDOM() LIMIT 2";
            cursor = sqLiteDatabase.rawQuery(sqlSelect0, null);
            if (cursor.moveToFirst()){
                do {
                    Question question = new Question(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                            cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                            cursor.getString(7), cursor.getString(8), null);

                    questionArrayList.add(question);
                }while (cursor.moveToNext());
            }
            String sqlSelect1 = "SELECT * FROM '"+TB_TABLE +"'" +
                    "WHERE ID_LoaiCauHoi= '" +type[1] +"' ORDER BY RANDOM() LIMIT 2";
            cursor = sqLiteDatabase.rawQuery(sqlSelect1, null);
            if (cursor.moveToFirst()){
                do {
                    Question question = new Question(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                            cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                            cursor.getString(7), cursor.getString(8), null);

                    questionArrayList.add(question);
                }while (cursor.moveToNext());
            }
            String sqlSelect2 = "SELECT * FROM '"+TB_TABLE +"'" +
                    "WHERE ID_LoaiCauHoi= '" +type[2] +"' ORDER BY RANDOM() LIMIT 1";
            cursor = sqLiteDatabase.rawQuery(sqlSelect2, null);
            if (cursor.moveToFirst()){
                do {
                    Question question = new Question(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                            cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                            cursor.getString(7), cursor.getString(8), null);

                    questionArrayList.add(question);
                }while (cursor.moveToNext());
            }
        }
        else
        {
            sqlSelect = "SELECT * FROM '"+TB_TABLE + "'WHERE ID_LoaiCauHoi = '" +typeExam + "'LIMIT 5";
            Cursor cursor = sqLiteDatabase.rawQuery(sqlSelect, null);
            if (cursor.moveToFirst()){
                do {
                    Question question = new Question(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                            cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                            cursor.getString(7), cursor.getString(8), null);

                    questionArrayList.add(question);
                }while (cursor.moveToNext());
            }
        }
        Collections.shuffle(questionArrayList,new Random());
        sqLiteDatabase.close();
        return questionArrayList;
    }
}
