package com.example.jw.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Question {
    private int id;
    private String title;
    private Vector<Answer> allAnswers;
    private int answerNumber;
    private String subject;
    private String tag;
    private List<Integer> studentAns;

    public Question(int id, String title, Vector<Answer> allAnswers, int answerNumber, String subject, String tag) {
        this.id = id;
        this.title = title;
        this.allAnswers = allAnswers;
        this.answerNumber = answerNumber;
        this.subject = subject;
        this.tag = tag;
        this.studentAns = new ArrayList<>();
    }

    public Question(int id, String title, int answerNumber, String subject, String tag) {
        this.id = id;
        this.title = title;
        this.answerNumber = answerNumber;
        this.subject = subject;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Vector<Answer> getAllAnswers() {
        return allAnswers;
    }

    public void setAllAnswers(Vector<Answer> allAnswers) {
        this.allAnswers = allAnswers;
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<Integer> getStudentAns() {
        return studentAns;
    }

    public void setStudentAns(List<Integer> studentAns) {
        this.studentAns = studentAns;
    }
}
