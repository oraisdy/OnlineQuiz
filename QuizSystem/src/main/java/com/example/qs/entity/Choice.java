package com.example.qs.entity;

import javax.persistence.*;

/**
 * Created by phoebegl on 2017/12/9.
 */
@Entity
@Table(name = "choice")
@IdClass(ChoicePK.class)
public class Choice {

    @Id
    @Column(name = "examid")
    private int examid;

    @Id
    @Column(name = "userid")
    private int userid;

    @Id
    @Column(name = "questionid")
    private int questionid;

    @Id
    @Column(name = "answerid")
    private int answerid;

    @Id
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
