package com.jee.cabinetSpring.service;

import com.jee.cabinetSpring.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    public Patient add_patient(Patient p);
    public Optional<Patient> find_patient(long id);
    public void delete_patient(long id);
    public Patient update_patient(Patient d);
    public List<Patient> findAll();
}
