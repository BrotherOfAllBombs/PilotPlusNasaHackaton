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
        waypoints.add(new LatLng(50.8, -1.35));
        waypoints.add(new LatLng(49.6, -1.6));//Шербур
        waypoints.add(new LatLng(49.6, -6.5));
        waypoints.add(new LatLng(51.8, -8.3));//Квинстаун
        waypoints.add(new LatLng(41.7, -49.95));
        point.setWaypoints(waypoints);

        List<POIDescription> descriptions = new ArrayList<>();
        List<LatLng> pointsToShow = new ArrayList<>();
        List<LatLng> waypointsToShow = new ArrayList<>();

        pointsToShow.add(new LatLng(30, 0));
        pointsToShow.add(new LatLng(50, -90));
        descriptions.add(new POIDescription("Титаник", "\"Титаник\" - самый известный из трансатлантических пароходов, погибший в результате столкновения с айсбергом в 1912 году. Одной из причин этой катастрофы, к слову, считается рекордное сближение Земли и Луны, вызвавшее очень высокие приливы и позволившие айсбергам уже ранней весной оказаться настолько далеко к югу.", new POIImage(R.drawable.image_1_1_titanik), pointsToShow, null));

        pointsToShow = new ArrayList<>();
        pointsToShow.add(new LatLng(54.5, -5.8));
        pointsToShow.add(new LatLng(54.75, -6));
        descriptions.add(new POIDescription("Строительство", "Титаник строился на верфи Белфаста и был спущен на воду в 1911 году. Корпус корабля скрепляли свыше трёх миллионов заклёпок, 75% из которых были забиты вручную.", new POIImage(R.drawable.image_1_2_building), pointsToShow, null));

        POIDescription descriptionBelfast = new POIDescription("Сердце \"Титаника\"", "«Титаник» был оборудован двумя четырехцилиндровыми паровыми машинами и паровой турбиной. Вся силовая установка обладала мощностью 55 000 л. с. Корабль мог развивать скорость до 23 узлов (42 км/ч).", new POIImage(R.drawable.image_1_3), pointsToShow, null);
        descriptions.add(descriptionBelfast);

        waypointsToShow = new ArrayList<>();
        waypointsToShow.add(new LatLng(50.8, -1.35));
        waypointsToShow.add(new LatLng(49.6, -1.6));//Шербур
        waypointsToShow.add(new LatLng(49.6, -6.5));
        waypointsToShow.add(new LatLng(51.8, -8.3));//Квинстаун
        waypointsToShow.add(new LatLng(41.7, -49.95));
        descriptions.add(new POIDescription("Путешествие", "10 апреля 1912 года \"Титаник\" вышел из Саусгемптона и направился в Нью-Йорк, посетив по пути Шербур (Франция) и Куинстаун (Ирландия). На борту было 1320 пассажиров, включая председателя пароходной компании-владельца Брюса Исмея.", new POIImage(R.drawable.image_1_4), waypointsToShow, waypointsToShow));

        pointsToShow = new ArrayList<>();
        pointsToShow.add(new LatLng(25, 30));
        pointsToShow.add(new LatLng(55, -90));
        waypointsToShow = new ArrayList<>();
        waypointsToShow.add(new LatLng(41, -49));
        waypointsToShow.add(new LatLng(41, -51));
        waypointsToShow.add(new LatLng(43, -51));
        waypointsToShow.add(new LatLng(43, -49));
        waypointsToShow.add(new LatLng(41, -49));
        POIDescription description = new POIDescription("Катастрофа", "14 апреля в 23:39 \"Титаник\" на полном ходу столкнулся в тумане с айсбергом. Спустя почти два часа корабль разломился и ушёл на дно.", new POIImage(R.drawable.image_1_5), pointsToShow, waypointsToShow);
        description.setBearing(270);
        description.setTilt(90);
        descriptions.add(description);

        pointsToShow = new ArrayList<>();
        pointsToShow.add(new LatLng(25, 0));
        pointsToShow.add(new LatLng(55, -60));
        waypointsToShow = new ArrayList<>();
        waypointsToShow.add(new LatLng(41, -49.5));
        waypointsToShow.add(new LatLng(41, -50.5));
        waypointsToShow.add(new LatLng(42, -50.5));
        waypointsToShow.add(new LatLng(42, -49.5));
        waypointsToShow.add(new LatLng(41, -49.5));
        POIDescription descriptionRust = new POIDescription("Обнаружение", "Обломки \"Титаника\" были обнаружены в 1985 году экспедицией Эдвина Балларда. Две половины корпуса лежат на расстоянии в 600 метров друг от друга. \nЕжегодно одна тонна металла \"Титаника\" превращается в ржавчину.", new POIImage(R.drawable.image_1_6_bow), pointsToShow, waypointsToShow);
        descriptions.add(descriptionRust);

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
