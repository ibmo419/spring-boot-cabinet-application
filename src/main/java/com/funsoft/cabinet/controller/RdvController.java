package com.funsoft.cabinet.controller;

import com.funsoft.cabinet.model.Client;
import com.funsoft.cabinet.model.Medecin;
import com.funsoft.cabinet.model.Rv;
import com.funsoft.cabinet.repository.ClientRepository;
import com.funsoft.cabinet.repository.MedecinRepository;
import com.funsoft.cabinet.repository.RvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class RdvController {
    @Autowired
    RvRepository agent;
    @Autowired
    ClientRepository agentclient;
    @Autowired
    MedecinRepository agentmed;


    @RequestMapping(value="/rdv/add",method = RequestMethod.GET)
    public ModelAndView add_rdv(){
        List<Medecin> meds=(List<Medecin>)agentmed.findAll();
        List<Client> clients=(List<Client>)agentclient.findAll();
        ModelAndView model = new ModelAndView();
        Rv rdv= new Rv();
        model.addObject("meds",meds);
        model.addObject("clients",clients);
        model.addObject("rdvForm",rdv);
        model.setViewName("Rv_Form");
        return model;
    }

    @RequestMapping(value="/rdv/save",method = RequestMethod.POST)
    public ModelAndView save_rdv(@ModelAttribute("rdvForm") Rv rdv){
        agent.save(rdv);
        return new ModelAndView("redirect:/rdv/list");
    }

    // controlleur pour affichage de la list de médecins a partir de BD
    @RequestMapping(value="/rdv/list",method = RequestMethod.GET)
    public ModelAndView list_rdv(){
        List<Rv> rdvs=(List<Rv>) agent.findAll();
        ModelAndView model = new ModelAndView();
        model.addObject("rdvs",rdvs);
        model.setViewName("mes_rendez_vous");
        return  model;
    }
    //controller pour supprimer un rendez-vous dont l'id est idr
    @RequestMapping(value="/rdv/delete/{idm}",method = RequestMethod.GET)
    public ModelAndView delete_rdv(@PathVariable("idm") long idrdv){
        agent.deleteById(idrdv);
        return (new ModelAndView("redirect:/rdv/list"));
    }
    // Controlleur pour modifier un rendez-vous dont l'id est idr
    @RequestMapping(value = "/rdv/update/{idr}",method = RequestMethod.GET)
    public ModelAndView edit_rdv(@PathVariable("idr") long idrdv){
        Rv rd =  agent.findById(idrdv).get();
        ModelAndView model = new ModelAndView();
        List<Medecin> meds=(List<Medecin>)agentmed.findAll();
        List<Client> clients=(List<Client>)agentclient.findAll();
        model.addObject("rdvForm",rd);
        model.addObject("meds",meds);
        model.addObject("clients",clients);
        model.setViewName("Rendez_Vous_Edit");
        return (model);

    }
}
