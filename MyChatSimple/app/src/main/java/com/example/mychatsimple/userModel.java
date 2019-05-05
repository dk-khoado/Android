package com.example.mychatsimple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class userModel {
   private String userName;
   private String userEmail;
   private String userID;
   private String text;
   private String textID;

    public userModel(String userName, String userEmail, String userID, String text) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userID = userID;
        this.text = text;
    }

    public userModel() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextID() {
        return textID;
    }

    public void setTextID(String textID) {
        this.textID = textID;
    }
}
