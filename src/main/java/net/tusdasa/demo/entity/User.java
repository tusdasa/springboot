package net.tusdasa.demo.entity;

import java.util.Date;

public class User {

    private Integer uid;

    private String firstName;

    private String lastName;

    private Date birthday;

    public User() {
    }

    public User(Integer uid, String firstName, String lastName, Date birthday) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
