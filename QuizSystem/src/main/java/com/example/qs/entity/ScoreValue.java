package com.example.qs.entity;

import javax.persistence.*;

/**
 * Created by phoebegl on 2017/12/7.
 * 分值
 */
@Entity
@Table(name = "scorevalue")
@IdClass(ScoreValuePK.class)
public class ScoreValue {

    @Id
    @Column(name = "exam_id")
    private int examid;

    @Id
    @Column(name = "question_id")
    private int questionid;

    @Id
    @Column(name = "Problem_num")
    private int problemnum;


    @Column(name = "Scorevalue")
    private int scorevalue;

    public int getExamid() {
        return examid;
    }

    public void setExamid(int examid) {
        this.examid = examid;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public int getProblemnum() {
        return problemnum;
    }

    public void setProblemnum(int problemnum) {
        this.problemnum = problemnum;
    }

    public int getScorevalue() {
        return scorevalue;
    }

    public void setScorevalue(int scorevalue) {
        this.scorevalue = scorevalue;
    }
}
