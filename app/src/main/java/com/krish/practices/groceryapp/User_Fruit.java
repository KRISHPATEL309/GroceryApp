package com.krish.practices.groceryapp;

public class User_Fruit {
    String Fruit_Name,Price,Quantity;
    String Image;

    public User_Fruit(){

    }

        public User_Fruit(String fruit_Name, String price, String quantity, String image) {
        Fruit_Name = fruit_Name;
        Price = price;
        Quantity = quantity;
        Image = image;
    }

    public String getFruit_Name() {
        return Fruit_Name;
    }

    public void setFruit_Name(String fruit_Name) {
        Fruit_Name = fruit_Name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }


    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
