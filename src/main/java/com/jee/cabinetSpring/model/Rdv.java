package com.jee.cabinetSpring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Optional;

@Getter
@Setter

@Entity
@Table(name = "rdvs")
public class Rdv {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    //assertTrue(GenericValidator.isDate("2019-02-28", "yyyy-MM-dd", true));
    //@DateTimeFormat(pattern="yyyy-MM-dd")
    @NotEmpty
    private String date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;


    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    public Rdv() {
        this.patient = new Patient();
        this.doctor = new Doctor();
    }
}
