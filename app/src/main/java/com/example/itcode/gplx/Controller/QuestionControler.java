package com.example.itcode.gplx.Controller;

import android.content.Context;

import com.example.itcode.gplx.DAO.QuestionDAO;
import com.example.itcode.gplx.DTO.Question;

import java.util.ArrayList;

public class QuestionControler {
    private Context context;
    private QuestionDAO questionDAO;

    public QuestionControler(Context context){
        this.context = context;
    }

    public ArrayList<Question> getQuestionList(int typeExam){
        questionDAO = new QuestionDAO(context);
        return questionDAO.getQuestionList(typeExam);
    }

    public ArrayList<Question> getQuestionListNotRandom(int typeExam){
        questionDAO = new QuestionDAO(context);
        return questionDAO.getQuestionDatabase(typeExam);
    }

    public int getStateQuestionSave(int idQuestion){
        questionDAO = new QuestionDAO(context);
        return questionDAO.getStateQuestionSave(idQuestion);
    }

    public void setStateQuestionSave(int state, String idQuestion){
        questionDAO = new QuestionDAO(context);
        questionDAO.setStateQuestionSave(state, idQuestion);
    }

    public int getQuestionCountNumber(int questionType){
        questionDAO = new QuestionDAO(context);
        return questionDAO.getQuestionCountNumber(questionType);
    }

    public ArrayList<Question> getSaveQuestionList(){
        questionDAO = new QuestionDAO(context);
        return questionDAO.getSaveQuestionList();
    }

    public boolean deleteSaveQuestion(String questionID){
        questionDAO = new QuestionDAO(context);
        return questionDAO.deleteSaveQuestion(questionID);
    }
}
