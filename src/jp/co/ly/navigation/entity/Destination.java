package jp.co.ly.navigation.entity;

import java.io.Serializable;

public class Destination implements Serializable{

    /**
     * serialVersionId
     */
    private static final long serialVersionUID = -9073638945547739051L;
    
    private String name;
    private double lat;
    private double lon;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLon() {
        return lon;
    }
    public void setLon(double lon) {
        this.lon = lon;
    }
    
    
    
}
