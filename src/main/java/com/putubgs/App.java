package com.putubgs;

import com.putubgs.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class App {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("predana_mvp");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            
            User newUser = new User();
            newUser.setUsername("putu");
            newUser.setEmail("putu@example.com");
            // Hash the password before saving
            String hashedPassword = encoder.encode("securepassword");
            newUser.setPassword(hashedPassword);

            em.persist(newUser);
            em.getTransaction().commit();

            // Now fetch the user data using a native SQL query
            String sql = "SELECT * FROM p_user WHERE username = 'putu'";
            List<User> users = em.createNativeQuery(sql, User.class).getResultList();

            // Display the user data
            for (User user : users) {
                System.out.println("User fetched from database:");
                System.out.println("Username: " + user.getUsername());
                System.out.println("Email: " + user.getEmail());
                // Don’t print the password; just showing it here for completeness
                System.out.println("Password: " + user.getPassword());
                boolean isMatch = checkPassword("securepasswords", hashedPassword);
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

    public static boolean checkPassword(String rawPassword, String storedHash) {
        return encoder.matches(rawPassword, storedHash);
    }
}
