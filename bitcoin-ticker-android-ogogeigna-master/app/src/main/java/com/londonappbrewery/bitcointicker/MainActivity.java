package com.londonappbrewery.bitcointicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    // Constants:
    // 1. TODO: Create the base URL
    // check DOC
    private final String BASE_URL = "https://rest.coinapi.io/v1/exchangerate/BTC/";
    final String API_KEY = "A32181EC-94BE-4E8D-9649-9B197DEF527E";

    // Member Variables:
    TextView mPriceTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        mPriceTextView = (TextView) findViewById(R.id.priceLabel);
        Spinner spinner = (Spinner) findViewById(R.id.currency_spinner);

        // Create an ArrayAdapter using the String array and a spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_array, R.layout.spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);



        // 2. TODO: Set an OnItemSelected listener on the spinner
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                // Retrieve the clicked item
                String currency = (String) adapterView.getItemAtPosition(i);
                Log.d("Bitcoin", "" + currency);

                String finalUrl = BASE_URL + currency + "?apikey=" + API_KEY;
                Log.d("Bitcoin", "Final url is: " + finalUrl);

                letsDoSomeNetworking(finalUrl);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                Log.d("Bitcoin", "Nothing selected");
            }
        });

    }


    // 4. TODO: complete the letsDoSomeNetworking() method
    private void letsDoSomeNetworking(String url) {

        AsyncHttpClient client = new AsyncHttpClient();

        // base url + currency + app key
        client.get(url, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                Log.d("Bitcoin", "Success: JSON: " + response.toString());

                try {

                    String rate = response.getString("rate");

                    mPriceTextView.setText(rate);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Log.e("Bitcoin", "Fail " + throwable.toString());
                Log.d("Bitcoin","Status code "  + statusCode);

                Toast.makeText(MainActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }



}
