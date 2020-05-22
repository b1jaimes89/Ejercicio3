package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.modelo.Elemento;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context contexto;
    ArrayList<Elemento> elementos;
    ImageView imagenelemento;



    public Adaptador(Context contexto, ArrayList<Elemento> elementos) {
        this.contexto = contexto;
        this.elementos = elementos;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() { return elementos.size(); }

    @Override
    public Object getItem(int position) { return elementos.get(position); }

    @Override
    public long getItemId(int position) {  return elementos.get(position).getId(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.elemento_lista,null);
        TextView nombreelemento = vista.findViewById(R.id.cuadronombre);
        TextView provedorelemento = vista.findViewById(R.id.cuadroprovedor);
        TextView precioelemetno = vista.findViewById(R.id.cuadroprecio);
        TextView costoelemento  = vista.findViewById(R.id.cuadrocosto);
        imagenelemento = vista.findViewById(R.id.imagenelemento);

        Picasso.with(contexto)
                .load(elementos.get(position).getThumbnail())
                .into(imagenelemento);

        nombreelemento.setText(elementos.get(position).getNombre());
        provedorelemento.setText(contexto.getResources().getString(R.string.de)+" "+elementos.get(position).getProvider());
        precioelemetno.setText(" "+ elementos.get(position).getPrice());
        if (Float.parseFloat(elementos.get(position).getDelivery()) == 0.0){
            costoelemento.setText(contexto.getResources().getString(R.string.gratis));
        }else{
            costoelemento.setText(elementos.get(position).getDelivery());
        }

        return vista;
    }

}
