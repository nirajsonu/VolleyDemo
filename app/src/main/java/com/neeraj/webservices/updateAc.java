package com.neeraj.webservices;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class updateAc extends AppCompatActivity {
EditText e,e1,e2;
Button b;
    RequestQueue requestQueue;
    ProgressDialog pd;
    String url="https://nirajsonu.000webhostapp.com/update.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        e=findViewById(R.id.editText);
        e1=findViewById(R.id.editText2);
        e2=findViewById(R.id.editText3);
        requestQueue= Volley.newRequestQueue(this);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd=new ProgressDialog(updateAc.this);
                pd.setMessage("Please wait");
                pd.setCancelable(false);
                pd.show();
                StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();
                        Toast.makeText(updateAc.this, "" + response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(updateAc.this, "" + error, Toast.LENGTH_LONG).show();
                    }
                })
                {
                    @Override
                    protected Map<String, String> getPostParams() throws AuthFailureError {
                        Map<String,String> data=new HashMap<String, String>();
                        data.put("id",e.getText().toString());
                        data.put("name",e1.getText().toString());
                        data.put("course",e2.getText().toString());
                        return data;
                    }
                };
                requestQueue.add(request);

            }
        });
    }
}
