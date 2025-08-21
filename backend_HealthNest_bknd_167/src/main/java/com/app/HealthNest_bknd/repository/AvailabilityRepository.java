package com.app.HealthNest_bknd.repository;


import com.app.HealthNest_bknd.model.Availability;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class AvailabilityRepository extends SimpleJpaRepository<Availability, String> {
    private final EntityManager em;
    public AvailabilityRepository(EntityManager em) {
        super(Availability.class, em);
        this.em = em;
    }
    @Override
    public List<Availability> findAll() {
        return em.createNativeQuery("Select * from \"healthnest_bknd_127\".\"Availability\"", Availability.class).getResultList();
    }
}