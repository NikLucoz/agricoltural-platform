package it.unicam.cs.agricultural_platform.models;

import it.unicam.cs.agricultural_platform.models.user.User;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean IsApproved = false;
    private boolean ReviewNeeded = false;
    private double price;
    private int stockQuantity;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User Author;

    public Content() {

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
