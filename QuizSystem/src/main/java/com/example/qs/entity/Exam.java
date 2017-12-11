package com.example.qs.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by phoebegl on 2017/12/7.
 */
@Entity
@Table(name = "exam")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "start_at")
    private String startat;

    @Column(name = "end_at")
    private String endat;

    @Column(name = "question_count")
    private int questionCount;

    @Column(name = "Subject")
    private String subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartat() {
        return startat;
    }

    public void setStartat(String startat) {
        this.startat = startat;
    }

    public String getEndat() {
        return endat;
    }

    public void setEndat(String endat) {
        this.endat = endat;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
