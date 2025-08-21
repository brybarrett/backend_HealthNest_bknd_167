package com.app.HealthNest_bknd.repository;


import com.app.HealthNest_bknd.model.Appointment;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class AppointmentRepository extends SimpleJpaRepository<Appointment, String> {
    private final EntityManager em;
    public AppointmentRepository(EntityManager em) {
        super(Appointment.class, em);
        this.em = em;
    }
    @Override
    public List<Appointment> findAll() {
        return em.createNativeQuery("Select * from \"healthnest_bknd_127\".\"Appointment\"", Appointment.class).getResultList();
    }
}