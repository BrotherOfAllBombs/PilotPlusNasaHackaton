package com.nasa.kiev.spaceapps.challenge.pilotplus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nasa.kiev.spaceapps.challenge.pilotplus.model.POIDescription;
import com.nasa.kiev.spaceapps.challenge.pilotplus.model.PointOfInterest;

/**
 *
 */

public class POIInfoActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    public static final String POINT = "POINT";
    public static final int SPEED_TRIANGLE_FACTOR = 3;
    private PointOfInterest pointOfInterest;
    private ImageView image;
    private TextView text;
    private int position;
    private Button button;
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        image = (ImageView) findViewById(R.id.info_image);
        text = (TextView) findViewById(R.id.info_text);
        button = (Button) findViewById(R.id.info_test_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outputData(position);
                position++;
            }
        });

        mDetector = new GestureDetectorCompat(this, this);

        pointOfInterest = getIntent().getExtras().getParcelable(POINT);

        outputData(0);
        position = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void outputData(int i) {
        POIDescription description = pointOfInterest.getDescriptions().get(i);
        image.setImageResource(description.getImage().getImageId());
        text.setText(description.getText());
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
}
