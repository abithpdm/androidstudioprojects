package com.minu.appzoc8.complexfragment;

/**
 * Created by appzoc8 on 26/11/15.
 */
public class CustomList {
    String name,phone;
    int image;

    public CustomList() {
    }

    public CustomList(String name, String phone, int image) {
        this.name = name;
        this.phone = phone;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
