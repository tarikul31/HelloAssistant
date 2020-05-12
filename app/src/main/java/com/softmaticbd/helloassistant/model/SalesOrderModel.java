package com.softmaticbd.helloassistant.model;

public class SalesOrderModel {
    private String Code;
    private String Name;
    private String Size;
    private String Price;
    private String Qty;
    private String Image;

    public SalesOrderModel() {
    }

    public SalesOrderModel(String code, String name, String size, String price) {
        Code = code;
        Name = name;
        Size = size;
        Price = price;
    }

    public SalesOrderModel(String code, String name, String size, String price, String qty, String image) {
        Code = code;
        Name = name;
        Size = size;
        Price = price;
        Qty = qty;
        Image = image;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
