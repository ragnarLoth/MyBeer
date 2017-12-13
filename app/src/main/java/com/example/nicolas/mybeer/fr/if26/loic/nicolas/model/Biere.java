package com.example.nicolas.mybeer.fr.if26.loic.nicolas.model;

/**
 * Created by Nicolas on 09/11/2017.
 */

public class Biere {
    private String nom;
    private Float degre;
    private Float note;
    private int id;
    private String comment;

    //biere stockée dans la base
    public Biere(String nom, Float degre, Float note, int id, String comment) {
        this.nom = nom;
        this.degre = degre;
        this.note = note;
        this.id = id;
        this.comment = comment;
    }

    //bière avant l'ajout dans la base
    public Biere(String nom, Float degre, Float note, String comment) {
        this.nom = nom;
        this.degre = degre;
        this.note = note;
        this.comment = comment;
    }


    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setId(int id) {
        this.id = id;
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
