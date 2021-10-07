package com.neeraj.webservices;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class deleted extends AppCompatActivity {
Button b;
EditText e;
String url="http://192.168.1.14/Neeraj/delete.php";
RequestQueue requestQueue;
ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleted);
        b=findViewById(R.id.b);
        e=findViewById(R.id.idd);
        requestQueue= Volley.newRequestQueue(this);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd=new ProgressDialog(deleted.this);
                pd.setMessage("Please wait");
                pd.setCancelable(false);
                pd.show();
                StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();
                        Toast.makeText(deleted.this,"Deleted"+response,Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        Toast.makeText(deleted.this,"Try again"+error,Toast.LENGTH_LONG).show();
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> data=new HashMap<>();
                        data.put("id",e.getText().toString());
                        return data;
                    }
                };
                requestQueue.add(request);
            }
        });

    }
}
