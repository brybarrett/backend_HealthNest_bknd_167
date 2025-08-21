package com.app.HealthNest_bknd.repository;


import com.app.HealthNest_bknd.model.Review;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class ReviewRepository extends SimpleJpaRepository<Review, String> {
    private final EntityManager em;
    public ReviewRepository(EntityManager em) {
        super(Review.class, em);
        this.em = em;
    }
    @Override
    public List<Review> findAll() {
        return em.createNativeQuery("Select * from \"healthnest_bknd_127\".\"Review\"", Review.class).getResultList();
    }
}