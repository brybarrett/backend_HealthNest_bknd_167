package com.app.HealthNest_bknd.repository;


import com.app.HealthNest_bknd.model.Slot;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class SlotRepository extends SimpleJpaRepository<Slot, String> {
    private final EntityManager em;
    public SlotRepository(EntityManager em) {
        super(Slot.class, em);
        this.em = em;
    }
    @Override
    public List<Slot> findAll() {
        return em.createNativeQuery("Select * from \"healthnest_bknd_127\".\"Slot\"", Slot.class).getResultList();
    }
}