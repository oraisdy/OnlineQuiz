package com.example.jw.domain;

public class Scorevalue {
    private Integer exam_id;
    private Integer question_id;
    private Integer Scorevalue;
    private Integer Problem_num;

    public Integer getExam_id() {
        return exam_id;
    }

    public void setExam_id(Integer exam_id) {
        this.exam_id = exam_id;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public Integer getScorevalue() {
        return Scorevalue;
    }

    public void setScorevalue(Integer scorevalue) {
        Scorevalue = scorevalue;
    }

    public Integer getProblem_num() {
        return Problem_num;
    }

    public void setProblem_num(Integer problem_num) {
        Problem_num = problem_num;
    }
}
