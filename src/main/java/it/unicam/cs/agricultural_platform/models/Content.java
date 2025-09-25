package it.unicam.cs.agricultural_platform.models;

import it.unicam.cs.agricultural_platform.models.user.User;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private boolean IsApproved = false;
    private boolean ReviewNeeded = false;
    private double price;
    private int stockQuantity;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User Author;

    public Content() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isApproved() {
        return IsApproved;
    }

    public boolean isReviewNeeded() {
        return ReviewNeeded;
    }

    public User getAuthor() {
        return Author;
    }

    public void setApproved(boolean approved) {
        IsApproved = approved;
    }

    public void setReviewNeeded(boolean reviewNeeded) {
        ReviewNeeded = reviewNeeded;
    }

    public void setAuthor(User author) {
        Author = author;
    }

    public Long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stock_quantity) {
        this.stockQuantity = stock_quantity;
    }
}
