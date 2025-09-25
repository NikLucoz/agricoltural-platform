package it.unicam.cs.agricultural_platform.models;

import it.unicam.cs.agricultural_platform.models.user.User;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "content_seq")
    @SequenceGenerator(name = "content_seq", sequenceName = "content_sequence", allocationSize = 1)
    private Long id;
    private String name;
    private String description;
    private boolean isApproved = false;
    private boolean reviewNeeded = false;
    private double price;
    private int stockQuantity;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    protected User author;

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
        return isApproved;
    }

    public boolean isReviewNeeded() {
        return reviewNeeded;
    }

    public User getAuthor() {
        return author;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public void setReviewNeeded(boolean reviewNeeded) {
        reviewNeeded = reviewNeeded;
    }

    public void setAuthor(User author) {
        this.author = author;
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
