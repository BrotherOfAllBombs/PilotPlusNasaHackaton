package com.nasa.kiev.spaceapps.challenge.pilotplus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nasa.kiev.spaceapps.challenge.pilotplus.model.POIDescription;
import com.nasa.kiev.spaceapps.challenge.pilotplus.model.PointOfInterest;

/**
 *
 */

public class POIInfoFragment extends Fragment {

    private int position = 0;
    private PointOfInterest pointOfInterest;

    private ImageView image;
    private TextView text;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_info, container, false);
        image = (ImageView) result.findViewById(R.id.info_image);
        text = (TextView) result.findViewById(R.id.info_text);
        button = (Button) result.findViewById(R.id.info_test_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawImage(position);
                position++;
            }
        });
        return result;
    }

    public void passInfo(PointOfInterest point) {
        this.pointOfInterest = point;
        drawImage(0);
        position = 1;
    }

    private void drawImage(int i) {
        if (i < pointOfInterest.getDescriptions().size()) {
            POIDescription description = pointOfInterest.getDescriptions().get(i);
            image.setImageResource(description.getImage().getImageId());
            text.setText(description.getText());
        }
    }
}
