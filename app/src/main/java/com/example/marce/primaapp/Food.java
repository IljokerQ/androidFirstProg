package com.example.marce.primaapp;

public class Food {

    private String nameProductor;
    private int quantity = 0;
    private float price;


    public Food(String nameProductor,float price){
        this.nameProductor = nameProductor;
        this.price = price;
    }



    public String getNameProductor() {
        return nameProductor;
    }

    public void setNameProductor(String nameProductor) {
        this.nameProductor = nameProductor;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        this.quantity++;
    }
    public void decrementQuantity() {
        if(quantity == 0) return;
        this.quantity--;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }






}

