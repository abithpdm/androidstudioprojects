package com.vidhu.appzoc.tripper;

/**
 * Created by appzoc13 on 4/12/15.
 */
public class ObjectDrawerITem {
    private  int icon;
    private String name;

    public ObjectDrawerITem(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public ObjectDrawerITem() {
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
