package com.example.estudiante.json;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.PrivateKey;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                "api.openweathermap.org/data/2.5/weather?q=London",
                "",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("VOLLEY", response.toString());
                        //extraen los datos response.get...

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VOLLEY", error.toString());

                    }
                }


        );

        requestQueue.add(request);
        requestQueue.start();


        try {

            JSONObject invJson = new JSONObject();
            JSONArray invArray = new JSONArray();


            JSONObject json = new JSONObject(s);
            Log.d("JSONCLASE", json.toString());

            JSONArray arrayContacts = json.getJSONArray("contacts");
            Log.d("JSONCLASE", json.toString());

            for (int i = 0; i < arrayContacts.length(); i++){
                Log.d("JSONCLASE", "Element "+ i + ": " +arrayContacts.get(i).toString());

                JSONObject contactJson = arrayContacts.getJSONObject(i);
                String id = contactJson.getString("id");
                Log.d("JSONCLASE", id);
                String gender = contactJson.getString("gender");
                Log.d("JSONCLASE", gender);
                String address = contactJson.getString("address");
                Log.d("JSONCLASE", address);
                String email = contactJson.getString("email");
                Log.d("JSONCLASE", email);
                String name = contactJson.getString("name");
                Log.d("JSONCLASE", name);

                JSONObject phoneJson = contactJson.getJSONObject("phone");
                String office = phoneJson.getString("office");
                Log.d("JSONCLASE", office);
                String home = phoneJson.getString("home");
                Log.d("JSONCLASE", home);
                String mobile = phoneJson.getString("mobile");
                Log.d("JSONCLASE", mobile);

                //reconstruir JSON

                JSONObject invJsonContact = new JSONObject();
                invJsonContact.accumulate("id", id);
                invJsonContact.accumulate("gender", gender);
                invJsonContact.accumulate("phone", phoneJson);
                invJsonContact.accumulate("address", address);
                invJsonContact.accumulate("email", email);
                invJsonContact.accumulate("name", name);

                Log.d("JSONCLASE", "Reconstruido: " + invJson.toString());

                invArray.put(invJsonContact);


            }

            invJson.accumulate("contacts", invArray);
            Log.d("JSONCLASE", "Reconstruido: " + invJson.toString());




        } catch (JSONException e){}
    }

    String s = "{"+ "\"contacts\": [" +
            "{" +
            "\"id\": \"c200\"," +
            "\"name\": \"Ravi Tamada\"," +
            "\"email\": \"ravi@gmail.com\"," +
            "\"address\": \"xx-xx-xxxx,x - street, x - country\"," +
            "\"gender\" : \"male\"," +
            "\"phone\": {" +
            "    \"mobile\": \"+91 0000000000\"," +
            "    \"home\": \"00 000000\"," +
            "    \"office\": \"00 000000\"" +
            "}" +
            "}," +
            "{" +
            "\"id\": \"c201\"," +
            "\"name\": \"Johnny Depp\"," +
            "\"email\": \"johnny_depp@gmail.com\"," +
            "\"address\": \"xx-xx-xxxx,x - street, x - country\"," +
            "\"gender\" : \"male\"," +
            "\"phone\": {" +
            "    \"mobile\": \"+91 0000000000\"," +
            "    \"home\": \"00 000000\"," +
            "    \"office\": \"00 000000\"" +
            "}" +
            "}" +
            "" +
            "  ]" +
            "}";
}
