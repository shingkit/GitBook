package com.shingkit.gitbook.rxAndroid;

import java.util.List;

/**
 * Created by sj on 2015/12/13 0013.
 */
public class Person {
    private String name;
    private int age;
    private List<Course>list;

    public Person() {
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

    public List<Course> getList() {
        return list;
    }

    public void setList(List<Course> list) {
        this.list = list;
    }

    public Person(String name, int age, List<Course> list) {
        this.name = name;
        this.age = age;
        this.list = list;
    }

    public class Course {
        private String title;

        public Course(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
