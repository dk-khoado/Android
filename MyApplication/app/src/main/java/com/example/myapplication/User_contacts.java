package com.example.myapplication;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.SerializablePermission;

public class User_contacts  extends  MainActivity{

    private int ID;
    private  String name;
    private  String phoneNumber;
    private String dia_chi;
    private  String city;
    public String getDia_chi() {
        return dia_chi;
    }
    public int getID() {
        return ID;
    }
    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public  User_contacts(int ID,String mName, String mPhoneNu, String DiaChi, String city_name){
        this.ID = ID;
        name = mName;
        phoneNumber = mPhoneNu;
        this.dia_chi = DiaChi;
        this.city = city_name;
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
