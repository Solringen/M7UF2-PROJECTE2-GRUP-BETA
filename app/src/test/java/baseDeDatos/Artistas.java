package baseDeDatos;

import java.sql.Blob;
import java.util.List;

public class Artistas {
    private String id;
    private String nom;
    private String cogNom;
    private Blob audio;
    private Blob imatge;
    private String biografia;
    private String correntArtistic;
    private List<String> escultura;

    public Artistas(String id, String nom, String cogNom, Blob audio, Blob imatge, String biografia, String correntArtistic, List<String> escultura) {
        this.id = id;
        this.nom = nom;
        this.cogNom = cogNom;
        this.audio = audio;
        this.imatge = imatge;
        this.biografia = biografia;
        this.correntArtistic = correntArtistic;
        this.escultura = escultura;
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

    public List<String> getEscultura() {
        return escultura;
    }

    public void setEscultura(List<String> escultura) {
        this.escultura = escultura;
    }

    @Override
    public String toString() {
        return "Artistas{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", cogNom='" + cogNom + '\'' +
                ", audio=" + audio +
                ", imatge=" + imatge +
                ", biografia='" + biografia + '\'' +
                ", correntArtistic='" + correntArtistic + '\'' +
                ", escultura=" + escultura +
                '}';
    }
}
