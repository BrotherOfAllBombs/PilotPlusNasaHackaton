package com.nasa.kiev.spaceapps.challenge.pilotplus.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 */

public class POIImage implements Parcelable {
    private int imageId;

    public POIImage(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    protected POIImage(Parcel in) {
        imageId = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<POIImage> CREATOR = new Parcelable.Creator<POIImage>() {
        @Override
        public POIImage createFromParcel(Parcel in) {
            return new POIImage(in);
        }

        @Override
        public POIImage[] newArray(int size) {
            return new POIImage[size];
        }
    };
}
