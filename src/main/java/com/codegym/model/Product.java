package com.codegym.model;

public class Product {
    private int id;
    private String name;
    private float price;
    private String product_description;
    private String producer;


    public Product(int id, String name, float price, String product_description, String producer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.product_description = product_description;
        this.producer = producer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}
