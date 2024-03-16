package com.example.TaskProject.entity;

public class SupplierClientRequest {
    private int id;

    private String name;

    private String email;

    private String shippingAddress;

    private String phone;
    private String type;

    public SupplierClientRequest(String name, String email, String shippingAddress, String phone, String type) {
        this.name = name;
        this.email = email;
        this.shippingAddress = shippingAddress;
        this.phone = phone;
        this.type = type;
    }

    public SupplierClientRequest() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
