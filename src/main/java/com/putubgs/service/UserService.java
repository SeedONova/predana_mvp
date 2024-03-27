package com.putubgs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.putubgs.model.User;
import com.putubgs.repository.UserRepository;
import com.putubgs.util.SecurityUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UserService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("predana_mvp");

    public User createUser(String username, String email, String password) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPassword(SecurityUtil.encodePassword(password));

            em.persist(newUser);
            em.getTransaction().commit();

            return newUser;
        } finally {
            em.close();
        }
    }

    public boolean validateUserPassword(String username, String password) {
        EntityManager em = emf.createEntityManager();
        try {
            String sql = "SELECT * FROM p_user WHERE username = :username";
            List<User> users = em.createNativeQuery(sql, User.class)
                                  .setParameter("username", username)
                                  .getResultList();

            if (!users.isEmpty()) {
                User user = users.get(0);
                return SecurityUtil.checkPassword(password, user.getPassword());
            }

            return false;
        } finally {
            em.close();
        }
    }
}