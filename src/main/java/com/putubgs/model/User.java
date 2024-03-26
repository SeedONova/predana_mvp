package com.putubgs.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "p_user")
public class User {
    @Id 
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;


    private String username;
    private String email;
    private String password;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    private boolean deleted = Boolean.FALSE;

    public User(){}

    public User(String id, String username, String email, String password, LocalDateTime dateCreated, boolean deleted) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateCreated = dateCreated;
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Transaction> transactions = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<BudgetPlan> budgetPlans = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s", id, username, email, password, dateCreated, deleted);
    }

    @PrePersist
    public void prePersist() {
        if (dateCreated == null) {
            dateCreated = LocalDateTime.now();
        }
    }
}
