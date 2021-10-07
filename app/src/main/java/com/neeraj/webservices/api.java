package com.neeraj.webservices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class api extends AppCompatActivity {
TextView t;
Button b;
private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        t=findViewById(R.id.jsona);
        b=findViewById(R.id.parse);
        queue= Volley.newRequestQueue(this);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            jsonparse();
            }
        });

    }
    public void jsonparse()
    {
        final String url="https://api.myjson.com/bins/rry74";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("result");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject obj=jsonArray.getJSONObject(i);
                        int id=obj.getInt("id");
                        String name=obj.getString("name");
                        String course=obj.getString("corse");
                        t.append(id+""+name+""+course+"");
                        Toast.makeText(api.this,""+url,Toast.LENGTH_LONG).show();
                    }
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
        queue.add(request);
    }
}
