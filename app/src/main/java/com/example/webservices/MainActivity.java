package com.example.webservices;

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
import com.neeraj.webservices.R;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    Button btn;
    String url="https://samyak1209.000webhostapp.com/add.php";
    RequestQueue requestQueue;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.editText);
        e2=findViewById(R.id.editText2);
        btn=findViewById(R.id.button);
        requestQueue= Volley.newRequestQueue(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd=new ProgressDialog(MainActivity.this);
                pd.setMessage("Please Wait");
                pd.setCancelable(false);
                pd.show();

                StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();
                        Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                {
                    //init block
                    @Override
                    protected  Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> data=new HashMap<String, String>();
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
