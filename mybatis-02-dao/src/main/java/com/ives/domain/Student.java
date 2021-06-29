package com.ives.domain;

public class Student {
    private Integer id;
    private String name;
    private String email;
    private Integer age;

    public Student() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String toString() {
        return "學生實體的信息Student{id=" + this.id + ", name='" + this.name + '\'' + ", email='" + this.email + '\'' + ", age=" + this.age + '}';
    }
}
