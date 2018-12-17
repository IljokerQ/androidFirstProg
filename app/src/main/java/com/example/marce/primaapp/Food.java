package com.example.marce.primaapp;

public class Food {
    private String nameProductor;

    private String price;



    public Food(String nameProductor,String price){
        this.nameProductor = nameProductor;

        this.price = price;
    }
    public void setNameProductor(String name){
        this.nameProductor = name;

    }
    public String getNameProductor(){
        return nameProductor;
    }

    public void setPrice(String p){
        this.price=p;
    }
    public String getPrice(){
        return price;
    }

}

