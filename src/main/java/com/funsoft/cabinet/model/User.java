package com.funsoft.cabinet.model;


import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    private String username;

    // creation de la liaison entre l'utilisateur et l'address
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_address",referencedColumnName = "id")
    private Address address;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Employe employe;

    public User(){

    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
}
