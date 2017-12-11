package com.example.jw.domain;

public class Choice {
    private int ExamId;
    private int UserId;
    private int QuestionId;
    private int AnswerId;
    private int ProblemNum;

    public int getExamId() {
        return ExamId;
    }

    public void setExamId(int examId) {
        ExamId = examId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getQuestionId() {
        return QuestionId;
    }

    public void setQuestionId(int questionId) {
        QuestionId = questionId;
    }

    public int getAnswerId() {
        return AnswerId;
    }

    public void setAnswerId(int answerId) {
        AnswerId = answerId;
    }

    public int getProblemNum() {
        return ProblemNum;
    }

    public void setProblemNum(int problemNum) {
        ProblemNum = problemNum;
    }
}
