package com.example.qs.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by phoebegl on 2017/12/11.
 */
@Embeddable
public class ChoicePK implements Serializable{

    @Column(name = "examid")
    private int examid;

    @Column(name = "userid")
    private int userid;

    @Column(name = "questionid")
    private int questionid;

    @Column(name = "answerid")
    private int answerid;

    @Column(name = "problem_num")
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
