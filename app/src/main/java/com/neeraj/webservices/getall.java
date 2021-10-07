package com.neeraj.webservices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class getall extends AppCompatActivity {
TextView t;
ListView l;
    RequestQueue mQueue;
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getall);
        l=findViewById(R.id.l);
        //t=findViewById(R.id.t);
        mQueue= Volley.newRequestQueue(this);

        jsonParse();
    }
    private void jsonParse() {

        String url = "https://api.myjson.com/bins/face8";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ArrayList<String> items = new ArrayList<String>();
                            JSONArray jsonArray = response.getJSONArray("Neeraj");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);
                                String firstName = employee.getString("firstname");
                                int age = employee.getInt("age");
                                String mail = employee.getString("mail");
                                items.add(firstName);
                                items.add(String.valueOf(age));
                                items.add(mail);
                                //t.append(firstName + ", " + String.valueOf(age) + ", " + mail + "\n\n");
                            }
                            arrayAdapter=new ArrayAdapter(getall.this,android.R.layout.simple_list_item_1,items);
                            l.setAdapter(arrayAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }

}
