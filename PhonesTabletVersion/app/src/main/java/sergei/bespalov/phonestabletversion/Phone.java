package sergei.bespalov.phonestabletversion;

/**
 * Created by Sergei on 07.03.2015.
 */
public class Phone {

    public final String name;
    public final String os;
    public final Double rating;
    public final Integer price;

    public Phone(String name, String os, Double rating, Integer price){
        this.name = name;
        this.os = os;
        this.rating = rating;
        this.price = price;
    }
}
