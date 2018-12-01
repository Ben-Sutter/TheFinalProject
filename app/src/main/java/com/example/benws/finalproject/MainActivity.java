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

        Button ulButton = findViewById(R.id.upperLeftButton);

        ulButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Temp message to remind you this is Top Left", Toast.LENGTH_SHORT).show();
                launchTL();
            }
        });
    }

    private void launchTL() {
        Intent intent = new Intent(this, TopLeft_Activity.class);
        startActivity(intent);
    }
}
