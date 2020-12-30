package com.example.camp_proj1;

public class UserInfo {

    String name;
    String phoneNumber;
    private int photo;

    public UserInfo(String name, String number, int photo){
        this.name = name;
        this.phoneNumber = number;
        this.photo = photo;
    }

    public String getName(){
        return name;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public int getPhoto(){
        return photo;
    }


}
