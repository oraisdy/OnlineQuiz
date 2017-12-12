package com.example.qs.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by phoebegl on 2017/12/11.
 */
public class ChoicePK implements Serializable{

    private int examid;

    private int userid;

    private int questionid;

    private int answerid;

    private int problemnum;

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

    public int getProblemnum() {
        return problemnum;
    }

    public void setProblemnum(int problemnum) {
        this.problemnum = problemnum;
    }
}
