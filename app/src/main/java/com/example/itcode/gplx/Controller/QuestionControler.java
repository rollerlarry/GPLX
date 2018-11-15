package com.example.itcode.gplx.Controller;

import android.content.Context;

import com.example.itcode.gplx.DAO.QuestionDAO;
import com.example.itcode.gplx.DTO.Question;

import java.util.ArrayList;

public class QuestionControler {
    private Context context;
    public QuestionControler(Context context){
        this.context = context;
    }
    public ArrayList<Question> getQuestionList(int typeExam){
        QuestionDAO questionDAO = new QuestionDAO(context);
        return questionDAO.getQuestionList(typeExam);
    }
}
