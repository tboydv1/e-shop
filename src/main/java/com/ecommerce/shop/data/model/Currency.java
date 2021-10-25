package com.ecommerce.shop.data.model;


public enum Currency {

    NGN("Naira"), USD("Dollar"), SRC("Singapore Dollar"),
    GBP("British Pounds"), FRC("Franc"), GHC("Ghana Cedis"),
SRP("Saudi Arabia");
    private String name;
    Currency(String s) {
       this.name = s;
    }

    public String getName(){
        return this.name;
    }
}
