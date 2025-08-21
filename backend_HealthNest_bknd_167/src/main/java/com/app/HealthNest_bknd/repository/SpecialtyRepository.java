package com.app.HealthNest_bknd.repository;


import com.app.HealthNest_bknd.model.Specialty;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class SpecialtyRepository extends SimpleJpaRepository<Specialty, String> {
    private final EntityManager em;
    public SpecialtyRepository(EntityManager em) {
        super(Specialty.class, em);
        this.em = em;
    }
    @Override
    public List<Specialty> findAll() {
        return em.createNativeQuery("Select * from \"healthnest_bknd_127\".\"Specialty\"", Specialty.class).getResultList();
    }
}