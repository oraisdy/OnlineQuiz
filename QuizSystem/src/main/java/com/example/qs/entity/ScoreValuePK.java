package com.example.qs.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by phoebegl on 2017/12/11.
 */
@Embeddable
public class ScoreValuePK implements Serializable {

    @Column(name = "exam_id")
    private int examid;

    @Column(name = "question_id")
    private int questionid;

    @Column(name = "Problem_num")
    private int problem_num;

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
        return problem_num;
    }

    public void setProblem_num(int problem_num) {
        this.problem_num = problem_num;
    }
}
