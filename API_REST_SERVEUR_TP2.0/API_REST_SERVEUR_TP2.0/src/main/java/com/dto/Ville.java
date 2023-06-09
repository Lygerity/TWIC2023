package com.dto;

public class Ville {
    private String nom;
    private String codePostal;
    private String ligne;
    private String codeCommune;
    private String latitude;
    private String longitude;

    public Ville(){
        this.setLigne(null);
        this.setNom(null);
        this.setCodePostal(null);
        this.setCodeCommune(null);
        this.setLatitude(null);
        this.setLongitude(null);
    }

    public String getInfo(){
        return "Nom : "+ this.getNom() + " Code Postal : " +this.getCodePostal();
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }

    public String getCodeCommune() {
        return codeCommune;
    }

    public void setCodeCommune(String codeCommune) {
        this.codeCommune = codeCommune;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
