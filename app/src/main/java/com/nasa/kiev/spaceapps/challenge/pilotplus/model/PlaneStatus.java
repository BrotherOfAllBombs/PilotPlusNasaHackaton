package com.nasa.kiev.spaceapps.challenge.pilotplus.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class PlaneStatus implements Parcelable {
    private LatLng currentPosition;
    private List<LatLng> waypoints;

    public PlaneStatus(LatLng currentPosition, List<LatLng> waypoints) {
        this.currentPosition = currentPosition;
        this.waypoints = waypoints;
    }

    public LatLng getCurrentPosition() {
        return currentPosition;
    }

    public List<LatLng> getWaypoints() {
        return waypoints;
    }

    protected PlaneStatus(Parcel in) {
        currentPosition = (LatLng) in.readValue(LatLng.class.getClassLoader());
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
        dest.writeValue(currentPosition);
        if (waypoints == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(waypoints);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PlaneStatus> CREATOR = new Parcelable.Creator<PlaneStatus>() {
        @Override
        public PlaneStatus createFromParcel(Parcel in) {
            return new PlaneStatus(in);
        }

        @Override
        public PlaneStatus[] newArray(int size) {
            return new PlaneStatus[size];
        }
    };
}
