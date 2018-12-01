package com.example.benws.finalproject;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TopLeftActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_left);

        setRandomText();

        //This section of code is used to return to the main activity.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "And we are headed back!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        //Sets button functionality to call new random activity.
        Button newRandom = (Button) findViewById(R.id.newRandomButton);
        newRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRandomText();
            }
        });
    }

    private void setRandomText() {
        String randomActivityText = "";
        ((TextView)findViewById(R.id.randomEventString)).setText(randomActivityText);
    }
}
