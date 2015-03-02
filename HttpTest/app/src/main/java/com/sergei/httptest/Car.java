package com.sergei.httptest;

/**
 * Created by sergei on 02.03.15.
 */
public class Car {
    String name;
    String vin;

    public Car(String name,String vin){
        this.name = name;
        this.vin = vin;
    }

    public String getName(){
        return name;
    }

    public String getVin(){
        return vin;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setVin(String vin){
        this.vin = vin;
    }
}
