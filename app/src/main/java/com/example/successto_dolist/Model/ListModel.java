package com.example.successto_dolist.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class ListModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String todo;
    private Boolean completed;
    private Long Date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public Long getDate() {
        return Date;
    }

    public void setDate(Long date) {
        Date = date;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;


    }

    @Override
    public String toString() {
        return "ListModel{" +
                "id=" + id +
                ", todo='" + todo + '\'' +
                ", completed=" + completed +
                ", Date=" + Date +
                '}';
    }
}
