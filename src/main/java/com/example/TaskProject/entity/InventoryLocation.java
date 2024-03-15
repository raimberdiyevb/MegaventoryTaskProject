package com.example.TaskProject.entity;

public class InventoryLocation {
    private int id;
    private String abbreviation;
    private String name;
    private String address;

    public InventoryLocation(String abbreviation, String name, String address) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.address = address;
    }

    public InventoryLocation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
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

    @Override
    public String toString() {
        return "InventoryLocation{" +
                "id=" + id +
                ", abbreviation='" + abbreviation + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
