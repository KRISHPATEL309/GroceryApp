package com.krish.practices.groceryapp;

public class UserHelperClass {
    String Name;
    String Mobile_num;
    String EmailRegister;
    String PasswordRegister;
    String Uid;

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }
    public UserHelperClass(){

    }

    public UserHelperClass(String uid,String name, String mobile_num, String emailRegister, String passwordRegister) {
        Name = name;
        Mobile_num = mobile_num;
        EmailRegister = emailRegister;
        PasswordRegister = passwordRegister;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobile_num() {
        return Mobile_num;
    }

    public void setMobile_num(String mobile_num) {
        Mobile_num = mobile_num;
    }

    public String getEmailRegister() {
        return EmailRegister;
    }

    public void setEmailRegister(String emailRegister) {
        EmailRegister = emailRegister;
    }

    public String getPasswordRegister() {
        return PasswordRegister;
    }


    public void setPasswordRegister(String passwordRegister) {
        PasswordRegister = passwordRegister;
    }
}
