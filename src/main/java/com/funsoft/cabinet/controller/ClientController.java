package com.funsoft.cabinet.controller;



import com.funsoft.cabinet.model.Client;
import com.funsoft.cabinet.repository.ClientRepository;
import com.funsoft.cabinet.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

// Cette page est reellement un model MVC :Model & View & Control

@RestController
public class ClientController {
    @Autowired
    ClientService agent;

    @RequestMapping(value="/",method = RequestMethod.GET)
    public ModelAndView hello(){
        ModelAndView model = new ModelAndView();
        model.setViewName("index"); // specifier le page web à retourner
        return (model);
    }

    @RequestMapping(value="/addClient",method = RequestMethod.GET)
    public String test_ajout_client(){
    Client c=new Client();
    c.setNom("Mohamed");
    c.setPrenom("Ali");
    agent.save_or_update(c);
    return "client ajouté:"+"/n"+c.toString();
    }

    @RequestMapping(value="/clients/add",method = RequestMethod.GET)
    public ModelAndView ajouter_client(){
        ModelAndView model=new ModelAndView();
        Client c=new Client();
        model.addObject("clientForm",c);
        model.setViewName("Client_Form");
        return(model);
    }

    @RequestMapping(value="/clients/save",method = RequestMethod.POST)
    public ModelAndView add_client(@Valid @ModelAttribute("clientForm") Client cl, BindingResult result){
        if(result.hasErrors()){
            return (new ModelAndView("Client_Form"));
        }
        else{
        agent.save_or_update(cl);
        return (new ModelAndView("redirect:/clients/list"));
        }
    }

    @RequestMapping(value="/clients/list",method = RequestMethod.GET)
    public ModelAndView list_clients(){
        List<Client> clients=(List<Client>) agent.consulte(); // recupérer la liste des clients ajoutées  ala base de donnée
        ModelAndView model = new ModelAndView();
        model.addObject("clients",clients); // ajouter la liste en tant que objet a votre model
        model.setViewName("mes_clients"); // indiquer le nom de la page
    return (model);
    }

    //controller pour supprimer un client dont l'id est idc
    @RequestMapping(value="/clients/delete/{idc}",method = RequestMethod.GET)
    public ModelAndView delete_client(@PathVariable("idc") long idclient){
        agent.delete(idclient);
        return (new ModelAndView("redirect:/clients/list"));
    }

    // controller pour modifier un client dont l'id est idc
    @RequestMapping(value="/clients/update/{idc}",method = RequestMethod.GET)
    public ModelAndView edit_client(@PathVariable("idc") long idclient){
        Client cl=agent.findById(idclient);
        ModelAndView model = new ModelAndView();
        model.addObject("clientForm",cl);
        model.setViewName("Client_Edit");
        return (model);
    }

}
