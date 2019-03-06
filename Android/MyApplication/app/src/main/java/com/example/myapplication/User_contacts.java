package com.example.myapplication;

public class User_contacts extends  MainActivity{
    private  String name;
    private  String phoneNumber;
    public  User_contacts(String mName, String mPhoneNu){
        name = mName;
        phoneNumber = mPhoneNu;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
