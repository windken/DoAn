package com.example.wind.doan.Home;

/**
 * Created by wind on 9/21/2017.
 */

public class player {

    private String name;
    private  int img;
    private String places;

    public player (String name, int img, String places)
    {
        this.name = name;
        this.img = img;
        this.places = places;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }
}
