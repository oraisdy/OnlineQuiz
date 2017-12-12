package com.example.jw.domain;

public class Choice {
    private int examid;
    private int userid;
    private int questionid;
    private int answerid;
    private int problem_num;

    public int getExamid() {
        return examid;
    }

    public void setExamid(int examid) {
        this.examid = examid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public int getAnswerid() {
        return answerid;
    }

    public void setAnswerid(int answerid) {
        this.answerid = answerid;
    }

    public int getProblem_num() {
        return problem_num;
    }

    public void setProblem_num(int problem_num) {
        this.problem_num = problem_num;
    }
}
