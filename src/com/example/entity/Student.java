package com.example.entity;

public class Student {
    private int stId;
    private String stName;
    private String address;
    private String phone;

    public Student() {
    }

    public Student(String stName, String address, String phone) {
        this.stName = stName;
        this.address = address;
        this.phone = phone;
    }

    public int getStId() {
        return stId;
    }

    public void setStId(int stId) {
        this.stId = stId;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
