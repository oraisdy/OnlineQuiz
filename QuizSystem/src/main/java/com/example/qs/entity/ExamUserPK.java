package com.example.qs.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by phoebegl on 2017/12/11.
 */
@Embeddable
public class ExamUserPK implements Serializable {

    @Column(name = "exam_id")
    private int examid;

    @Column(name = "user_id")
    private int userid;

    public int getExamid() {
        return examid;
    }

    public void setExamid(int examid) {
        this.examid = examid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
