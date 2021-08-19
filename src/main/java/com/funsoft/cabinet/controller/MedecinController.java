package com.funsoft.cabinet.controller;

import com.funsoft.cabinet.model.Medecin;
import com.funsoft.cabinet.model.Recherche;
import com.funsoft.cabinet.repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController

public class MedecinController {
    @Autowired
    MedecinRepository agent;

    @RequestMapping(value = "/medecins/add",method = RequestMethod.GET)
    public ModelAndView add_med(){
        ModelAndView model =new ModelAndView();
        Medecin med = new Medecin();
        model.addObject("medForm",med);
        model.setViewName("Medecin_Form");
        return model;
    }
    @RequestMapping(value="/medecins/save",method = RequestMethod.POST)
    public ModelAndView save(@Valid @ModelAttribute("medForm") Medecin med, BindingResult result){
        if(result.hasErrors()){
            return (new ModelAndView("Medecin_Form"));
        }
        else {
            agent.save(med);
            return new ModelAndView("redirect:/medecins/list");
        }
    }
    // controlleur pour affichage de la list de médecins a partir de BD
    @RequestMapping(value="/medecins/list",method = RequestMethod.GET)
    public ModelAndView list_med(){
        List<Medecin> meds=(List<Medecin>) agent.findAll();
        ModelAndView model = new ModelAndView();
        model.addObject("meds",meds);
        model.setViewName("mes_medecins");
        return  model;
    }

    // controlleur pour la partie recherche de médecin selon la spécialité
    @RequestMapping(value="/medecins/find",method = RequestMethod.GET)
    public ModelAndView find_medecins(){
        Recherche res =new Recherche();
        ModelAndView model = new ModelAndView();
        model.addObject("resForm",res);
        model.setViewName("Find_Medecin");
        return  model;
    }


    //controlleur pour lancer la recherche medecins selon spécilaité
    @RequestMapping(value="/medecins/recherche",method = RequestMethod.POST)
    public ModelAndView recherche_medecin(@ModelAttribute("resForm") Recherche res){
        List<Medecin> lmeds=agent.findBySpeciality(res.getSpeciality());
        res.setMeds(lmeds);
        ModelAndView model=new ModelAndView();
        model.addObject("resForm",res);
        model.setViewName("Find_Medecin");
        return model;
    }
   // controlleur pour acceder le page web Advanced_Find_Medecin pour faire la recherche avancée sur un pseudo de nom ou prenom des medecins
    @RequestMapping(value="/medecins/advancedfind",method = RequestMethod.GET)
    public ModelAndView advancedfind_medecins(){
        Recherche res =new Recherche();
        ModelAndView model = new ModelAndView();
        model.addObject("resForm",res);
        model.setViewName("Advanced_Find_Medecin");
        return  model;
    }

    //controlleur pour lancer la recherche avancée medecins par spécilité et par pseudo
    @RequestMapping(value="/medecins/rechercheavancee",method = RequestMethod.POST)
    public ModelAndView rechercheavancee_medecin(@ModelAttribute("resForm") Recherche res){
        List<Medecin> lmeds=agent.Search(res.getSpeciality(), "%"+res.getPseudo()+"%");
        res.setMeds(lmeds);
        ModelAndView model=new ModelAndView();
        model.addObject("resForm",res);
        model.setViewName("Advanced_Find_Medecin");
        return model;
    }

    //controller pour supprimer un client dont l'id est idm
    @RequestMapping(value="/medecins/delete/{idm}",method = RequestMethod.GET)
    public ModelAndView delete_client(@PathVariable("idm") long idmedecin){
        agent.deleteById(idmedecin);
        return (new ModelAndView("redirect:/medecins/list"));
    }
// Controlleur pour modifier un médecin dont l'id est idm
    @RequestMapping(value = "/medecins/update/{idm}",method = RequestMethod.GET)
    public ModelAndView edit_medecin(@PathVariable("idm") long idmedecin){
        Medecin md =  agent.findById(idmedecin).get();
        ModelAndView model = new ModelAndView();
        model.addObject("medForm",md);
        model.setViewName("Medecin_Edit");
        return (model);

    }
}
