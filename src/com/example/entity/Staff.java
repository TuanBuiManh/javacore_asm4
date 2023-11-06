package com.example.entity;

public class Staff {
    private int staffId;
    private String userName;
    private String passWord;

    public Staff() {
    }

    public Staff(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
