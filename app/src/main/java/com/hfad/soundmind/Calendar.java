package com.hfad.soundmind;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Calendar {

    @Id
    private long id;

    private String month;
    private int year;
    private String reflection;
    private String todolist;
    private String supplies;

    public Calendar(){}

    public Calendar(String month, int year, String reflection, String todolist, String supplies){
        this.month = month;
        this.year = year;
        this.reflection = reflection;
        this.todolist = todolist;
        this.supplies = supplies;
    }

    public String getMonth() { return month; }

    public int getYear() { return year; }

    public String getReflection() { return reflection; }

    public String getTodolist() { return todolist; }

    public String getSupplies() { return supplies; }

    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() { return month + " " + year; }
}
