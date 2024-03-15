package com.example.TaskProject.entity;

public class Product {
    private int id;

    private String sku;
    private String description;
    private double salesPrice;
    private double purchasePrice;

    public Product(String sku, String description, double salesPrice, double purchasePrice) {
        this.sku = sku;
        this.description = description;
        this.salesPrice = salesPrice;
        this.purchasePrice = purchasePrice;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", description='" + description + '\'' +
                ", salesPrice=" + salesPrice +
                ", purchasePrice=" + purchasePrice +
                '}';
    }
}
