package com.example.myapplication.modelo;

import java.io.Serializable;

public class Elemento implements Serializable {
    int id;
    String nombre;
    String thumbnail;
    String price;
    String provider;
    String delivery;

    public Elemento(int id, String nombre, String thumbnail, String price, String provider, String delivery) {
        this.id = id;
        this.nombre = nombre;
        this.thumbnail = thumbnail;
        this.price = price;
        this.provider = provider;
        this.delivery = delivery;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getThumbnail() { return thumbnail; }

    public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price;    }

    public String getProvider() { return provider; }

    public void setProvider(String provider) { this.provider = provider; }

    public String getDelivery() { return delivery; }

    public void setDelivery(String delivery) { this.delivery = delivery; }
}
