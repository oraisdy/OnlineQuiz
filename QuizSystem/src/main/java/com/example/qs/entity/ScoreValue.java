package com.example.qs.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by phoebegl on 2017/12/7.
 * 分值
 */
@Entity
@Table(name = "scorevalue")
public class ScoreValue {

    @EmbeddedId
    private ScoreValuePK id;

    @Column(name = "Scorevalue")
    private int scorevalue;



    public ScoreValuePK getId() {
        return id;
    }

    public void setId(ScoreValuePK id) {
        this.id = id;
    }

    public int getScorevalue() {
        return scorevalue;
    }

    public void setScorevalue(int scorevalue) {
        this.scorevalue = scorevalue;
    }

}
