package com.app.HealthNest_bknd.repository;


import com.app.HealthNest_bknd.model.Prescription;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class PrescriptionRepository extends SimpleJpaRepository<Prescription, String> {
    private final EntityManager em;
    public PrescriptionRepository(EntityManager em) {
        super(Prescription.class, em);
        this.em = em;
    }
    @Override
    public List<Prescription> findAll() {
        return em.createNativeQuery("Select * from \"healthnest_bknd_127\".\"Prescription\"", Prescription.class).getResultList();
    }
}