package com.example.impdates;

public class MyListData{
    private String dob,frequency,name,occasion;

    public MyListData(){

    }
    public MyListData(String dob, String frequency, String name, String occasion) {
        this.dob = dob;
        this.frequency = frequency;
        this.name = name;
        this.occasion = occasion;
    }

    public String getDob() {
        return dob;
    }

    public String getFrequency() {
        return frequency;
    }

    public String getName() {
        return name;
    }

    public String getOccasion() {
        return occasion;
    }
}