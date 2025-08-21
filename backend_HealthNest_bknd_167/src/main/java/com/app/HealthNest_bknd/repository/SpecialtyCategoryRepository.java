package com.app.HealthNest_bknd.repository;


import com.app.HealthNest_bknd.model.SpecialtyCategory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class SpecialtyCategoryRepository extends SimpleJpaRepository<SpecialtyCategory, String> {
    private final EntityManager em;
    public SpecialtyCategoryRepository(EntityManager em) {
        super(SpecialtyCategory.class, em);
        this.em = em;
    }
    @Override
    public List<SpecialtyCategory> findAll() {
        return em.createNativeQuery("Select * from \"healthnest_bknd_127\".\"SpecialtyCategory\"", SpecialtyCategory.class).getResultList();
    }
}