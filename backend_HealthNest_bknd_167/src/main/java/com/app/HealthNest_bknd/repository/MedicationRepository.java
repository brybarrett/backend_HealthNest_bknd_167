package com.app.HealthNest_bknd.repository;


import com.app.HealthNest_bknd.model.Medication;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class MedicationRepository extends SimpleJpaRepository<Medication, String> {
    private final EntityManager em;
    public MedicationRepository(EntityManager em) {
        super(Medication.class, em);
        this.em = em;
    }
    @Override
    public List<Medication> findAll() {
        return em.createNativeQuery("Select * from \"healthnest_bknd_127\".\"Medication\"", Medication.class).getResultList();
    }
}