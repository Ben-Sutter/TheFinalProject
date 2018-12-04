package com.example.benws.finalproject;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TopLeftActivity extends AppCompatActivity {

    private static RequestQueue requestQueue;

    String string = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestQueue = Volley.newRequestQueue(this);

        setContentView(R.layout.activity_top_left);

        setRandomText();

        //This section of code is used to return to the main activity.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "And we are headed back!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        //Sets button functionality to call new random activity.
        Button newRandom = (Button) findViewById(R.id.newRandomButton);
        newRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"it clicked", Toast.LENGTH_SHORT).show();
                setRandomText();
            }
        });

        TextView randomText = findViewById(R.id.randomEventString);

    }

    private void setRandomText() {
        startAPICall();
        String randomActivityText = string;
        ((TextView)findViewById(R.id.randomEventString)).setText(randomActivityText);
    }


    void startAPICall() {
        Toast.makeText(getApplicationContext(),"in api", Toast.LENGTH_SHORT).show();
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://www.boredapi.com/api/activity/",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            Toast.makeText(getApplicationContext(),"made it", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                            string += response.toString();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(final VolleyError error) {
                            Toast.makeText(getApplicationContext(),"Object request error", Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Ocp-Apim-Subscription-Key", "YOUR_API_KEY");
                    return params;
                }
            };
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //URL http://www.boredapi.com/api/activity
    }
}
