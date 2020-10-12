package com.example.zoomimage;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button Zoom1, Zoom2, Zoom3;
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private TableLayout tableLayout;
    private ScrollView scrollView;
    private MoveViewTouchListener moveViewTouchListener;
    private float min_zoom_level = 5.0f;
    private float max_zoom_level = 0.1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testlayout);

        table+2Layout = findViewById(R.id.table);
        scrollView = findViewById(R.id.scroll);
        Zoom1 = findViewById(R.id.zoom1x);
        Zoom2 = findViewById(R.id.Zoom2x);
        Zoom3 = findViewById(R.id.Zoom3x);

        Zoom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                min_zoom_level = 2.0f;
                max_zoom_level = 0.1f;
            }
        });

        Zoom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                min_zoom_level = 3.0f;
                max_zoom_level = 0.1f;
            }
        });

        Zoom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                min_zoom_level = 4.0f;
                max_zoom_level = 0.1f;

            }
        });


        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        moveViewTouchListener = new MoveViewTouchListener(tableLayout);

    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        mScaleGestureDetector.onTouchEvent(motionEvent);
        moveViewTouchListener.onTouch(tableLayout, motionEvent);

        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();

            mScaleFactor = Math.max(max_zoom_level,
                    Math.min(mScaleFactor, min_zoom_level));
            tableLayout.setScaleX(mScaleFactor);
            tableLayout.setScaleY(mScaleFactor);
            return true;
        }
    }
}