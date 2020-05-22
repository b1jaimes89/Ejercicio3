package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.modelo.Elemento;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONArray> {

    ListView listado;
    ProgressBar cargandoconexion;

    String urlbase,nombre,thumbnail,price,provider,delivery;
    String errorco;
    int  id;

    RequestQueue queue;
    JsonArrayRequest request;
    ArrayList<Elemento> elementos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listado = findViewById(R.id.listado);
        cargandoconexion =findViewById(R.id.cargandoconexion);

        errorco = getResources().getString(R.string.errorco);

        elementos = new ArrayList<Elemento>();
        queue = Volley.newRequestQueue(this);
        listado = findViewById(R.id.listado);
        urlbase = getResources().getString(R.string.base);
        request = new JsonArrayRequest(Request.Method.GET,urlbase,null,this,this);
        queue.add(request);

        listado.setOnItemClickListener(onClickListView);
        cargandoconexion = findViewById(R.id.cargandoconexion);


    }

    @Override
    public void onErrorResponse(VolleyError error) {
        cargandoconexion.setVisibility(View.GONE);
        Toast.makeText(MainActivity.this,errorco,Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onResponse(JSONArray response) {
        cargandoconexion.setVisibility(View.GONE);
        JSONObject jsonObject;
        //Log.d("RESPUESTA",response.toString());

        try{
            for ( int i = 0; i < response.length(); i++){
                jsonObject = response.getJSONObject(i);
                id = jsonObject.getInt("id");
                nombre = jsonObject.getString("name");
                thumbnail = jsonObject.getString("thumbnail_url");
                price = jsonObject.getString("price");
                provider = jsonObject.getString("provider");
                delivery  = jsonObject.getString("delivery");

                Elemento elemento = new Elemento(id, nombre, thumbnail, price, provider, delivery);
                elementos.add(elemento);
            }

            Adaptador adaptador = new Adaptador (this, elementos);
            listado.setAdapter(adaptador);

        }catch (JSONException e){

        }

    }

    private ListView.OnItemClickListener onClickListView = new  ListView.OnItemClickListener(){
        @Override
        public void onItemClick(final AdapterView<?> adapter, View v, int position, long arg){
            int idelemento = elementos.get(position).getId();
            Bundle bundle = new Bundle();
            bundle.putInt("ID",idelemento);
            Intent intent = new Intent(MainActivity.this, Visualizador.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
}
