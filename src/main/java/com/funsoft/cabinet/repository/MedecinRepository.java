package com.funsoft.cabinet.repository;

import com.funsoft.cabinet.model.Medecin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedecinRepository extends CrudRepository<Medecin,Long> {
// select * from medecin spécilité=spec
    public List<Medecin> findBySpeciality(String spec);
    public List<Medecin> findByNom(String nom);
    public List<Medecin> findBySpecialityAndNom(String spec,String nom);

    @Query(value="Select m from Medecin m where m.speciality = :spec "+
    "and (m.nom Like :pseudo or m.prenom Like : pseudo)")
    public List<Medecin> Search(@Param("spec") String s,@Param("pseudo") String p);
}
