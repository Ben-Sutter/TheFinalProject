package com.example.benws.finalproject;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TopRightActivity extends AppCompatActivity {

    private static final String TAG = "FinalProject:TopLeft";

    private static RequestQueue requestQueue;
    private JSONObject input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_right);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Button submitButton = (Button) findViewById(R.id.typeSubmitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String spinnerText = spinner.getSelectedItem().toString();
                setPartText(spinnerText);
            }
        });
    }

    void setPartText(String typeText) {
        startAPICall(typeText);
    }

    void setTheTextForReal() {
        String randomPartName;
        int randomPartPrice = 0;
        int randomPartPeople = 0;
        int randomPartAccess = 0;
        try {
            randomPartName = input.getString("activity");
            randomPartPrice = 100 * input.getInt("price");
            randomPartPeople = input.getInt("participants");
            randomPartAccess = input.getInt("accessibility");
        } catch (JSONException e) {
            randomPartName = "ERROR";
        }
        String textInAll =
                randomPartName + "\nPrice: $" + randomPartPrice
                        + "\nParticipants: " + randomPartPeople
                        + "\nAccessibility: " + randomPartAccess;
        ((TextView) findViewById(R.id.typeResult)).setText(textInAll);
    }


    void startAPICall(final String typeText) {
        final String TYPE_URL = "http://www.boredapi.com/api/activity?type=" + typeText;
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://www.boredapi.com/api/activity?type=" + typeText,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            input = response;
                            Toast.makeText(getApplicationContext(), "" + input, Toast.LENGTH_SHORT).show();
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


    //URL: "http://www.boredapi.com/api/activity?type=recreational"
    //API request: URL + spinner.getSelectedItem().toString()
}
