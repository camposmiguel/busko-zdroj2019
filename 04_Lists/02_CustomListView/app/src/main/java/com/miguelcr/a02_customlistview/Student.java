package com.miguelcr.a02_customlistview;

public class Student {

    private String name;
    private int age;
    private String photoUrl;

    public Student(String name, int age, String photoUrl) {
        this.name = name;
        this.age = age;
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
