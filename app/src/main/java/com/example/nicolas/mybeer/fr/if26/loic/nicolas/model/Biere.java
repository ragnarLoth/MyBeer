package com.example.nicolas.mybeer.fr.if26.loic.nicolas.model;

/**
 * Created by Nicolas on 09/11/2017.
 */

public class Biere {
    private String nom;
    private String degre;
    private String note;

    public Biere(String nom, String degre, String note) {
        this.nom = nom;
        this.degre = degre;
        this.note = note;
    }

    public String getLabel() {
        return nom;
    }

    public String getPrix() {
        return degre;
    }

    public String getDate() {
        return note;
    }

    public void setLabel(String nom) {
        this.nom = nom;
    }

    public void setDate(String note) {
        this.note = note;
    }

    public void setPrix(String degre) {
        this.degre = degre;
    }

    @Override
    public String toString() {
        return "Depense{" +
                "nom='" + nom + '\'' +
                ", degre=" + degre + ", note: " + note +
                '}';
    }

}
