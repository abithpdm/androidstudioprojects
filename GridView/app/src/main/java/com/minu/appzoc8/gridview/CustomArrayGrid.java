package com.minu.appzoc8.gridview;

/**
 * Created by appzoc8 on 12/11/15.
 */
public class CustomArrayGrid {
    int image;
    String text;

    public CustomArrayGrid() {
    }

    public CustomArrayGrid(int image, String text) {
        this.image = image;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

