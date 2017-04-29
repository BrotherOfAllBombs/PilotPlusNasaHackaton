package com.nasa.kiev.spaceapps.challenge.pilotplus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nasa.kiev.spaceapps.challenge.pilotplus.model.POIDescription;
import com.nasa.kiev.spaceapps.challenge.pilotplus.model.PointOfInterest;

/**
 *
 */

public class POIInfoActivity extends AppCompatActivity {

    public static final String POINT = "POINT";
    private PointOfInterest pointOfInterest;
    private ImageView image;
    private TextView text;
    private int position;
    private Button button;

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
                drawImage(position);
                position++;
            }
        });

        pointOfInterest = getIntent().getExtras().getParcelable(POINT);
    }

    @Override
    protected void onResume() {
        super.onResume();

        drawImage(0);
        position = 1;
    }

    /*public void passInfo(PointOfInterest point) {
        this.pointOfInterest = point;
        drawImage(0);
        position = 1;
    }*/

    private void drawImage(int i) {
        if (i < pointOfInterest.getDescriptions().size()) {
            POIDescription description = pointOfInterest.getDescriptions().get(i);
            image.setImageResource(description.getImage().getImageId());
            text.setText(description.getText());
        }
    }
}
