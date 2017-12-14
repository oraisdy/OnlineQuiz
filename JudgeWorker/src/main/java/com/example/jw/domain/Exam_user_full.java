package com.example.jw.domain;

public class Exam_user_full {
    private Integer exam_id;
    private Integer Score;
    private User user;

    public Exam_user_full(Integer exam_id, Integer Score, Integer Id, String Name, String Email, String Role, String Classname) {
        setExam_id(exam_id);
        setScore(Score);
        setUser(new User(Id, Name, Email, Role, Classname));
    }

    public Integer getExam_id() {
        return exam_id;
    }

    public void setExam_id(Integer exam_id) {
        this.exam_id = exam_id;
    }

    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer score) {
        Score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
