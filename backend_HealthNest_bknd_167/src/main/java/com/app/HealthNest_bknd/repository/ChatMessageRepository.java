package com.app.HealthNest_bknd.repository;


import com.app.HealthNest_bknd.model.ChatMessage;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class ChatMessageRepository extends SimpleJpaRepository<ChatMessage, String> {
    private final EntityManager em;
    public ChatMessageRepository(EntityManager em) {
        super(ChatMessage.class, em);
        this.em = em;
    }
    @Override
    public List<ChatMessage> findAll() {
        return em.createNativeQuery("Select * from \"healthnest_bknd_127\".\"ChatMessage\"", ChatMessage.class).getResultList();
    }
}