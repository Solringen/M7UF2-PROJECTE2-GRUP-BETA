package com.example.m7uf2_projecte2_grup_beta;

import com.google.firebase.firestore.Blob;

import java.util.ArrayList;
import java.util.List;

public class Artistas {
    private String id;
    private String nom;
    private String cogNom;
    private List<Blob> fotos;
    private List<Blob> audios;
    private String biografia;
    private String correntArtistic;
    private List<Esculturas> escultura;

     public Artistas(){
         this.fotos = new ArrayList<Blob>();
     }

    public Artistas(String id, String nom, String cogNom, String biografia, String correntArtistic, List<Esculturas> escultura) {
        this.id = id;
        this.nom = nom;
        this.cogNom = cogNom;
        this.biografia = biografia;
        this.correntArtistic = correntArtistic;
        this.escultura = escultura;
        this.fotos = new ArrayList<Blob>();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCogNom() {
        return cogNom;
    }

    public void setCogNom(String cogNom) {
        this.cogNom = cogNom;
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

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getCorrentArtistic() {
        return correntArtistic;
    }

    public void setCorrentArtistic(String correntArtistic) {
        this.correntArtistic = correntArtistic;
    }

    public List<Esculturas> getEscultura() {
        return escultura;
    }

    public void setEscultura(List<Esculturas> escultura) {
        this.escultura = escultura;
    }

    @Override
    public String toString() {
        return "Artistas{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", cogNom='" + cogNom + '\'' +
                ", fotos=" + fotos +
                ", audios=" + audios +
                ", biografia='" + biografia + '\'' +
                ", correntArtistic='" + correntArtistic + '\'' +
                ", escultura=" + escultura +
                '}';
    }
}
