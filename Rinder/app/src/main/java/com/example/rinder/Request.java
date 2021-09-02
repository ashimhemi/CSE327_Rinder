package com.example.rinder;

public class Request {
    String Uid,name,gender,sub;

    public Request() {
    }

    public Request(String uid, String name, String gender, String sub) {
        this.Uid = uid;
        this.name = name;
        this.gender = gender;
        this.sub = sub;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
}
