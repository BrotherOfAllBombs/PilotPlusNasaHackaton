package com.nasa.kiev.spaceapps.challenge.pilotplus.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 *
 */

public class POIDescription {
    private List<LatLng> points; //points to center the map on. Null if nothing special to center on
    private POIImage image;
    private String description;

    public POIDescription(String description, POIImage image, List<LatLng> points) {
        this.description = description;
        this.image = image;
        this.points = points;
    }

    public List<LatLng> getPoints() {
        return points;
    }

    public POIImage getImage() {
        return image;
    }

    public String getText() {
        return description;
    }
}
