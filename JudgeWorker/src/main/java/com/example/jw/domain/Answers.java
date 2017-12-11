package com.example.jw.domain;

public class Answers {
    public Integer ID;
    public Integer QuestionID;
    public String Content;
    public Integer Type;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(Integer questionID) {
        QuestionID = questionID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }
}
