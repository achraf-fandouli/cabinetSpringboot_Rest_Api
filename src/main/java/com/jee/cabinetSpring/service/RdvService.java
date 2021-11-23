package com.jee.cabinetSpring.service;

import com.jee.cabinetSpring.model.Rdv;

import java.util.List;
import java.util.Optional;

public interface RdvService {

    public void add_rdv(Rdv rdv);
    public Optional<Rdv> find_rdv(long id);
    public void delete_rdv(long id);
    public void update_rdv(Rdv rdv);
    public List<Rdv> findAll();
}
