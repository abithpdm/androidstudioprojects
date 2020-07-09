package com.minu.appzoc8.listviewdb;

/**
 * Created by appzoc8 on 1/12/15.
 */
public class Item {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item() {

    }

    public Item(String name) {

        this.id = id;
        this.name = name;
    }

    private int id;
    private String name;
}
