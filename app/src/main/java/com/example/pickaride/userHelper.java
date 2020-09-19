package com.example.pickaride;

public class userHelper {

    String name,phone,mail,pass;


    public userHelper(String name, String phone, String mail, String pass) {
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.pass = pass;
    }

    public userHelper(String hours) {
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
