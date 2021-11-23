package com.jee.cabinetSpring.controller;

import com.jee.cabinetSpring.model.Doctor;
import com.jee.cabinetSpring.model.Patient;
import com.jee.cabinetSpring.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientMvc {

    @Autowired
    PatientService agentPatient;


//    //welcome page (http://localhost:8082/clinique)
//    @RequestMapping(value = "/",method = RequestMethod.GET)
//    public ModelAndView index(){
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("index");
//        return mv;
//    }

    //Display form add patient
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public ModelAndView form_add() {
        ModelAndView mv = new ModelAndView();
        //view
        mv.setViewName("patient");
        //c'est le modele
        Patient patient = new Patient();
        mv.addObject("Formpatient", patient);
        return mv;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    //transformer les donnees de formulaire en un objet via la methode save()
    public ModelAndView save(@ModelAttribute("Formpatient") Patient p){
        agentPatient.add_patient(p);
        //retourner un modelAndView et faire la redirection vers la racine(la racine retourne un modeAndView)
        return new ModelAndView("redirect:/patients/list");
    }


    //Display form-list-RDV
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView display_patients(){
        ModelAndView mv = new ModelAndView();
        //view c'est la page Html
        mv.setViewName("patients");
        //c'est le modele
        List<Patient> patients = agentPatient.findAll();
        mv.addObject("listpatients",patients);
        return mv;
    }

    @RequestMapping(value = "/patients/delete/{id}",method =RequestMethod.GET)
    public ModelAndView delete_patients(@PathVariable("id")long id){
        agentPatient.delete_patient(id);
        return new ModelAndView("redirect:/patients/list");
    }


    @RequestMapping(value = "/patients/update/{id}",method =RequestMethod.GET)
    public ModelAndView display_form_update_patient(@PathVariable("id")long id){
        /*Puisque le retour est optional donc on a besoin de ajouter .get() a la fin
        pour recuperer l'id si existe
        */
        Patient patient = agentPatient.find_patient(id).get();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("editpatient");
        mv.addObject("Formpatient",patient);
        return mv;
    }

}
