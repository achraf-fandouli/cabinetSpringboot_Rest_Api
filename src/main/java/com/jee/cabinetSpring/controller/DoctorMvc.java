package com.jee.cabinetSpring.controller;

import com.jee.cabinetSpring.exception.ResourceNotFound;
import com.jee.cabinetSpring.model.Doctor;
import com.jee.cabinetSpring.model.Search;
import com.jee.cabinetSpring.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.print.Doc;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorMvc {

    @Autowired
    DoctorService agentDoctor;


    @GetMapping
    public List<Doctor> dispplay_doctors(){
        return agentDoctor.findAll();
    }


    @GetMapping("/{id}")
    public Doctor find_doctor(@PathVariable("id") long id) throws ResourceNotFound{
        Doctor d= agentDoctor.find_doctor(id).orElseThrow(
                () -> new ResourceNotFound("Doctor not found for id : "+id)
        );

        return d;
    }


//    @DeleteMapping("/{id}")
//    public Map<String,Boolean> delete_doctor(@PathVariable("id") long id) throws ResourceNotFound
//    {
//        agentDoctor.delete_doctor(id);
//        Map<String,Boolean> res=new HashMap<>();
//        res.put("deleted",Boolean.TRUE);
//        return res;
//    }

    @DeleteMapping("/{id}")
    public Map<String,Boolean> delete_doctor(@PathVariable("id") long id) throws ResourceNotFound
    {
        Doctor d= agentDoctor.find_doctor(id).orElseThrow(
                () -> new ResourceNotFound("Doctor not found for id : "+id)
        );

        agentDoctor.delete_doctor(id);
        Map<String,Boolean> res=new HashMap<>();
        res.put("deleted",Boolean.TRUE);
        return res;
    }

    @PostMapping
    //@RequestBody : le contenue de body de la requte va etre convertie en objet doctor
    public Doctor add_doctor(@RequestBody Doctor doctor){
        return agentDoctor.add_doctor(doctor);
    }


    @PutMapping("/{id}")
    public Doctor update_doctor(@PathVariable("id") long id,@RequestBody Doctor doctor){
        Doctor d = agentDoctor.find_doctor(id).get();
        d.setFirstname(doctor.getFirstname());
        d.setLastname(doctor.getLastname());
        d.setAddress(doctor.getAddress());
        d.setSpecialite(doctor.getSpecialite());
        return agentDoctor.update_doctor(d);
    }




}
