package com.example.qs.entity;

import javax.persistence.*;

/**
 * Created by phoebegl on 2017/12/9.
 */
@Entity
@Table(name = "exam_user")
public class ExamUser {

    @EmbeddedId
    private ExamUserPK id;

    @Column(name = "Score")
    private int score;

    public ExamUserPK getId() {
        return id;
    }

    public void setId(ExamUserPK id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
