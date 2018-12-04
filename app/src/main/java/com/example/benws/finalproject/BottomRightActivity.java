package com.example.benws.finalproject;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class BottomRightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_right);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final SeekBar simpleSeekBar=(SeekBar)findViewById(R.id.seekBar); // initiate the seek bar

        simpleSeekBar.setBackgroundColor(Color.GRAY); // green background color for the seek bar

        simpleSeekBar.setOnSeekBarChangeListener();

        Button submitButton=(Button)findViewById(R.id.costSubmitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "" + simpleSeekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
