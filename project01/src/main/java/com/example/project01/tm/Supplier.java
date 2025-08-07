package com.example.project01.tm;

//Supplier Object
public class Supplier {
    private String id;
    private String name;
    private String address;
    private String telNo;

    public Supplier() {

    }

    public Supplier(String id, String name, String address, String telNo) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telNo = telNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }
}
