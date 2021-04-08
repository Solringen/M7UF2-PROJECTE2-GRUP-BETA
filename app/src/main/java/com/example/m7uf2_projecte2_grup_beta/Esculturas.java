package com.example.m7uf2_projecte2_grup_beta;

import com.google.firebase.firestore.Blob;



public class Esculturas {
    private String id;
    private String titol;
    private String ubicacio;
    private Blob audio;
    private Blob imatge;
    private String descripcion;
    private Artistas artista;
   public  Esculturas(){

   }
    public Esculturas(String id, String titol, String ubicacio, Blob audio, Blob imatge, String descripcion, Artistas artista) {
        this.id = id;
        this.titol = titol;
        this.ubicacio = ubicacio;
        this.audio = audio;
        this.imatge = imatge;
        this.descripcion = descripcion;
        this.artista = artista;
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

    public Blob getAudio() {
        return audio;
    }

    public void setAudio(Blob audio) {
        this.audio = audio;
    }

    public Blob getImatge() {
        return imatge;
    }

    public void setImatge(Blob imatge) {
        this.imatge = imatge;
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

    @Override
    public String toString() {
        return " Esculturas{" +
                "id='" + id + '\'' +
                ",\n titol='" + titol + '\'' +
                ",\n ubicacio='" + ubicacio + '\'' +
                ",\n audio=" + audio +
                ",\n imatge=" + imatge +
                ",\n descripcion='" + descripcion + '\'' +
                ",\n artista=" + artista+
                '}';
    }
}
