package com.funsoft.cabinet.model;

import javax.persistence.*;

//creation d'une entité + creation d'un tableau
@Entity
@Table(name="rv")

public class Rv {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    private String jour;

// creation de la liaison entre le tableau medecin et le tableau rendez-vous
    @JoinColumn(name="ID_Medecin",referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Medecin medecin;
    // creation de la liaison entre le tableau client et le tableau rendez-vous
    @JoinColumn(name="ID_Client",referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Client client;

    //Constructeur
    public Rv(){
    this.client= new Client();
    this.medecin=new Medecin();
    }

    public long getId() {
        return id;
    }

    public String getJour() {
        return jour;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }


    // getter et setter de 2 attributs client et medecin
    public Medecin getMedecin() {
        return medecin;
    }

    public Client getClient() {
        return client;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
