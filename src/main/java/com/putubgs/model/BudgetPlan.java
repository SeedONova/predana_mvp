package com.putubgs.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDateTime;

@Entity
@Table(name = "p_budget_plan")
@SQLDelete(sql = "UPDATE p_budget_plan SET deleted = true WHERE budget_id = ?")
public class BudgetPlan {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "budget_id", nullable = false)
    private String id;

    @Column(name = "limit_amount", nullable = false)
    private Double limitAmount;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    private boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public BudgetPlan() {
        this.deleted = false;
    }

    public BudgetPlan(String id, Double limitAmount, LocalDateTime dateCreated, boolean deleted, User user, Category category) {
        this.id = id;
        this.limitAmount = limitAmount;
        this.dateCreated = dateCreated;
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

    public Double getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(Double limitAmount) {
        this.limitAmount = limitAmount;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
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
        return String.format("BudgetPlan[id=%s, limitAmount=%.2f, dateCreated=%s, deleted=%b, user=%s, category=%s]",
                             id, limitAmount, dateCreated, deleted, 
                             user != null ? user.getId() : "null", 
                             category != null ? category.getId() : "null");
    }

    @PrePersist
    public void prePersist() {
        if (dateCreated == null) {
            dateCreated = LocalDateTime.now();
        }
    }
}
