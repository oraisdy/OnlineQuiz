package com.example.qs.entity;

import javax.persistence.*;

/**
 * Created by phoebegl on 2017/12/9.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "Role")
    private String role;

    @Column(name = "Classname")
    private String classnum;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getClassnum() {
        return classnum;
    }

    public void setClassnum(String classnum) {
        this.classnum = classnum;
    }
}
