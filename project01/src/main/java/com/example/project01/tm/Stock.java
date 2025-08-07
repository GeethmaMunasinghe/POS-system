package com.example.project01.tm;

import java.time.LocalDateTime;

public class Stock {
    private Integer id;
    private String productId;
    private int quantity;
    private LocalDateTime updateTime;

    public Stock(){

    }

    public Stock(Integer id, String productId, int quantity, LocalDateTime updateTime) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
