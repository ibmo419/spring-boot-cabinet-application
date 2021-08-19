package com.funsoft.cabinet.model;

import java.util.List;

public class Recherche {
    private String speciality;
    private List<Medecin> meds ;
    private String pseudo;

    public Recherche(){ }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public List<Medecin> getMeds() {
        return meds;
    }

    public void setMeds(List<Medecin> meds) {
        this.meds = meds;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
