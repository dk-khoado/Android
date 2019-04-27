package com.example.a1706020040_dokimdangkhoa;

import java.io.Serializable;

public class subjectModel implements Serializable {
    private  int IdUser;
    private int id ;
    private String subjectName;
    private String subjectCode;
    private String credits;
    private String description;
    public subjectModel(){

    }
    public subjectModel(int id, String subjectName, String subjectCode, String credits, String description) {
        this.id = id;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.credits = credits;
        this.description = description;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int idUser) {
        IdUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
