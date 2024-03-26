package com.putubgs.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;

@Entity
@Table(name = "p_transaction")
public class Transaction {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "transaction_id", nullable = false)
    private String id;
    
    @Column(name = "transaction_amount")
    private Double amount;
    
    @Column(name = "transaction_date")
    private LocalDateTime date;
    
    private String description;
    
    private boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Transaction() {}

    public Transaction(String id, Double amount, LocalDateTime date, String description, boolean deleted, User user, Category category) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.deleted = deleted;
        this.user = user;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("Transaction[id=%s, amount=%.2f, date=%s, description=%s, deleted=%b, user=%s, category=%s]",
                             id, amount, date, description, deleted, user != null ? user.getId() : "null", category != null ? category.getId() : "null");
    }

    @PrePersist
    public void prePersist() {
        if (date == null) {
            date = LocalDateTime.now();
        }
    }
}

