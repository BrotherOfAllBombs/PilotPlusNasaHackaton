package com.nasa.kiev.spaceapps.challenge.pilotplus.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class POIDescription implements Parcelable {
    private List<LatLng> points; //points to center the map on. Null if nothing special to center on
    private List<LatLng> waypoints; //points to draw a polyline
    private float tilt = 0;
    private float bearing = 0;
    private POIImage image;
    private String title;
    private String description;

    public POIDescription(String title, String description, POIImage image, List<LatLng> points, List<LatLng> waypoints) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.points = points;
        this.waypoints = waypoints;
    }

    public String getTitle() {
        return title;
    }

    public List<LatLng> getPoints() {
        return points;
    }

    public List<LatLng> getWaypoints() {
        return waypoints;
    }

    public POIImage getImage() {
        return image;
    }

    public String getText() {
        return description;
    }

    public float getTilt() {
        return tilt;
    }

    public void setTilt(float tilt) {
        this.tilt = tilt;
    }

    public float getBearing() {
        return bearing;
    }

    public void setBearing(float bearing) {
        this.bearing = bearing;
    }

    protected POIDescription(Parcel in) {
        if (in.readByte() == 0x01) {
            points = new ArrayList<LatLng>();
            in.readList(points, LatLng.class.getClassLoader());
        } else {
            points = null;
        }
        if (in.readByte() == 0x01) {
            waypoints = new ArrayList<LatLng>();
            in.readList(waypoints, LatLng.class.getClassLoader());
        } else {
            waypoints = null;
        }
        tilt = in.readFloat();
        bearing = in.readFloat();
        image = (POIImage) in.readValue(POIImage.class.getClassLoader());
        title = in.readString();
        description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (points == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(points);
        }
        if (waypoints == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(waypoints);
        }
        dest.writeFloat(tilt);
        dest.writeFloat(bearing);
        dest.writeValue(image);
        dest.writeString(title);
        dest.writeString(description);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<POIDescription> CREATOR = new Parcelable.Creator<POIDescription>() {
        @Override
        public POIDescription createFromParcel(Parcel in) {
            return new POIDescription(in);
        }

        @Override
        public POIDescription[] newArray(int size) {
            return new POIDescription[size];
        }
    };
}