package com.jee.cabinetSpring.controller;

import com.jee.cabinetSpring.model.Doctor;
import com.jee.cabinetSpring.model.Patient;
import com.jee.cabinetSpring.model.Rdv;
import com.jee.cabinetSpring.repository.PatientRepository;
import com.jee.cabinetSpring.service.DoctorService;
import com.jee.cabinetSpring.service.RdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.stylesheets.LinkStyle;

import java.net.http.HttpClient;
import java.util.List;

@Controller
public class RdvMvc {

    @Autowired
    RdvService agentRdv;

    @Autowired
    DoctorService agentDoctor;

    @Autowired
    PatientRepository agentPatient;

    @RequestMapping(value = "/rdvs/add", method = RequestMethod.GET)
    public ModelAndView dispaly_form() {
        List<Doctor> doctors = agentDoctor.findAll();
        List<Patient> patients = (List<Patient>) agentPatient.findAll();

        Rdv rdv = new Rdv();

        ModelAndView mv = new ModelAndView();

        mv.setViewName("rdv");
        mv.addObject("FormRdv", rdv);
        mv.addObject("doctors", doctors);
        mv.addObject("patients", patients);
        return mv;
    }


    @RequestMapping(value = "/rdvs/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("FormRdv") Rdv rdv) {
        agentRdv.add_rdv(rdv);
        return new ModelAndView("redirect:/rdvs/list");
    }

    //Display form-list-RDV
    @RequestMapping(value = "/rdvs/list",method = RequestMethod.GET)
    public ModelAndView display_rdvss(){
        ModelAndView mv = new ModelAndView();
        //view c'est la page html doctors.html
        List<Rdv> rdvsList = agentRdv.findAll();
        mv.setViewName("rdvs");
        mv.addObject("Rdvs",rdvsList);
        return mv;
    }


    @RequestMapping(value = "/rdvs/doctor/detail/{id}",method =RequestMethod.GET)
    public ModelAndView detail_Doctor(@PathVariable("id")long id){
        //agentRdv.delete_rdv(id);
        ModelAndView mv = new ModelAndView();
        Doctor doctor = agentDoctor.find_doctor(id).get();
        mv.setViewName("detailDoctor");
        System.out.println("doctor.getFirstname() = " + doctor.getFirstname());
        mv.addObject("doctordetail",doctor);
        return mv;
                //new ModelAndView("redirect:/rdvs/list");
    }

    @RequestMapping(value = "/rdvs/patient/detail/{id}",method =RequestMethod.GET)
    public ModelAndView detail_Patient(@PathVariable("id")long id){
        //agentRdv.delete_rdv(id);
        ModelAndView mv = new ModelAndView();
        Patient patient = agentPatient.findById(id).get();
        mv.setViewName("detailPatient");
        mv.addObject("patientdetail",patient);
        return mv;
        //new ModelAndView("redirect:/rdvs/list");
    }

    @RequestMapping(value = "/rdvs/delete/{id}",method = RequestMethod.GET)
    public ModelAndView delete_Rdv(@PathVariable("id") long id){
        agentRdv.delete_rdv(id);
        return new ModelAndView("redirect:/rdvs/list");
    }

    //via l'id de Rendez-vous charger le formulaire avec les données et afficher la page afin de faire la modification
    @RequestMapping(value = "/rdvs/update/{id}",method = RequestMethod.GET)
    public ModelAndView update_rdv(@PathVariable("id") long id){

        Rdv rdv = agentRdv.find_rdv(id).get();

        List<Doctor> doctors = agentDoctor.findAll();
        List<Patient> patients = (List<Patient>)agentPatient.findAll();

        ModelAndView mv = new ModelAndView();

        mv.addObject("FormRdv",rdv);
        mv.addObject("doctors",doctors);
        mv.addObject("patients",patients);

        mv.setViewName("editrdv");

        return mv;
    }
}
