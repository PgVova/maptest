package com.mmh.mapo.ui.dvo;

/**
 * Created by on 21.06.17.
 */

public class MapItemDvo {

    private String name;
    private String address;
    private String distance;
    private String logo;
    private String time;
    private String price;

    public MapItemDvo() {
    }

    public MapItemDvo(String name, String address, String distance, String logo, String time, String price) {
        this.name = name;
        this.address = address;
        this.distance = distance;
        this.logo = logo;
        this.time = time;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDistance() {
        return distance;
    }

    public String getLogo() {
        return logo;
    }

    public String getTime() {
        return time;
    }

    public String getPrice() {
        return price;
    }
}
