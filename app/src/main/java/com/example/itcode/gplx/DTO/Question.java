package com.example.itcode.gplx.DTO;

public class Question {
    private int questionID;
    private int questionTypeID;
    private String textQuestion;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String answerTrue;
    private String imageQuestion;
    private String userAnswer;

    public Question(int questionID, int questionTypeID, String textQuestion, String answerA, String answerB, String answerC, String answerD, String answerTrue, String imageQuestion, String userAnswer) {
        this.questionID = questionID;
        this.questionTypeID = questionTypeID;
        this.textQuestion = textQuestion;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.answerTrue = answerTrue;
        this.imageQuestion = imageQuestion;
        this.userAnswer = userAnswer;
    }

    public Question() {

    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public int getQuestionTypeID() {
        return questionTypeID;
    }

    public void setQuestionTypeID(int questionTypeID) {
        this.questionTypeID = questionTypeID;
    }

    public String getTextQuestion() {
        return textQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public String getAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(String answerTrue) {
        this.answerTrue = answerTrue;
    }

    public String getImageQuestion() {
        return imageQuestion;
    }

    public void setImageQuestion(String imageQuestion) {
        this.imageQuestion = imageQuestion;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}
