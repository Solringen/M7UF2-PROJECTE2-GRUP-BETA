package com.example.m7uf2_projecte2_grup_beta;

import com.google.firebase.firestore.Blob;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.List;


public class Esculturas {
    private String id;
    private String titol;
    private String ubicacio;
    private  String any;
    private String material;
    private String alcada;
    private  String amplada;
    private  String pes;
    private String idArtista;

    private List<Blob> fotos;
    private List<Blob> audios;
   public  Esculturas(){
       this.fotos = new ArrayList<Blob>();
   }

    public Esculturas(String id, String titol, String ubicacio, String any, String material, String alcada, String amplada, String pes, String idArtista) {
        this.id = id;
        this.titol = titol;
        this.ubicacio = ubicacio;
        this.any = any;
        this.material = material;
        this.alcada = alcada;
        this.amplada = amplada;
        this.pes = pes;
        this.idArtista = idArtista;
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

    public String getAny() {
        return any;
    }

    public void setAny(String any) {
        this.any = any;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getAlcada() {
        return alcada;
    }

    public void setAlcada(String alcada) {
        this.alcada = alcada;
    }

    public String getAmplada() {
        return amplada;
    }

    public void setAmplada(String amplada) {
        this.amplada = amplada;
    }

    public String getPes() {
        return pes;
    }

    public void setPes(String pes) {
        this.pes = pes;
    }

    public String getArtista() {
        return idArtista;
    }

    public void setArtista(String idArtista) {
        this.idArtista = idArtista;
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
                ", any='" + any + '\'' +
                ", material='" + material + '\'' +
                ", alcada='" + alcada + '\'' +
                ", amplada='" + amplada + '\'' +
                ", pes='" + pes + '\'' +
                ", artista=" + idArtista +
                ", fotos=" + fotos +
                ", audios=" + audios +
                '}';
    }
}
