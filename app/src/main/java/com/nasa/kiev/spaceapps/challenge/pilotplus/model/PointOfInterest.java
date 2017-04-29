package com.nasa.kiev.spaceapps.challenge.pilotplus.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 *
 */

public class PointOfInterest {
    private String title;
    private LatLng position;
    private List<POIDescription> descriptions;
    private List<LatLng> waypoints;

    public PointOfInterest(String title, LatLng position) {
        this.title = title;
        this.position = position;
    }

    public List<POIDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<POIDescription> descriptions) {
        this.descriptions = descriptions;
    }

    public List<LatLng> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<LatLng> waypoints) {
        this.waypoints = waypoints;
    }

    public String getTitle() {
        return title;
    }

    public LatLng getPosition() {
        return position;
    }
}
