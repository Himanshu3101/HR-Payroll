package com.yoeki.kalpnay.hrporatal.Survey;

public class QuestionModel{

    String question;
    String question1;
    String questionunread;
    String questioncount;
    String answeredquestin;
    String question2;

    public String getQuestioncount() {
        return questioncount;
    }

    public void setQuestioncount(String questioncount) {
        this.questioncount = questioncount;
    }

    public String getAnsweredquestin() {
        return answeredquestin;
    }

    public void setAnsweredquestin(String answeredquestin) {
        this.answeredquestin = answeredquestin;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }
    public String getQuestion1() {
        return question1;
    }
    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getQuestionunread() {
        return questionunread;
    }

    public void setQuestionunread(String questionunread) {
        this.questionunread = questionunread;
    }

    public String getQuestion(){
        return question;
    }

    public void setQuestion(String question){
        this.question = question;
    }
}
