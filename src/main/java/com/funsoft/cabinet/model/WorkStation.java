package com.funsoft.cabinet.model;

import javax.persistence.*;

@Entity
@Table(name="workstation")
public class WorkStation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @OneToOne(mappedBy = "workStation")
    private Employe employe;

    public WorkStation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
