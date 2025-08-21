package com.app.HealthNest_bknd.repository;


import com.app.HealthNest_bknd.model.Doctor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class DoctorRepository extends SimpleJpaRepository<Doctor, String> {
    private final EntityManager em;
    public DoctorRepository(EntityManager em) {
        super(Doctor.class, em);
        this.em = em;
    }
    @Override
    public List<Doctor> findAll() {
        return em.createNativeQuery("Select * from \"healthnest_bknd_127\".\"Doctor\"", Doctor.class).getResultList();
    }
}