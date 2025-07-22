package com.example.project01.tm;

public class Product {
    private String id;
    private String name;
    private String description;
    private int unit_price;

    public Product() {

    }

    public Product(String id, String name, String description, int unit_price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unit_price = unit_price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(int unit_price) {
        this.unit_price = unit_price;
    }
}
