package com.app.HealthNest_bknd.repository;


import com.app.HealthNest_bknd.model.Notification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class NotificationRepository extends SimpleJpaRepository<Notification, String> {
    private final EntityManager em;
    public NotificationRepository(EntityManager em) {
        super(Notification.class, em);
        this.em = em;
    }
    @Override
    public List<Notification> findAll() {
        return em.createNativeQuery("Select * from \"healthnest_bknd_127\".\"Notification\"", Notification.class).getResultList();
    }
}