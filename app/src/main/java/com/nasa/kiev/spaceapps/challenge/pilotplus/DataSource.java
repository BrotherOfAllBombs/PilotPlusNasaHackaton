package com.nasa.kiev.spaceapps.challenge.pilotplus;

import com.google.android.gms.maps.model.LatLng;
import com.nasa.kiev.spaceapps.challenge.pilotplus.model.POIDescription;
import com.nasa.kiev.spaceapps.challenge.pilotplus.model.POIImage;
import com.nasa.kiev.spaceapps.challenge.pilotplus.model.PointOfInterest;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class DataSource {
    public List<PointOfInterest> getLists() {
        List<PointOfInterest> points = new ArrayList<>();

        PointOfInterest point;
        point = new PointOfInterest("Titanic", new LatLng(41.726931, -49.948253));

        List<LatLng> waypoints = new ArrayList<>();
        waypoints.add(new LatLng(0, 0));
        waypoints.add(new LatLng(5, -15));
        waypoints.add(new LatLng(10, -20));
        waypoints.add(new LatLng(20, -25));
        waypoints.add(new LatLng(40, -45));
        waypoints.add(new LatLng(41.7, -49.95));
        point.setWaypoints(waypoints);

        List<POIDescription> descriptions = new ArrayList<>();
        descriptions.add(new POIDescription("Some description 1", new POIImage(R.drawable.image_1_1), null));
        descriptions.add(new POIDescription("Some description 2 very very very very very very very very very very very very very very very very very long", new POIImage(R.drawable.image_1_1), null));

        List<LatLng> pointsToShow = new ArrayList<>();
        pointsToShow.add(new LatLng(0, 0));
        pointsToShow.add(new LatLng(5, 5));
        descriptions.add(new POIDescription("Some description 2 very very very very very very very very very very very very very very very very very long", new POIImage(R.drawable.image_1_1), pointsToShow));

        pointsToShow = new ArrayList<>();
        pointsToShow.add(new LatLng(-3, -3));
        pointsToShow.add(new LatLng(-30, -30));
        descriptions.add(new POIDescription("Some description 3 very very very very very very very very very very very very very very very very very long", new POIImage(R.drawable.image_1_1), pointsToShow));

        point.setDescriptions(descriptions);

        points.add(point);

        return points;
    }
}
