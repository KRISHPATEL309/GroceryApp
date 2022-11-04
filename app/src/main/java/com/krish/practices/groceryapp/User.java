package com.krish.practices.groceryapp;

import android.widget.ImageView;

public class User {
    String Categoris_Name;
    int Image;

    public User(){

    }

    public User(String categoris_Name, int image) {
        Categoris_Name = categoris_Name;
        Image = image;
    }


    public String getCategoris_Name() {
        return Categoris_Name;
    }

    public void getCategoris_Name(String categoris_Name) {
        Categoris_Name = categoris_Name;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
