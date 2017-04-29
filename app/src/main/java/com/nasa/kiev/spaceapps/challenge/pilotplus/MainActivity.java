package com.nasa.kiev.spaceapps.challenge.pilotplus;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.google.android.gms.maps.model.SquareCap;
import com.nasa.kiev.spaceapps.challenge.pilotplus.model.PointOfInterest;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener, GoogleMap.OnInfoWindowClickListener {

    private List<PointOfInterest> points = new DataSource().getLists();

    private MapFragment mapFragment;
    private POIInfoFragment infoFragment;
    private Polyline actualWaypoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        infoFragment = (POIInfoFragment) getSupportFragmentManager().findFragmentById(R.id.info);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        for (PointOfInterest poi : points) {
            googleMap.addMarker(new MarkerOptions()
                    .position(poi.getPosition())
                    .title(poi.getTitle()))
                    .setTag(poi);
        }
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnInfoWindowClickListener(this);
        googleMap.setOnMapClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        select(marker);
        return false;
    }

    private void select(Marker marker) {
        final PointOfInterest poi = (PointOfInterest) marker.getTag();
        final List<LatLng> waypoints = poi.getWaypoints();
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                deselect();
                drawWaypoints(googleMap);
            }

            private void drawWaypoints(GoogleMap googleMap) {
                PolylineOptions polyline = new PolylineOptions().width(2).jointType(JointType.ROUND).startCap(new RoundCap()).endCap(new SquareCap()).color(Color.RED);

                for (LatLng point : waypoints) {
                    polyline.add(point);
                }

                actualWaypoints = googleMap.addPolyline(polyline);
            }
        });
    }

    @Override
    public void onMapClick(LatLng latLng) {
        deselect();
        infoFragment.getView().setVisibility(View.GONE);
    }

    private void deselect() {
        if (actualWaypoints != null) {
            actualWaypoints.remove();
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        infoFragment.getView().setVisibility(View.VISIBLE);
        infoFragment.passInfo((PointOfInterest)marker.getTag());

        Intent i = new Intent(this, POIInfoActivity.class);
        i.putExtra(POIInfoActivity.POINT, (PointOfInterest)marker.getTag());
        startActivity(i);
    }
}
