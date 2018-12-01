package com.example.benws.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button tlButton = findViewById(R.id.upperLeftButton);

        tlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchTL();
            }
        });

        Button trButton = findViewById(R.id.upperRightButton);

        trButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Temp message to remind you this is Top Right", Toast.LENGTH_SHORT).show();
                launchTR();
            }
        });

        Button blButton = findViewById(R.id.bottomLeftButton);

        blButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Temp message to remind you this is Bottom Left", Toast.LENGTH_SHORT).show();
                launchBL();
            }
        });

        Button brButton = findViewById(R.id.bottomRightButton);

        brButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Temp message to remind you this is Bottom Right", Toast.LENGTH_SHORT).show();
                launchBR();
            }
        });
    }

    private void launchTL() {
        Intent intent = new Intent(this, TopLeftActivity.class);
        startActivity(intent);
    }
    private void launchTR() {
        Intent intent = new Intent(this, TopRightActivity.class);
        startActivity(intent);
    }
    private void launchBL() {
        Intent intent = new Intent(this, BottomLeftActivity.class);
        startActivity(intent);
    }
    private void launchBR() {
        Intent intent = new Intent(this, BottomRightActivity.class);
        startActivity(intent);
    }
}
