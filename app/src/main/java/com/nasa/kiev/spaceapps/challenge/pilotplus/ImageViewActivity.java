package com.nasa.kiev.spaceapps.challenge.pilotplus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 *
 */

public class ImageViewActivity extends AppCompatActivity {
    public static final java.lang.String IMAGE_ID = "IMAGE_ID";
    private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        image = (ImageView) findViewById(R.id.image);
        int imageId = getIntent().getExtras().getInt(IMAGE_ID);
        image.setImageResource(imageId);
    }
}
