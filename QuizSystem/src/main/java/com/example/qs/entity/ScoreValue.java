package com.example.qs.entity;

/**
 * Created by phoebegl on 2017/12/7.
 * 分值
 */
public class ScoreValue {

    private String id;
    private String examid;
    private String questionid;
    private int value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExamid() {
        return examid;
    }

    public void setExamid(String examid) {
        this.examid = examid;
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
