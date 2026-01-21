package com.app.register;

public class Product {
    public String name, category, status;
    public int price, stock;

    public Product(String name, int price, String category, int stock, String status) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
        this.status = status;
    }
}
