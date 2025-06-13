package xyz.dedsecm.vroomvroomcar.model;

public class Vehicule {
    private int id;
    private String immatricule;
    private String marque;
    private String modele;
    private String categorie;
    private Integer motorisation;
    private String photoUrl;
    private Integer dateCreation;
    private Integer revenueAnnuelDePrevision;

    public Vehicule() {
    }

    public Vehicule(int id, String immatricule, String marque, String modele, String categorie) {
        this.id = id;
        this.immatricule = immatricule;
        this.marque = marque;
        this.modele = modele;
        this.categorie = categorie;
    }

    public int getId() {
        return id;
    }

    public String getImmatricule() {
        return immatricule;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public String getCategorie() {
        return categorie;
    }

    public Integer getMotorisation() {
        return motorisation;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public Integer getDateCreation() {
        return dateCreation;
    }

    public Integer getRevenueAnnuelDePrevision() {
        return revenueAnnuelDePrevision;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImmatricule(String immatricule) {
        this.immatricule = immatricule;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setMotorisation(Integer motorisation) {
        this.motorisation = motorisation;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setDateCreation(Integer dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setRevenueAnnuelDePrevision(Integer revenueAnnuelDePrevision) {
        this.revenueAnnuelDePrevision = revenueAnnuelDePrevision;
    }

    public String toString() {
        return "Vehicule{" +
                "id=" + id +
                ", immatricule='" + immatricule + '\'' +
                ", marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", categorie='" + categorie + '\'' +
                ", motorisation=" + motorisation +
                ", photoUrl='" + photoUrl + '\'' +
                ", dateCreation=" + dateCreation +
                ", revenueAnnuelDePrevision=" + revenueAnnuelDePrevision +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicule vehicule = (Vehicule) o;
        return id == vehicule.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
