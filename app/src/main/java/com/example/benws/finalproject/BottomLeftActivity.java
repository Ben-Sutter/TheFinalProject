package com.example.benws.finalproject;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class BottomLeftActivity extends AppCompatActivity {

    private static final String TAG = "FinalProject:TopLeft";

    private static RequestQueue requestQueue;
    private JSONObject input;

    private RadioButton checkedRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestQueue = Volley.newRequestQueue(this);

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
                checkedRadioButton = findViewById(selected);
                int buttonChoice = Integer.parseInt(checkedRadioButton.getText().toString());
                //result.setText(String.valueOf(buttonChoice));
                setPartText(buttonChoice);
            }
        });
    }

    void setPartText(int numPeople) {
        startAPICall(numPeople);
    }

    void setTheTextForReal() {
        String randomPartName;
        int randomPartPrice = 0;
        int randomPartPeople = 0;
        double randomPartAccess = 0;
        try {
            randomPartName = input.getString("activity");
            randomPartPrice = (int)(100 * input.getDouble("price"));
            randomPartPeople = input.getInt("participants");
            randomPartAccess = input.getDouble("accessibility");
        } catch (JSONException e) {
            randomPartName = "ERROR";
        }
        String textInAll =
                randomPartName + "\nPrice: $" + randomPartPrice
                        + "\nParticipants: " + randomPartPeople
                        + "\nAccessibility: " + randomPartAccess;
        ((TextView) findViewById(R.id.partResults)).setText(textInAll);
    }


    void startAPICall(final int numPeople) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://www.boredapi.com/api/activity?participants=" + numPeople,
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

    //Calling to the api
    //String buttonInt = String.valueOf(buttonChoice);
    //URL = "http://www.boredapi.com/api/activity?participants="
    //API call = URL + buttonInt

    //private String callParticipantAPI(final int idNum) {

    //}
}
