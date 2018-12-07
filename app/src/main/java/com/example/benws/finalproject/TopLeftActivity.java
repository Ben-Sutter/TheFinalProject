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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TopLeftActivity extends AppCompatActivity {

    private static final String TAG = "FinalProject:TopLeft";

    private static RequestQueue requestQueue;


    JSONObject input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestQueue = Volley.newRequestQueue(this);

        setContentView(R.layout.activity_top_left);

        //This section of code is used to return to the main activity.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Sets button functionality to call new random activity.
        Button newRandom = (Button) findViewById(R.id.newRandomButton);
        newRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Start API button clicked");
                setRandomText();

            }
        });

        TextView randomText = findViewById(R.id.randomEventString);
        setRandomText();

    }

    void setRandomText() {
        startAPICall();
    }

    void setTheTextForReal() {
        String randomActivityName;
        int randomActivityPrice = 0;
        int randomActivityPeople = 0;
        int randomActivityAccess = 0;
        try {
            randomActivityName = input.getString("activity");
            randomActivityPrice = 100 * input.getInt("price");
            randomActivityPeople = input.getInt("participants");
            randomActivityAccess = input.getInt("accessibility");
        } catch (JSONException e) {
            randomActivityName = "ERROR";
        }
        String textInAll =
                randomActivityName + "\nPrice: " + randomActivityPrice
                        + "\nParticipants" + randomActivityPeople
                        + "\nAccesibility" + randomActivityAccess;
        ((TextView) findViewById(R.id.randomEventString)).setText(textInAll);
    }


    void startAPICall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://www.boredapi.com/api/activity/",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            input = response;
                            //Toast.makeText(getApplicationContext(), "" + input, Toast.LENGTH_SHORT).show();
                            Log.d(TAG, response.toString());
                            setTheTextForReal();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(final VolleyError error) {
                            Log.w(TAG, error.toString());
                        }
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Ocp-Apim-Subscription-Key", "YOUR_API_KEY");
                    Log.d(TAG, params.toString());
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
