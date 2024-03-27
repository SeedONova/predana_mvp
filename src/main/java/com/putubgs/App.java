package com.putubgs;

import com.putubgs.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.putubgs.util.SecurityUtil;

import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("predana_mvp");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            
            User newUser = new User();
            newUser.setUsername("putu");
            newUser.setEmail("putu@example.com");
            String hashedPassword = SecurityUtil.encodePassword("securepassword");
            newUser.setPassword(hashedPassword);

            em.persist(newUser);
            em.getTransaction().commit();

            String sql = "SELECT * FROM p_user WHERE username = 'putu'";
            List<User> users = em.createNativeQuery(sql, User.class).getResultList();

            for (User user : users) {
                System.out.println("User fetched from database:");
                System.out.println("Username: " + user.getUsername());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Password: " + user.getPassword());
                boolean isMatch = SecurityUtil.checkPassword("securepasswords", hashedPassword);
                System.out.println("Password match: " + isMatch);
            }
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}
