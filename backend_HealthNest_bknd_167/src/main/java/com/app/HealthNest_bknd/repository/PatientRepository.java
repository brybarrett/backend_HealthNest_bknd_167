package com.app.HealthNest_bknd.repository;


import com.app.HealthNest_bknd.model.Patient;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class PatientRepository extends SimpleJpaRepository<Patient, String> {
    private final EntityManager em;
    public PatientRepository(EntityManager em) {
        super(Patient.class, em);
        this.em = em;
    }
    @Override
    public List<Patient> findAll() {
        return em.createNativeQuery("Select * from \"healthnest_bknd_127\".\"Patient\"", Patient.class).getResultList();
    }
}