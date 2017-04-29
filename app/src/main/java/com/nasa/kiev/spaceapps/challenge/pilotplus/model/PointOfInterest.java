package com.nasa.kiev.spaceapps.challenge.pilotplus.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class PointOfInterest implements Parcelable {
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

    protected PointOfInterest(Parcel in) {
        title = in.readString();
        position = (LatLng) in.readValue(LatLng.class.getClassLoader());
        if (in.readByte() == 0x01) {
            descriptions = new ArrayList<POIDescription>();
            in.readList(descriptions, POIDescription.class.getClassLoader());
        } else {
            descriptions = null;
        }
        if (in.readByte() == 0x01) {
            waypoints = new ArrayList<LatLng>();
            in.readList(waypoints, LatLng.class.getClassLoader());
        } else {
            waypoints = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeValue(position);
        if (descriptions == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(descriptions);
        }
        if (waypoints == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(waypoints);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PointOfInterest> CREATOR = new Parcelable.Creator<PointOfInterest>() {
        @Override
        public PointOfInterest createFromParcel(Parcel in) {
            return new PointOfInterest(in);
        }

        @Override
        public PointOfInterest[] newArray(int size) {
            return new PointOfInterest[size];
        }
    };
}