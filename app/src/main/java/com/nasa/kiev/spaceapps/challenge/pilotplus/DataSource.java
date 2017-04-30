package com.nasa.kiev.spaceapps.challenge.pilotplus;

import com.google.android.gms.maps.model.LatLng;
import com.nasa.kiev.spaceapps.challenge.pilotplus.model.POIDescription;
import com.nasa.kiev.spaceapps.challenge.pilotplus.model.POIImage;
import com.nasa.kiev.spaceapps.challenge.pilotplus.model.PlaneStatus;
import com.nasa.kiev.spaceapps.challenge.pilotplus.model.PointOfInterest;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class DataSource {
    public static List<PointOfInterest> getLists() {
        List<PointOfInterest> points = new ArrayList<>();

        PointOfInterest point;
        point = new PointOfInterest("Последнее путешествие \"Титаника\"", new LatLng(41.726931, -49.948253));

        List<LatLng> waypoints = new ArrayList<>();
        waypoints.add(new LatLng(0, 0));
        waypoints.add(new LatLng(5, -15));
        waypoints.add(new LatLng(10, -20));
        waypoints.add(new LatLng(20, -25));
        waypoints.add(new LatLng(40, -45));
        waypoints.add(new LatLng(41.7, -49.95));
        point.setWaypoints(waypoints);

        List<POIDescription> descriptions = new ArrayList<>();
        List<LatLng> pointsToShow = new ArrayList<>();
        List<LatLng> waypointsToShow = new ArrayList<>();

        pointsToShow.add(new LatLng(30, -35));
        pointsToShow.add(new LatLng(50, -55));
        descriptions.add(new POIDescription("Title 1", "Some description 1", new POIImage(R.drawable.image_1_1), pointsToShow, null));

        pointsToShow = new ArrayList<>();
        pointsToShow.add(new LatLng(54.5, -5.8));
        pointsToShow.add(new LatLng(54.75, -6));
        descriptions.add(new POIDescription("Строительство", "Титаник строился на верфи Белфаста и был спущен на воду в 1911 году. Корпус корабля скрепляли свыше трёх миллионов заклёпок, 75% из которых были забиты вручную.", new POIImage(R.drawable.image_1_2_building), pointsToShow, null));

        pointsToShow = new ArrayList<>();
        pointsToShow.add(new LatLng(0, 0));
        pointsToShow.add(new LatLng(5, 5));
        descriptions.add(new POIDescription("Сердце \"Титаника\"", "«Титаник» был оборудован двумя четырехцилиндровыми паровыми машинами и паровой турбиной. Вся силовая установка обладала мощностью 55 000 л. с. Корабль мог развивать скорость до 23 узлов (42 км/ч)", new POIImage(R.drawable.image_1_3), pointsToShow, pointsToShow));

        pointsToShow = new ArrayList<>();
        pointsToShow.add(new LatLng(-3, -3));
        pointsToShow.add(new LatLng(-30, -30));
        descriptions.add(new POIDescription("Title 4", "Some description 4 very very very very very very very very very very very very very very very very very long", new POIImage(R.drawable.image_1_4), pointsToShow, pointsToShow));

        pointsToShow = new ArrayList<>();
        pointsToShow.add(new LatLng(30, -35));
        pointsToShow.add(new LatLng(50, -55));
        waypointsToShow = new ArrayList<>();
        waypointsToShow.add(new LatLng(40, -43));
        waypointsToShow.add(new LatLng(40, -47));
        waypointsToShow.add(new LatLng(43, -47));
        waypointsToShow.add(new LatLng(43, -43));
        waypointsToShow.add(new LatLng(40, -43));
        POIDescription description = new POIDescription("Title 5", "Crash site", new POIImage(R.drawable.image_1_5), pointsToShow, waypointsToShow);
        description.setBearing(270);
        description.setTilt(90);
        descriptions.add(description);

        point.setDescriptions(descriptions);

        points.add(point);

        return points;
    }

    public static PlaneStatus getPlaneStatus() {
        LatLng currentPosition = new LatLng(41.5, -50.1);
        List<LatLng> waypoints = new ArrayList<>();
        waypoints.add(new LatLng(40.7, -74));
        waypoints.add(new LatLng(42, -50));
        waypoints.add(new LatLng(42, -30));
        waypoints.add(new LatLng(40.4, -3.7));
        return new PlaneStatus(currentPosition, waypoints);
    }
}
