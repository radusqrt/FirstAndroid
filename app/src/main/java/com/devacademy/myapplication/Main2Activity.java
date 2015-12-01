package com.devacademy.myapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private int count = 0;
    private int countSeconds = 0;
    private boolean averageStart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final LinearLayout beginRunLayout = (LinearLayout) findViewById(R.id.beginRun);
        beginRunLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!averageStart) {
                    Thread t = new Thread() {

                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(1000);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            updateAverageTextView();
                                        }
                                    });
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };

                    t.start();
                    averageStart = true;
                }
                count++;
                TextView tv = (TextView) findViewById(R.id.custom_font);
                tv.setText(String.valueOf(count));
                tv.invalidate();
            }
        });

        initTypeFace();
    }

    private void updateAverageTextView()
    {
        double avg = count;
        avg /= (++countSeconds);
        TextView averageTextView = (TextView) findViewById(R.id.averagePace);
        String show = String.valueOf(avg);
        averageTextView.setText(show.substring(0, Math.min(show.length(), 4)));
        averageTextView.invalidate();
    }


    private void initTypeFace() {
        Typeface grunge = Typeface.createFromAsset(getAssets(), "fonts/futura.ttf");
        TextView test = (TextView) findViewById(R.id.custom_font);
        test.setTypeface(grunge);
    }

}
