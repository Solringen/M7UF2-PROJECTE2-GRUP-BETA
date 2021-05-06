package com.example.m7uf2_projecte2_grup_beta;

import com.google.firebase.firestore.Blob;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.List;


public class Esculturas {
    private String id;
    private String titol;
    private String ubicacio;
    private String descripcion;
    private Artistas artista;

    private List<Blob> fotos;
    private List<Blob> audios;
   public  Esculturas(){
       this.fotos = new ArrayList<Blob>();
   }
    public Esculturas(String id, String titol, String ubicacio,String descripcion, Artistas artista) {
        this.id = id;
        this.titol = titol;
        this.ubicacio = ubicacio;
        this.descripcion = descripcion;
        this.artista = artista;
        this.fotos = new ArrayList<Blob>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getUbicacio() {
        return ubicacio;
    }

    public void setUbicacio(String ubicacio) {
        this.ubicacio = ubicacio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Artistas getArtista() {
        return artista;
    }

    public void setArtista(Artistas artista) {
        this.artista = artista;
    }

    public List<Blob> getFotos() {
        return fotos;
    }

    public void setFotos(List<Blob> fotos) {
        this.fotos = fotos;
    }

    public List<Blob> getAudios() {
        return audios;
    }

    public void setAudios(List<Blob> audios) {
        this.audios = audios;
    }

    @Override
    public String toString() {
        return "Esculturas{" +
                "id='" + id + '\'' +
                ", titol='" + titol + '\'' +
                ", ubicacio='" + ubicacio + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", artista=" + artista +
                ", fotos=" + fotos +
                ", audios=" + audios +
                '}';
    }
}
