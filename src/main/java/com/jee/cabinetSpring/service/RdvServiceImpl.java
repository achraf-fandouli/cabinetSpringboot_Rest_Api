package com.jee.cabinetSpring.service;

import com.jee.cabinetSpring.model.Rdv;
import com.jee.cabinetSpring.repository.RdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RdvServiceImpl implements RdvService{

    @Autowired
    RdvRepository agent;

    @Override
    public void add_rdv(Rdv rdv) {
        agent.save(rdv);
    }

    @Override
    public Optional<Rdv> find_rdv(long id) {
        return agent.findById(id);
    }

    @Override
    public void delete_rdv(long id) {
        agent.deleteById(id);
    }

    @Override
    public void update_rdv(Rdv rdv) {
        agent.save(rdv);
    }

    @Override
    public List<Rdv> findAll() {
        return (List<Rdv>)agent.findAll();
    }
}
