package com.example.benws.finalproject;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BottomLeftActivity extends AppCompatActivity {

    final int ID_SUBTRACT = 2131296385;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_left);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "And we are headed back!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        final TextView result = findViewById(R.id.partResults);

        RadioButton button1 = findViewById(R.id.radioButton1);
        RadioButton button2 = findViewById(R.id.radioButton2);
        RadioButton button3 = findViewById(R.id.radioButton3);
        RadioButton button4 = findViewById(R.id.radioButton4);

        final RadioGroup g = findViewById(R.id.radioGroup);

        Button sub = findViewById(R.id.participantsSubmitButton);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selected = g.getCheckedRadioButtonId();
                RadioButton checkedRadioButton = findViewById(selected);
                int buttonChoice = Integer.parseInt(checkedRadioButton.getText().toString());
                result.setText(String.valueOf(buttonChoice));
            }
        });
    }

    //Calling to the api
    //String buttonInt = String.valueOf(buttonChoice);
    //URL = "http://www.boredapi.com/api/activity?participants="
    //API call = URL + buttonInt

    //private String callParticipantAPI(final int idNum) {

    //}
}
