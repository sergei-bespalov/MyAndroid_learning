package com.sergei.newsfeed;

import java.io.Serializable;

/**
 * Created by Student_03 on 09.03.2015.
 */
public class News implements Serializable{
    public int id;
    public String title;
    public String description;
    public String image;

    public News(int id, String title, String description, String imageUrl){
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = imageUrl;
    }


}
