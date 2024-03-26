package com.putubgs.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "p_category")
@SQLDelete(sql = "UPDATE p_category SET deleted = true WHERE category_id = ?")
public class Category {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "category_id", nullable = false)
    private String id;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "category_type", nullable = false)
    private String categoryType;

    @Column(name = "category_icon", nullable = false)
    private String categoryIcon;

    @Column(name = "custom_category", nullable = false)
    private boolean customCategory;

    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<BudgetPlan> budgetPlans = new ArrayList<>();

    public Category() {
        this.deleted = false;
        this.customCategory = false;
    }

    public Category(String id, String categoryName, String categoryType, String categoryIcon, boolean customCategory, User user) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.categoryIcon = categoryIcon;
        this.customCategory = customCategory;
        this.deleted = false;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public boolean isCustomCategory() {
        return customCategory;
    }

    public void setCustomCategory(boolean customCategory) {
        this.customCategory = customCategory;
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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<BudgetPlan> getBudgetPlans() {
        return budgetPlans;
    }

    public void setBudgetPlans(List<BudgetPlan> budgetPlans) {
        this.budgetPlans = budgetPlans;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryType='" + categoryType + '\'' +
                ", categoryIcon='" + categoryIcon + '\'' +
                ", customCategory=" + customCategory +
                ", deleted=" + deleted +
                ", user=" + (user != null ? user.getId() : "null") +
                '}';
    }
}
