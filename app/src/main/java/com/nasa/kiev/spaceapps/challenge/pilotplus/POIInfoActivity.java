package com.nasa.kiev.spaceapps.challenge.pilotplus;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.google.android.gms.maps.model.SquareCap;
import com.nasa.kiev.spaceapps.challenge.pilotplus.model.POIDescription;
import com.nasa.kiev.spaceapps.challenge.pilotplus.model.PointOfInterest;

/**
 *
 */

public class POIInfoActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, OnMapReadyCallback {

    public static final String POINT = "POINT";
    public static final int SPEED_TRIANGLE_FACTOR = 3;
    private static final long DATA_ANIMATION_DURATION = 500;
    private PointOfInterest pointOfInterest;
    private ImageView image;
    private TextView title;
    private TextView text;
    private int position;
    private GestureDetectorCompat mDetector;
    private View container;
    private MapFragment mapFragment;
    private Polyline currentWaypoints;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        container = findViewById(R.id.info_container);
        image = (ImageView) findViewById(R.id.info_image);
        title = (TextView) findViewById(R.id.info_title);
        text = (TextView) findViewById(R.id.info_text);
        text.setMovementMethod(new ScrollingMovementMethod());

        /*image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(POIInfoActivity.this, ImageViewActivity.class);
                i.putExtra(ImageViewActivity.IMAGE_ID, pointOfInterest.getDescriptions().get(position).getImage().getImageId());
                startActivity(i);
            }
        });*/

        mDetector = new GestureDetectorCompat(this, this);

        pointOfInterest = getIntent().getExtras().getParcelable(POINT);

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void outputData(int i) {
        POIDescription description = pointOfInterest.getDescriptions().get(i);
        animateToNewTextAndImage(description);
        animateToNewMapContent(description);
    }

    private void animateToNewMapContent(final POIDescription description) {

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                deselect();
                zoomToSpace(googleMap);
                drawWaypoints(googleMap);
            }

            private void deselect() {
                if (currentWaypoints != null) {
                    currentWaypoints.remove();
                    currentWaypoints = null;
                }
            }

            private void zoomToSpace(final GoogleMap googleMap) {
                if (description.getPoints() == null) {
                    zoomToDefaultPlace(googleMap);
                    return;
                }

                final LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();

                for (LatLng point : description.getPoints()) {
                    boundsBuilder.include(point);
                }

                googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 50), new GoogleMap.CancelableCallback() {
                    @Override
                    public void onFinish() {
                        CameraPosition cameraPosition = googleMap.getCameraPosition();
                        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder()
                                                                                                    .target(cameraPosition.target)
                                                                                                    .zoom(cameraPosition.zoom)
                                                                                                    .tilt(description.getTilt())
                                                                                                    .bearing(description.getBearing())
                                                                                                    .build()));
                    }

                    @Override
                    public void onCancel() {

                    }
                });
            }

            private void zoomToDefaultPlace(GoogleMap googleMap) {
                final LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();

                boundsBuilder.include(pointOfInterest.getPosition());

                if (pointOfInterest.getWaypoints() != null) {
                    for (LatLng point : pointOfInterest.getWaypoints()) {
                        boundsBuilder.include(point);
                    }
                }

                googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 50));
            }

            private void drawWaypoints(GoogleMap googleMap) {
                if (description.getWaypoints() == null) {
                    return;
                }

                PolylineOptions polyline = new PolylineOptions().width(2).jointType(JointType.ROUND).startCap(new RoundCap()).endCap(new SquareCap()).color(Color.RED);

                for (LatLng point : description.getWaypoints()) {
                    polyline.add(point);
                }

                currentWaypoints = googleMap.addPolyline(polyline);
            }
        });
    }

    private void animateToNewTextAndImage(final POIDescription description) {
        container.animate()
                .alpha(0f)
                .setDuration(DATA_ANIMATION_DURATION)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        image.setImageResource(description.getImage().getImageId());
                        title.setText(description.getTitle().toUpperCase());
                        text.setText(description.getText());
                        container.setAlpha(1);
                    }
                })
                .start();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    private boolean isLeftToRight(float distanceX) {
        return distanceX > 0;
    }

    private boolean isHorizontalSwipe(float distanceX, float distanceY) {
        return Math.abs(distanceY * SPEED_TRIANGLE_FACTOR) < Math.abs(distanceX);
    }

    private void previous() {
        if (position > 0) {
            position--;
            outputData(position);
        }
    }

    private void next() {
        if (position < pointOfInterest.getDescriptions().size() - 1) {
            position++;
            outputData(position);
        }
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (isHorizontalSwipe(velocityX, velocityY)) {
            if (isLeftToRight(velocityX)) {
                previous();
            } else {
                next();
            }
            return true;
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        /*MarkerOptions markerOptions = new MarkerOptions().position(pointOfInterest.getPosition());
        googleMap.addMarker(markerOptions);

        if (pointOfInterest.getWaypoints() != null) {
            PolylineOptions polyline = new PolylineOptions().width(2).jointType(JointType.ROUND).startCap(new RoundCap()).endCap(new SquareCap()).color(Color.RED);

            for (LatLng point : pointOfInterest.getWaypoints()) {
                polyline.add(point);
            }

            googleMap.addPolyline(polyline);
        }*/
        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                outputData(0);
                position = 0;
            }
        });
    }
}
