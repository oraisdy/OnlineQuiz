package com.example.qs.entity;

import java.util.List;

/**
 * Created by phoebegl on 2017/12/9.
 */
public class Quiz {
    private String name;
    private String subject;
    private String start_time;
    private int last_time;
    private String description;
    private int problem_count;
    private List<Tag> score_value;
    private List<String> classes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public int getLast_time() {
        return last_time;
    }

    public void setLast_time(int last_time) {
        this.last_time = last_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProblem_count() {
        return problem_count;
    }

    public void setProblem_count(int problem_count) {
        this.problem_count = problem_count;
    }

    public List<Tag> getScore_value() {
        return score_value;
    }

    public void setScore_value(List<Tag> score_value) {
        this.score_value = score_value;
    }

    public List<String> getClasses() {
        return classes;
    }

    public void setClasses(List<String> classes) {
        this.classes = classes;
    }
}
