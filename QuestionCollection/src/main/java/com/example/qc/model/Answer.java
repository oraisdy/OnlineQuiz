package com.example.qc.model;

public class Answer {
    private int id;
    private String content;
    private int questionId;
    private int type;

    public Answer(int id, String content, int questionId, int type) {
        this.id = id;
        this.content = content;
        this.questionId = questionId;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
