package com.jee.cabinetSpring.service;

import com.jee.cabinetSpring.model.Patient;
import com.jee.cabinetSpring.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService{

    //injecter PatientRepository
    @Autowired
    PatientRepository agentPatient;

    @Override
    public Patient add_patient(Patient p) {
        return agentPatient.save(p);
    }

    @Override
    public Optional<Patient> find_patient(long id) {
        return agentPatient.findById(id);
    }

    @Override
    public void delete_patient(long id) {
        agentPatient.deleteById(id);

    }

    @Override
    public Patient update_patient(Patient p) {
        return agentPatient.save(p);
    }

    @Override
    public List<Patient> findAll() {
        return (List<Patient>) agentPatient.findAll();
    }
}
