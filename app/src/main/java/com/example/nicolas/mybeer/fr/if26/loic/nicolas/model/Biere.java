package com.example.nicolas.mybeer.fr.if26.loic.nicolas.model;

/**
 * Created by Nicolas on 09/11/2017.
 */

public class Biere {
    private String nom;
    private Float degre;
    private Float note;

    public Biere(String nom, Float degre, Float note) {
        this.nom = nom;
        this.degre = degre;
        this.note = note;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Float getDegre() {
        return degre;
    }

    public void setDegre(Float degre) {
        this.degre = degre;
    }

    public Float getNote() {
        return note;
    }

    public void setNote(Float note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Depense{" +
                "nom='" + nom + '\'' +
                ", degre=" + degre + ", note: " + note +
                '}';
    }

}
