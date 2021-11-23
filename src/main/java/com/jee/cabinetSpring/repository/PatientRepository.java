package com.jee.cabinetSpring.repository;

import com.jee.cabinetSpring.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient,Long> {

}
