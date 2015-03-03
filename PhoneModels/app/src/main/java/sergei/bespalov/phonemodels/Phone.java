package sergei.bespalov.phonemodels;

import android.content.Intent;

import java.io.Serializable;

/**
 * Created by Sergei on 04.03.2015.
 */
public class Phone implements Serializable {

    String name;
    Double rating;
    String os;
    Integer price;

    public Phone(String name, Double rating, String os, Integer price){
        this.name = name;
        this.rating = rating;
        this.os = os;
        this.price = price;
    }

}
