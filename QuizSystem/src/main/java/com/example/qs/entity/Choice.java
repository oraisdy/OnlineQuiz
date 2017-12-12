package com.example.qs.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by phoebegl on 2017/12/9.
 */
@Entity
@Table(name = "choice")
public class Choice {

    @EmbeddedId
    private ChoicePK id;

    public ChoicePK getId() {
        return id;
    }

    public void setId(ChoicePK id) {
        this.id = id;
    }
}
