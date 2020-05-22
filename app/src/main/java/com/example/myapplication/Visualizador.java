package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class    Visualizador extends AppCompatActivity implements Response.ErrorListener, Response.Listener<String>{

    ImageView elementovisual;
    TextView cuadronombre2, cuadrodes;
    ProgressBar conexionvisual;
    String urlid,errorco,urlfin;
    RequestQueue queue;
    StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizador);

        elementovisual = findViewById(R.id.elementovisual);
        cuadronombre2 = findViewById(R.id.cuadronombre2);
        cuadrodes = findViewById(R.id.cuadrodesc);
        conexionvisual = findViewById(R.id.conexionvisual);
        errorco = getResources().getString(R.string.errorco);
        urlid = getResources().getString(R.string.urlelemento);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        final int id =bundle.getInt("ID");

        urlfin = urlid+ id;
        queue = Volley.newRequestQueue(this);
        request = new StringRequest(Request.Method.POST, urlfin,this,this){
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("id",String.valueOf(id));
                return params;
            }
        };

        queue.add(request);


    }

    @Override
    public void onErrorResponse(VolleyError error) {
        conexionvisual.setVisibility(View.GONE);
        Toast.makeText(Visualizador.this,errorco,Toast.LENGTH_LONG).show();
        finish();
    }


    @Override
    public void onResponse(String response) {
        conexionvisual.setVisibility(View.GONE);
        try {
            byte[] u = response.toString().getBytes("ISO-8859-1");
            response = new String(u, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObject = new JSONObject(response);
            Picasso.with(this)
                    .load(jsonObject.getString("imag_url"))
                    .into(elementovisual);
            cuadronombre2.setText(jsonObject.getString("name"));
            String text1 = String.format(jsonObject.getString("desc"), "ISO-8859-1");
            cuadrodes.setText(text1);

        }catch (JSONException e){
            Toast.makeText(Visualizador.this,"error en json",Toast.LENGTH_LONG).show();
        }

    }
}
