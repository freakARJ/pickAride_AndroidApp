package com.example.pickaride;

public class userhelper2 {

    String hours;
    String address;
    String phone;
    String unit;


    public userhelper2(String hours,String address,String phone,String unit) {
        this.hours = hours;
        this.address=address;
        this.phone=phone;
        this.unit=unit;

    }
    public userhelper2(){

    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
