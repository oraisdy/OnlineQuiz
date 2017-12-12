package com.example.qs.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by phoebegl on 2017/12/11.
 */
public class ScoreValuePK implements Serializable {

    private int examid;

    private int questionid;

    private int problemnum;

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

    public int getProblem_num() {
        return problemnum;
    }

    public void setProblem_num(int problem_num) {
        this.problemnum = problem_num;
    }
}
