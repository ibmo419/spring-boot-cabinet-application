package com.funsoft.cabinet.model;

//POJO


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="clients")


public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @Size(min=5,max=10)
    @Column(name="nom_client")
    @Pattern(regexp = "[a-zA-Z]+",message = "must not contain special characters")
    private String nom;

    @NotEmpty
    @Size(min=5,max=10)
    @Column(name="prenom_client")
    @Pattern(regexp = "[a-zA-Z]+",message = "must not contain special characters")
    private String prenom;


    public Client(){

        
    }
    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    // liason de client avec le tableau de rendez-vous
    // Lazy : c a d le telechargement que des données client necessaire a partir de la base de données mais pas toute les données
    // Remove : une fois on supprime un client toutes ses rendez-vous se supprime automatiquement .

    @OneToMany(mappedBy = "client",fetch=FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Rv> lrdvs;

    public List<Rv> getLrdvs() {
        return lrdvs;
    }

    public void setLrdvs(List<Rv> lrdvs) {
        this.lrdvs = lrdvs;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", lrdvs=" + lrdvs +
                '}';
    }
}
