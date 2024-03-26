package com.putubgs.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

import com.putubgs.model.User;

public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public User saveUser(User user) {
        entityManager.persist(user);
        return user;
    }

    
}

