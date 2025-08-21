package com.app.HealthNest_bknd.repository;


import com.app.HealthNest_bknd.model.Weekday;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class WeekdayRepository extends SimpleJpaRepository<Weekday, String> {
    private final EntityManager em;
    public WeekdayRepository(EntityManager em) {
        super(Weekday.class, em);
        this.em = em;
    }
    @Override
    public List<Weekday> findAll() {
        return em.createNativeQuery("Select * from \"healthnest_bknd_127\".\"Weekday\"", Weekday.class).getResultList();
    }
}