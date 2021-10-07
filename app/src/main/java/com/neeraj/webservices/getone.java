package com.neeraj.webservices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class getone extends AppCompatActivity {
String url="http://192.168.1.7/Neeraj/getAll.php";
TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getone);
        final RequestQueue requestQueue= Volley.newRequestQueue(this);
        t=findViewById(R.id.js);
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                  //int visiblity=response.getInt("visibility");//jab object ka naam hi na ho to key se call kare.
                    //int coord=response.getJSONObject("main").getInt("pressure");//jab object ka naam ho ase.
                    JSONArray array=new JSONArray();
                    for(int i=0;i<array.length();i++)
                    {
                        int object=array.getJSONObject(i).getInt("id");
                        t.setText(object+"");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(objectRequest);
    }
}
