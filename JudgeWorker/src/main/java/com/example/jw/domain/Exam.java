package com.example.jw.domain;

import java.sql.Timestamp;

public class Exam {
    private Integer Id;
    private String Name;
    private Timestamp start_at;
    private Timestamp end_at;
    private Integer question_count;
    private String Subject;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Timestamp getStart_at() {
        return start_at;
    }

    public void setStart_at(Timestamp start_at) {
        this.start_at = start_at;
    }

    public Timestamp getEnd_at() {
        return end_at;
    }

    public void setEnd_at(Timestamp end_at) {
        this.end_at = end_at;
    }

    public Integer getQuestion_count() {
        return question_count;
    }

    public void setQuestion_count(Integer question_count) {
        this.question_count = question_count;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }
}
