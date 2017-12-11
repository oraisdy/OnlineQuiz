package com.example.qs.entity;

import java.util.List;

/**
 * Created by phoebegl on 2017/12/9.
 */
public class Tag {

    private int value;
    private List<String> tags;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
