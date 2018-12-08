package com.example.benws.finalproject;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
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

public class BottomRightActivity extends AppCompatActivity {

    private static final String TAG = "FinalProject:TopLeft";
    private static RequestQueue requestQueue;
    JSONObject input;
    double sliderAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_right);

        requestQueue = Volley.newRequestQueue(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final SeekBar simpleSeekBar = findViewById(R.id.seekBar); // initiate the seek bar

        final TextView currPrice = findViewById(R.id.currentPrice);

        simpleSeekBar.setBackgroundColor(Color.GRAY); // green background color for the seek bar

        simpleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                currPrice.setText("Max Price: $" + String.valueOf(progress));
            }

        });
        Button submitButton=(Button)findViewById(R.id.costSubmitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAPICall((double)(simpleSeekBar.getProgress()) / 100.0);
            }
        });
        //URL: http://www.boredapi.com/api/activity?minprice=0&maxprice=
        //API Request: URL + (double)(simpleSeekBar.getProgress() / 100)
    }
    void setTheTextForReal() {
        String randomActivityName;
        String randomActivityType;
        int randomActivityPrice = 0;
        int randomActivityPeople = 0;
        double randomActivityAccess = 0;
        try {
            randomActivityName = input.getString("activity");
            randomActivityType = input.getString("type");
            //The next two lines put the activity in Sentence Case.
            randomActivityType = randomActivityType.toUpperCase();
            randomActivityType = randomActivityType.substring(0,1) + randomActivityType.substring(1).toLowerCase();
            randomActivityPrice = (int)(100 * input.getDouble("price"));
            randomActivityPeople = input.getInt("participants");
            randomActivityAccess = input.getDouble("accessibility");
        } catch (JSONException e) {
            randomActivityName = "ERROR";
            randomActivityType = "ERROR";
        }
        String textInAll =
                randomActivityName + "\nType: " + randomActivityType
                        + "\nPrice: $" + randomActivityPrice
                        + "\nParticipants: " + randomActivityPeople
                        + "\nAccessibility: " + randomActivityAccess;
        ((TextView) findViewById(R.id.priceActivityChoice)).setText(textInAll);
    }


    void startAPICall(double seekbarVal) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://www.boredapi.com/api/activity?minprice=0&maxprice=" + seekbarVal,
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
    }
}

